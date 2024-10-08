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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {

    private UserService userService = new UserServiceImp();
    private TaskService taskService = new TaskServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (user.getRole().equals("MANAGER")) {
                List<User> users = userService.getAll();
                request.setAttribute("users", users);
                request.setAttribute("size", users.size());
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            } else if (user.getRole().equals("CLIENT")) {
                List<Task> tasks = null;
                if(taskService.updateTasks(user.getId())){
                    tasks = taskService.findTasks(user.getId());
                }
                request.setAttribute("tasks", tasks);
                request.setAttribute("size", tasks.size());
                request.getRequestDispatcher("client/dashboard.jsp").forward(request, response);
            }

        }

        response.sendRedirect("profile");

    }
}
