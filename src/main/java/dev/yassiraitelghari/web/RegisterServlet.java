package dev.yassiraitelghari.web;

import java.io.IOException;

import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.services.UserService;
import dev.yassiraitelghari.services.UserServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = "username2";
        String email = request.getParameter("email");
        String name = "name";
        String lastName = "lastName";
        String password = "password";
        String role = request.getParameter("role").toUpperCase(); // Adjust based on how you handle roles

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setName(name);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setRole(role);
        if(userService.add(user)!=null){
        response.sendRedirect("login");
        }else{
            response.sendRedirect("/");
        }
    }
}
