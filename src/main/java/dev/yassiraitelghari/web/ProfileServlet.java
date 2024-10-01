package dev.yassiraitelghari.web;

import dev.yassiraitelghari.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session !=null){
            User user = (User) session.getAttribute("user");
            if(user!=null){
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }
        }
        response.sendRedirect("login");

    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        HttpSession session = request.getSession(false);
        if(session !=null){
        User user = (User) session.getAttribute("user");
        if(user!=null){
            request.setAttribute("user",user);
            request.getRequestDispatcher("profile.jsp").forward(request,response);
            return;
        }
        }
        response.sendRedirect("login");
    }
}
