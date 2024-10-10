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

import java.io.IOException;

@WebServlet(name = "AssignTaskServlet")
public class AssignTaskServlet extends HttpServlet {
    private UserService userService = new UserServiceImp();
    private Task taskToAssign = null;
    private TaskService taskService = new TaskServiceImp();
    private User oldUser = null;
    @Override
    protected  void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        if(((User)(request.getSession().getAttribute("user"))).getRole().equals("MANAGER")){
            request.setAttribute("users",userService.getAll());
            int taskId = Integer.parseInt(request.getParameter("task_id"));
            int oldUserId = Integer.parseInt(request.getParameter("old_user_id"));
            Task task = taskService.findTask(taskId);
            User oldUser = userService.findById(oldUserId);
            if(task!=null &&oldUser != null){
                taskToAssign = task;
                this.oldUser = oldUser;
            }else{
                response.sendRedirect("requests");
                return;
            }
            request.getRequestDispatcher("assignToUsers.jsp").forward(request , response);
        }else{
            request.getRequestDispatcher("4040.jsp").forward(request , response);
        }
    }

    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
         User newUser = userService.findById(userId);
        if(newUser==null || this.oldUser==null){
            response.sendRedirect("requests");
        }else{
            Task task = this.taskToAssign;
            task.setUser(newUser);
            taskService.update(task);
            newUser.getTasks().add(task);
            User oldUser = this.oldUser;
            userService.update(newUser);
            oldUser.getTasks().remove(task);
            userService.update(oldUser);
        }
        response.sendRedirect("dashboard");
    }
}
