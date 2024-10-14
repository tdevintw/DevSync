package dev.yassiraitelghari.web;

import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.services.interfaces.UserService;
import dev.yassiraitelghari.services.implmentations.UserServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {


    private UserService userService =  new UserServiceImp();
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
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("last_name");
        String password= request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String method = request.getParameter("_method");
        if(method != null && method.equals("DELETE")){
            User user = (User) request.getSession(false).getAttribute("user");
            if(userService.delete(user)){
                request.getSession(false).invalidate();
            }
            response.sendRedirect("login");
            return;
        }
        if(password.equals(confirmPassword)){
            User user = (User) request.getSession(false).getAttribute("user");
            user.setName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
           userService.update(user);
           response.sendRedirect("profile");
        }

    }
}
