package dev.yassiraitelghari.web;

import dev.yassiraitelghari.domain.Task;
import dev.yassiraitelghari.repositories.TaskRepository;
import dev.yassiraitelghari.repositories.TaskRepositoryImp;
import dev.yassiraitelghari.services.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteTaskServlet extends HttpServlet {
    private TaskService taskService = new TaskServiceImp();
    private TagService tagService = new TagServiceImp();
    private UserService userService = new UserServiceImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("task_id");
        int parsedId = Integer.parseInt(id);
        Task task = taskService.findTask(parsedId);
        if (task.isAddedByMe()) {
            if (tagService.deleteByTask(parsedId)) {
                taskService.delete(parsedId);
            }
        } else {
            if (task.getUser().getDeleteJeton() > 0) {
                if (tagService.deleteByTask(parsedId)) {
                    taskService.delete(parsedId);
                }
                task.getUser().setDeleteJeton(0);
                userService.update( task.getUser());
            }

        }

        response.sendRedirect("../dashboard");
    }
}
