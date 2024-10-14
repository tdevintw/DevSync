package dev.yassiraitelghari.web.admin;

import dev.yassiraitelghari.services.interfaces.TaskService;
import dev.yassiraitelghari.services.implmentations.TaskServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StaticServlet extends HttpServlet {
    private TaskService taskService = new TaskServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choice = request.getParameter("by");
        List<String> key = new ArrayList<>();
        List<Integer> value = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM");
        String title;

        if (choice != null && choice.equals("month")) {
            Map<YearMonth, Integer> sortedMap = new TreeMap<>(taskService.staticsByMonths());
            for (Map.Entry<YearMonth, Integer> entry : sortedMap.entrySet()) {
                key.add(entry.getKey().format(formatter));
                value.add(entry.getValue());
            }
            title = "Validated Tasks Per Month";
        } else if (choice != null && choice.equals("year")) {
            Map<Integer, Integer> sortedMap = new TreeMap<>(taskService.staticsByYears());
            for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
                key.add("Year " + entry.getKey());
                value.add(entry.getValue());
            }
            title = "Validated Tasks Per Year";
        } else {
            Map<Integer, Integer> sortedMap = new TreeMap<>(taskService.staticsByWeeks());
            for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
                key.add("Week " + entry.getKey());
                value.add(entry.getValue());
            }
            title = "Validated Tasks Per Week";
        }

        request.setAttribute("values", value);
        request.setAttribute("keys", key);
        request.setAttribute("title", title);
        request.getRequestDispatcher("statics.jsp").forward(request, response);

    }
}
