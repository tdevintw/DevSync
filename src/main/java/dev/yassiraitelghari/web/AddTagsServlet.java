package dev.yassiraitelghari.web;

import dev.yassiraitelghari.utils.TagErrors;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dev.yassiraitelghari.domain.User;
import java.io.IOException;

@WebServlet(name = "AddTagsServlet")
public class AddTagsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/dashboard/addTags.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
        if(request.getParameter("cancel")!=null){
            User user = (User)request.getSession().getAttribute("user");
            request.getSession().invalidate();
            request.getSession().setAttribute("user",user);
            response.sendRedirect("../profile");

        }
        if(request.getParameter("tags")!=null){
            String tags = (String)request.getParameter("tags");
            if(tags.isEmpty()){
                request.setAttribute("tagsError",TagErrors.getEmptyTags());
                request.getRequestDispatcher("/dashboard/addTags.jsp").forward(request,response);
            }else{
                String[] strings = tags.split(",");
                request.getRequestDispatcher("../login.jsp").forward(request,response);
            }
        }

    }
}
