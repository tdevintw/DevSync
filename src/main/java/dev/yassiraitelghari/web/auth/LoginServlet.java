package dev.yassiraitelghari.web.auth;

import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.exceptions.InvalidCredentialsException;
import dev.yassiraitelghari.services.interfaces.UserService;
import dev.yassiraitelghari.services.implmentations.UserServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginServlet" , urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImp();
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
       HttpSession session = request.getSession(false);
       if(session != null &&  session.getAttribute("user")!=null){
           User user = (User)session.getAttribute("user");
                if(user.getRole().equals("MANAGER")){
                    response.sendRedirect("dashboard");
                }else{
                    response.sendRedirect("profile");
                }
           return;

       }
        request.getRequestDispatcher("auth/login.jsp").forward(request , response);
    }

    protected void doPost(HttpServletRequest request , HttpServletResponse response ) throws IOException , ServletException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try{
            Optional<User> user = userService.get(email , password);
            if(user.isPresent()){
                HttpSession session = request.getSession();
                session.setAttribute("user",user.get());
                response.sendRedirect("profile");
            }
        }catch (InvalidCredentialsException exception){
            request.setAttribute("error" , exception.getMessage());
            request.getRequestDispatcher("auth/login.jsp").forward(request , response);
        }
    }
}
