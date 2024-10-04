package dev.yassiraitelghari.web;

import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.services.UserService;
import dev.yassiraitelghari.services.UserServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
    private UserService userService = new UserServiceImp();
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
        String stringId = request.getParameter("id");
        if(stringId!=null){
            int id = Integer.parseInt(stringId);
            User user = userService.findById(id);
            if(user != null){
                request.setAttribute("user" , user);
                request.getRequestDispatcher("dashboard/addTask.jsp").forward(request ,response);
            }
        }
        response.sendRedirect("profile");
    }
}
