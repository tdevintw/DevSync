package dev.yassiraitelghari.web;

import dev.yassiraitelghari.repositories.TaskRepository;
import dev.yassiraitelghari.repositories.TaskRepositoryImp;
import dev.yassiraitelghari.services.TagService;
import dev.yassiraitelghari.services.TagServiceImp;
import dev.yassiraitelghari.services.TaskService;
import dev.yassiraitelghari.services.TaskServiceImp;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteTaskServlet extends HttpServlet {
    private TaskService taskService = new TaskServiceImp();
    private TagService tagService = new TagServiceImp();
    @Override
    protected  void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String id = request.getParameter("task_id");
        int parsedId = Integer.parseInt(id);
        if(tagService.deleteByTask(parsedId)){
            taskService.delete(parsedId);
        }
        response.sendRedirect("../dashboard");
    }
}
