package dev.yassiraitelghari.web;

import dev.yassiraitelghari.domain.Request;
import dev.yassiraitelghari.domain.Task;
import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Hibernate;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AssignTaskServlet")
public class AssignTaskServlet extends HttpServlet {
    private UserService userService = new UserServiceImp();
    private Task taskToAssign = null;
    private TaskService taskService = new TaskServiceImp();
    private User oldUser = null;
    private RequestService requestService = new RequestServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (((User) (request.getSession().getAttribute("user"))).getRole().equals("MANAGER")) {
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
            request.getRequestDispatcher("assignToUsers.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("4040.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
