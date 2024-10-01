package dev.yassiraitelghari.web;

import java.io.IOException;

import dev.yassiraitelghari.domain.Role;
import dev.yassiraitelghari.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dev.yassiraitelghari.services.*;
@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService =new  UserServiceImp();
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        request.getRequestDispatcher("register.jsp").forward(request , response);
    }

    protected void doPost(HttpServletRequest request , HttpServletResponse response ) throws ServletException , IOException{

        String email = request.getParameter("email");
        String role = request.getParameter("role");
        User user = new User();
        user.setEmail(email);
        user.setRole(Role.valueOf(role));
        user.setName("ggg");
        user.setPassword("rfff");
        user.setUsername("fffffff");

        if(userService.add(user)!=null){
            request.getRequestDispatcher("login.jsp").forward(request , response);
        }else{
            request.getRequestDispatcher("index.jsp").forward(request , response);
        }
    }
}