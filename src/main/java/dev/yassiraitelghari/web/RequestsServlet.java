package dev.yassiraitelghari.web;

import dev.yassiraitelghari.domain.Request;
import dev.yassiraitelghari.domain.User;
import dev.yassiraitelghari.services.RequestService;
import dev.yassiraitelghari.services.RequestServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RequestsServlet")
public class RequestsServlet extends HttpServlet {
    private RequestService requestService = new RequestServiceImp();
    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
       if(((User)(request.getSession().getAttribute("user"))).getRole().equals("MANAGER")){
           List<Request> requestList = requestService.getAll().stream().filter(request1 -> request1.getStatus().equals("Pending")).toList();
           request.setAttribute("requests" ,requestList );
           request.getRequestDispatcher("requests.jsp").forward(request , response);
       }else{
           request.getRequestDispatcher("4040.jsp").forward(request , response);
       }

    }
}

