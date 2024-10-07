package dev.yassiraitelghari.web;

import dev.yassiraitelghari.domain.Task;
import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.services.TaskService;
import dev.yassiraitelghari.services.TaskServiceImp;
import dev.yassiraitelghari.services.UserService;
import dev.yassiraitelghari.services.UserServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dev.yassiraitelghari.utils.TaskErrors;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
    private UserService userService = new UserServiceImp();
    private TaskService taskService = new TaskServiceImp();
    private User userWithTask;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      User activeUser = (User) request.getSession().getAttribute("user");
       if(activeUser.getRole().equals("Manager")){
           String stringId = request.getParameter("id");
           if (stringId != null) {
               int id = Integer.parseInt(stringId);
               User user = userService.findById(id);
               userWithTask = user;
               if (user != null) {
                   request.setAttribute("user", user);
                   request.getRequestDispatcher("/dashboard/addTask.jsp").forward(request, response);
               }
           }
       }else{
           userWithTask = activeUser;
           request.setAttribute("user", activeUser);
           request.getRequestDispatcher("/dashboard/addTask.jsp").forward(request, response);
       }
        response.sendRedirect("../profile");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskErrors taskError = new TaskErrors();
        LocalDateTime startLocalDateTime = null;
        LocalDateTime endLocalDateTime = null;
        boolean isErrorExist = false;
        String title = request.getParameter("name");
        String description = request.getParameter("description");
        String startDate = request.getParameter("startDate");
        String startTime = request.getParameter("startTime");
        String dateLimit = request.getParameter("dateLimit");
        String timeLimit = request.getParameter("timeLimit");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");


        if (title == null || !taskService.validateTitle(title)) {
            taskError.setTitleError("Title must be at least 3 Characters");
            isErrorExist = true;
        }

        if (description == null || !taskService.validateDescription(description)) {
            taskError.setDescriptionError("Description must be at least 15 Characters");
            isErrorExist = true;

        }

        if (startDate.isEmpty() || startTime.isEmpty()) {
            taskError.setStartDateError("Fill start Date");
            isErrorExist = true;
        } else {
            LocalDate date = LocalDate.parse(startDate, dateFormatter);
            LocalTime time = LocalTime.parse(startTime, timeFormatter);
            startLocalDateTime = LocalDateTime.of(date, time);
            if (!taskService.validateStartDate(startLocalDateTime)) {
                taskError.setStartDateError("Date cant be in the past");
                isErrorExist = true;
            }else if(!taskService.isDateWithin3Days(startLocalDateTime)){
                taskError.setStartDateError("Start date must within the next 3 days");
            }
        }

        if (dateLimit.isEmpty() || timeLimit.isEmpty()) {
            taskError.setEndDateError("Fill end Date");
            isErrorExist = true;

        } else {
            LocalDate endDate = LocalDate.parse(dateLimit, dateFormatter);
            LocalTime endTime = LocalTime.parse(timeLimit, timeFormatter);
            endLocalDateTime = LocalDateTime.of(endDate, endTime);
            if (!taskService.validateDateLimit(endLocalDateTime, startLocalDateTime)) {
                taskError.setEndDateError("End Date cant be before start Date");
                isErrorExist = true;

            }else if(!taskService.isDateWithin3Days(endLocalDateTime)){
                taskError.setEndDateError("End date must be within the next 3 days");
                isErrorExist = true;
            }
        }
        if (isErrorExist) {
            request.setAttribute("error", taskError);
            request.getRequestDispatcher("/dashboard/addTask.jsp").forward(request, response);
        } else {
            Task task = new Task();
            task.setName(title);
            task.setDescription(description);
            if (userWithTask != null) {
                task.setUser(userWithTask);
            }
            task.setStartDate(startLocalDateTime);
            task.setDateLimit(endLocalDateTime);
            task.setStatus("In Progress");
            request.getSession().setAttribute("task",task);
            response.sendRedirect("addTags");

        }
    }
}
