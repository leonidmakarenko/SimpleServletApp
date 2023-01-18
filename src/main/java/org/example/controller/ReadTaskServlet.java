package org.example.controller;

import org.example.model.Task;
import org.example.repository.TaskRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/read-task")
public class ReadTaskServlet extends HttpServlet {

    private TaskRepository taskRepository;

    @Override
    public void init() {
        taskRepository = TaskRepository.getTaskRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	Task task = taskRepository.read(Integer.parseInt(request.getParameter("id")));
    	request.setAttribute("task", task);
    	  try {
              request.getRequestDispatcher("/WEB-INF/pages/read-task.jsp").forward(request, response);
          } catch (ServletException e) {
        	  response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        	  request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);     	  
          }
    }
}