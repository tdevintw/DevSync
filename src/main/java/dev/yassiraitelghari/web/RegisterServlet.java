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
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = "username99";
        String email = request.getParameter("email");
        String name = "name";
        String lastName = "lastName";
        String password = "password";
        String role = request.getParameter("role").toUpperCase();

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setName(name);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setRole(role);
        user.setDeleteJeton(1);
        user.setReplaceJeton(2);
        if (userService.add(user) != null) {
            response.sendRedirect("login");
        } else {
            response.sendRedirect("/");
        }
    }
}
