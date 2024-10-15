package dev.yassiraitelghari.web.auth;

import java.io.IOException;

import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.services.interfaces.UserService;
import dev.yassiraitelghari.services.implmentations.UserServiceImp;
import dev.yassiraitelghari.utils.RegisterValidationMessages;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                if (user.equals("MANAGER")) {
                    response.sendRedirect("dashboard");
                } else {
                    response.sendRedirect("profile");
                }
                return;
            }
        }
        request.getRequestDispatcher("auth/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String name = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String password = request.getParameter("password");
        String role = request.getParameter("role").toUpperCase();
        RegisterValidationMessages validation = userService.add(username, email, name, lastName, password, role);

        if (validation.getRegistredUser() != null) {
            response.sendRedirect("login");
        } else {
            request.setAttribute("registerValidation",validation);
            request.getRequestDispatcher("auth/register.jsp").forward(request, response);
        }
    }
}
