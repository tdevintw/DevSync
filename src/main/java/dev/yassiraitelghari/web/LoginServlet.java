package dev.yassiraitelghari.web;

import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.services.UserService;
import dev.yassiraitelghari.services.UserServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet" , urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImp();
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
       HttpSession session = request.getSession(false);
       if(session != null){
           User user = (User) session.getAttribute("user");
            if(user !=null){
                if(user.getRole().equals("MANAGER")){
                    response.sendRedirect("profile");
                    return;
                }else{
                    response.sendRedirect("profile");
                    return;
                }
            }
       }
        request.getRequestDispatcher("login.jsp").forward(request , response);
    }

    protected void doPost(HttpServletRequest request , HttpServletResponse response ) throws IOException , ServletException{
        String email = request.getParameter("email");
        User user = userService.get(email);
        if(user!=null){
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            response.sendRedirect("profile");
        }else{
            response.sendRedirect("register");
        }
    }
}
