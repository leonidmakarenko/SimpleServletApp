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

@WebServlet("/edit-task")
public class UpdateTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;
    private Task task;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        task = taskRepository.read(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("task", task);
        try {
            request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp").forward(request, response);
        } catch (ServletException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (task == null) {
            task = taskRepository.read(Integer.parseInt(request.getParameter("id")));
        }
        String currentName = task.getTitle();
        if (request.getParameter("title") == null || request.getParameter("title").isEmpty()) {
            request.setAttribute("task", task);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("error", "Task name can't be empty!");
            request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp").forward(request, response);
            return;
        }
        if (request.getParameter("priority") == null || request.getParameter("priority").isEmpty()) {
            request.setAttribute("task", task);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.setAttribute("error", "Priority can't be empty!");
            request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp").forward(request, response);
            return;
        }
        task.setTitle(request.getParameter("title"));
        task.setPriority(Priority.valueOf(request.getParameter("priority")));
        if (taskRepository.update(task)) {
            response.sendRedirect("/tasks-list");
        } else {
            request.setAttribute("task", task);
            request.setAttribute("error", "Task with \"" + task.getTitle() + "\" name already exists!");
            request.getRequestDispatcher("/WEB-INF/pages/edit-task.jsp").forward(request, response);
            task.setTitle(currentName);
        }
    }
}