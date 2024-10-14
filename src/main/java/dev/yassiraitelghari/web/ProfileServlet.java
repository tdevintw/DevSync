package dev.yassiraitelghari.web;

import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.services.interfaces.UserService;
import dev.yassiraitelghari.services.implmentations.UserServiceImp;
import dev.yassiraitelghari.utils.ProfileUpdateValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {


    private UserService userService = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if((request.getSession().getAttribute("user"))==null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;

        }


        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }
        }
        response.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if((request.getSession().getAttribute("user"))==null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;

        }


        String firstName = request.getParameter("name");
        String lastName = request.getParameter("last_name");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String method = request.getParameter("_method");
        User user = (User) request.getSession(false).getAttribute("user");
        if (method != null && method.equals("DELETE")) {
            if (userService.delete(user)) {
                request.getSession(false).invalidate();
            }
            response.sendRedirect("login");
            return;
        }
        ProfileUpdateValidation profileUpdateValidation = userService.updateProfile(user, firstName, lastName, password, confirmPassword);
        if(profileUpdateValidation.getUpdatedUser()!=null){
            request.getSession().setAttribute("user",profileUpdateValidation.getUpdatedUser());
        }
        request.setAttribute("errors", profileUpdateValidation);
        request.getRequestDispatcher("profile.jsp").forward(request, response);

    }
}
