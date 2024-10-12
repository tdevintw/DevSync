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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {

    private UserService userService = new UserServiceImp();
    private TaskService taskService = new TaskServiceImp();
    private RequestService requestService = new RequestServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (user.getRole().equals("MANAGER")) {
                List<User> users = userService.getAll();
                for(User setUser : users){
                    setUser.setSuccessPercentage(userService.successPercentage(setUser));
                }
                request.setAttribute("users", users);
                int size = users.size() >0 ? users.size() : 0;
                request.setAttribute("size", size);
                List<Request> requestsWithToken = requestService.RequestWithToken();
                int tokenSize = requestsWithToken.isEmpty() ? 0  :  requestsWithToken.size() ;
                request.setAttribute("token_size" ,tokenSize);
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            } else if (user.getRole().equals("CLIENT")) {
                List<Task> tasks = null;
                int size = 0;
                if(taskService.updateTasks(user.getId())){
                    //update task to set status of expired tasks to expired and ont fetch them
                    tasks = taskService.findTasks(user.getId());
                    if(tasks!=null){
                        size = tasks.size();
                    }
                }
                request.setAttribute("tasks", tasks);
                request.setAttribute("size", size);

                request.getRequestDispatcher("client/dashboard.jsp").forward(request, response);
            }
        }
        response.sendRedirect("profile");

    }
}
