package dev.yassiraitelghari.web;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String stringId = request.getParameter("id");
        if (stringId != null) {
            int id = Integer.parseInt(stringId);
            User user = userService.findById(id);
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("dashboard/addTask.jsp").forward(request, response);
            }
        }
        response.sendRedirect("profile");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskErrors taskError = new TaskErrors();
        boolean isErrorExist = false;
        String title = request.getParameter("name");
        String description = request.getParameter("description");
        String startDate = request.getParameter("startDate");
        String startTime = request.getParameter("startTime");
        String dateLimit = request.getParameter("dateLimit");
        String timeLimit = request.getParameter("timeLimit");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");


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
            LocalDateTime startLocalDateTime = LocalDateTime.of(date, time);
            if (!taskService.validateStartDate(startLocalDateTime)) {
                taskError.setStartDateError("Date cant be in the past");
                isErrorExist = true;
            }
        }

        if (dateLimit.isEmpty() || timeLimit.isEmpty()) {
            taskError.setEndDateError("Fill end Date");
            isErrorExist = true;

        } else {
            LocalDate endDate = LocalDate.parse(dateLimit, dateFormatter);
            LocalTime endTime = LocalTime.parse(timeLimit, timeFormatter);
            LocalDateTime endLocalDateTime = LocalDateTime.of(endDate, endTime);
            if (!taskService.validateDateLimit(endLocalDateTime)) {
                taskError.setEndDateError("End Date cant be in the pass");
                isErrorExist = true;

            }
        }
        if (isErrorExist) {
            request.setAttribute("error", taskError);
            request.getRequestDispatcher("dashboard/addTask.jsp").forward(request, response);
        } else {
            response.sendRedirect("profile");
        }
    }
}
