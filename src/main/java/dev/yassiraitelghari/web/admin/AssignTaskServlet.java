package dev.yassiraitelghari.web.admin;

import dev.yassiraitelghari.domain.Request;
import dev.yassiraitelghari.domain.Task;
import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.services.implmentations.RequestServiceImp;
import dev.yassiraitelghari.services.implmentations.TaskServiceImp;
import dev.yassiraitelghari.services.implmentations.UserServiceImp;
import dev.yassiraitelghari.services.interfaces.RequestService;
import dev.yassiraitelghari.services.interfaces.TaskService;
import dev.yassiraitelghari.services.interfaces.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(name = "AssignTaskServlet")
public class AssignTaskServlet extends HttpServlet {
    private UserService userService = new UserServiceImp();
    private Task taskToAssign = null;
    private TaskService taskService = new TaskServiceImp();
    private User oldUser = null;
    private RequestService requestService = new RequestServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user")!=null && !((User) (request.getSession().getAttribute("user"))).getRole().equals("MANAGER")) {
            int taskId = Integer.parseInt(request.getParameter("task_id"));
            int oldUserId = Integer.parseInt(request.getParameter("old_user_id"));
            Task task = taskService.findTask(taskId);
            User oldUser = userService.findById(oldUserId);
            List<User> filteredUsers = userService.getAll().stream().filter(user -> user.getRole().equals("CLIENT")).filter(user -> !user.getUsername().equals(oldUser.getUsername())).toList();
            request.setAttribute("users", filteredUsers);

            if (task != null && oldUser != null) {
                taskToAssign = task;
                this.oldUser = oldUser;
            } else {
                response.sendRedirect("requests");
                return;
            }
            request.getRequestDispatcher("admin/assignToUsers.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(request.getSession().getAttribute("user")==null || ((User)(request.getSession().getAttribute("user"))).getRole().equals("MANAGER")){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        int userId = Integer.parseInt(request.getParameter("user_id"));
        User newUser = userService.findById(userId);
        if (newUser == null || this.oldUser == null) {
            response.sendRedirect("requests");
            return;
        }
        Task task = this.taskToAssign;
        Request request1 = requestService.get(task.getRequest().getId());
        task.setUser(newUser);
        task.setIsReplaced(true);
        taskService.update(task);

        User oldUser = this.oldUser;
        oldUser.getTasks().remove(task);
        oldUser.setReplaceJeton(oldUser.getReplaceJeton() - 1);
        userService.update(oldUser);

        newUser.getTasks().add(task);
        userService.update(newUser);
        request1.setStatus("Accepted");
        requestService.update(request1);
        response.sendRedirect("dashboard");
    }
}
