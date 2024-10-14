package dev.yassiraitelghari.web;

import dev.yassiraitelghari.domain.Task;
import dev.yassiraitelghari.services.interfaces.TaskService;
import dev.yassiraitelghari.services.implmentations.TaskServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TasksTaskServlet")
public class TasksServlet extends HttpServlet {

    private TaskService taskService = new TaskServiceImp();
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        if((request.getSession().getAttribute("user"))==null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


        request.setAttribute("tasks" , taskService.getAll());
        request.getRequestDispatcher("admin/tasks.jsp").forward(request , response);
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        if((request.getSession().getAttribute("user"))==null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String tag = request.getParameter("tag");
        List<Task> tasks = taskService.getAll().stream().filter(task -> taskService.isTaskWithTag(task , tag)).toList();
        request.setAttribute("tasks", tasks);
        request.getRequestDispatcher("admin/tasks.jsp").forward(request , response);
    }
}
