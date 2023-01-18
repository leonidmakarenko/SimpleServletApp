package org.example.controller;

import org.example.model.Priority;
import org.example.model.Task;
import org.example.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-task")
public class CreateTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/pages/create-task.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        Priority priority = Priority.valueOf(request.getParameter("priority"));
        boolean res = taskRepository.all().stream().anyMatch(t -> t.getTitle().equals(title));
        if (!res) {
            taskRepository.create(new Task(title, priority));
            response.sendRedirect("/tasks-list");
        } else {
            request.setAttribute("error", "Task with \"" + title + "\" name already exists!");
            request.getRequestDispatcher("/WEB-INF/pages/create-task.jsp").forward(request, response);
        }
    }
}