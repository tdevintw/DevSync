package dev.yassiraitelghari.web;

import dev.yassiraitelghari.domain.Request;
import dev.yassiraitelghari.domain.Task;
import dev.yassiraitelghari.services.RequestService;
import dev.yassiraitelghari.services.RequestServiceImp;
import dev.yassiraitelghari.services.TaskService;
import dev.yassiraitelghari.services.TaskServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "RequestServlet")

public class RequestServlet extends HttpServlet {
    private TaskService taskService = new TaskServiceImp();
    private RequestService requestServiece = new RequestServiceImp();
    private Task taskOfRequest = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Task task = taskService.findTask(Integer.parseInt(request.getParameter("task_id")));
        if(task.getUser().getReplaceJeton()<1){
            request.getSession().setAttribute("insufficient_token", "insufficient Replace Token , wait until next day");
            response.sendRedirect("dashboard");
        }else{
            taskOfRequest = task;
            request.setAttribute("task", task.getName());
            request.getRequestDispatcher("client/request.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentHour = LocalDateTime.now().getHour();
        int dayOfTheWeel = LocalDateTime.now().getDayOfWeek().getValue();

        if(dayOfTheWeel==6 || dayOfTheWeel ==7){
            request.setAttribute("error", "You cant send requests in weekend");
            request.getRequestDispatcher("client/request.jsp").forward(request, response);
        }else if(currentHour< 8 || currentHour >=18){
            request.setAttribute("error", "You can send requests only within 8AM And 6PM");
            request.getRequestDispatcher("client/request.jsp").forward(request, response);
        }else{
            Request newRequest = new Request();
            newRequest.setMessage(request.getParameter("message"));
            newRequest.setStatus("Pending");
            newRequest.setTask(taskOfRequest);
            requestServiece.add(newRequest);
        }
        response.sendRedirect("dashboard");
    }
}
