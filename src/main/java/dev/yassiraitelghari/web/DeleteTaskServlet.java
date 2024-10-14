package dev.yassiraitelghari.web;

import dev.yassiraitelghari.domain.Task;
import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.services.implmentations.TagServiceImp;
import dev.yassiraitelghari.services.implmentations.TaskServiceImp;
import dev.yassiraitelghari.services.implmentations.UserServiceImp;
import dev.yassiraitelghari.services.interfaces.TagService;
import dev.yassiraitelghari.services.interfaces.TaskService;
import dev.yassiraitelghari.services.interfaces.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteTaskServlet extends HttpServlet {
    private TaskService taskService = new TaskServiceImp();
    private TagService tagService = new TagServiceImp();
    private UserService userService = new UserServiceImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        if((request.getSession().getAttribute("user"))==null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        String id = request.getParameter("task_id");
        int parsedId = Integer.parseInt(id);
        Task task = taskService.findTask(parsedId);
        if (task.isAddedByMe()) {
            if (tagService.deleteByTask(parsedId)) {
                taskService.delete(parsedId);
            }
        } else {
            if (task.getUser().getDeleteJeton() > 0) {
                User user = task.getUser();
                if (tagService.deleteByTask(parsedId)) {
                    taskService.delete(parsedId);
                }
                user.setDeleteJeton(0);
                userService.update(user);
            } else {
                request.getSession().setAttribute("insufficient_token", "insufficient Delete Token , wait until next month");
            }

        }
        response.sendRedirect("../dashboard");
    }
}
