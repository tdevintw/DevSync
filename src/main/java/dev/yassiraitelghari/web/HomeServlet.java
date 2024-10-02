package dev.yassiraitelghari.web;

import dev.yassiraitelghari.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = {"/"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session!=null){
            User user = (User)session.getAttribute("user");
            if(user!=null){
                if(user.getRole().equals("MANAGER")){
                    response.sendRedirect("dashboard");
                }else{
                    response.sendRedirect("profile");
                }
            }
        }
        response.sendRedirect("login");
    }
}
