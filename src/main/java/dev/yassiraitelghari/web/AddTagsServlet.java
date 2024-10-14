package dev.yassiraitelghari.web;

import dev.yassiraitelghari.domain.Task;
import dev.yassiraitelghari.services.interfaces.TagService;
import dev.yassiraitelghari.services.implmentations.TagServiceImp;
import dev.yassiraitelghari.services.interfaces.TaskService;
import dev.yassiraitelghari.services.implmentations.TaskServiceImp;
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
    private TaskService taskService = new TaskServiceImp();
    private TagService tagService = new TagServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if((request.getSession().getAttribute("user"))==null){
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        request.getRequestDispatcher("/dashboard/addTags.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if((request.getSession().getAttribute("user"))==null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        if (request.getParameter("cancel") != null) {
            User user = (User) request.getSession().getAttribute("user");
            request.getSession().invalidate();
            request.getSession().setAttribute("user", user);
            response.sendRedirect("../profile");

        }
        if (request.getParameter("tags") != null) {
            String tags = (String) request.getParameter("tags");
            if (tags.isEmpty()) {
                request.setAttribute("tagsError", TagErrors.getEmptyTags());
                request.getRequestDispatcher("/dashboard/addTags.jsp").forward(request, response);
            } else {
                String[] strings = tags.split(",");
                Task task = taskService.add((Task) request.getSession().getAttribute("task"));
                tagService.add(strings , task);
                response.sendRedirect("../profile");
            }
        }

    }
}
