package dev.yassiraitelghari.web;

import dev.yassiraitelghari.domain.Task;
import dev.yassiraitelghari.services.interfaces.TaskService;
import dev.yassiraitelghari.services.implmentations.TaskServiceImp;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "UpdateTaskServlet")
public class UpdateTaskServlet extends HttpServlet {
    private TaskService taskService = new TaskServiceImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if((request.getSession().getAttribute("user"))==null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        String method = request.getParameter("method");
        if (method.equals("VALIDATE")) {
            Task task = taskService.findTask(Integer.parseInt(request.getParameter("task_id")));
            task.setStatus("Validated");
            task.setValidatedAt(LocalDateTime.now());
            taskService.update(task);
            response.sendRedirect("../dashboard");

        } else if (method.equals("CANCELED")) {
            Task task = taskService.findTask(Integer.parseInt(request.getParameter("task_id")));
            task.setStatus("Canceled");
            taskService.update(task);
            response.sendRedirect("../dashboard");
        }
    }
}
