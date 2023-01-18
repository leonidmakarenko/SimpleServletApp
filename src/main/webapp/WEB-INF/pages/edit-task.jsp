<%@ page import="org.example.model.Task" %>
<%@ page import="org.example.model.Priority" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Edit existing Task</title>

  <style>
    <%@include file="../styles/main.css"%>
  </style>

</head>
<body>
<%@include file="../pages/header.html" %>
<h1>Edit existing Task</h1>
<form action="/edit-task" method="post">
  <label><%=request.getAttribute("error") == null? "" : request.getAttribute("error")%></label> <br><br>

  <%
    Task task = (Task) request.getAttribute("task");
  %>


  <table>
    <tr>
      <td>
        <label for="id">Id:</label>
      </td>
      <td>
        <input type="text" id="id" name="id" value="<%=task.getId()%>" disabled>
      </td>
    </tr>
    <tr>
      <td>
        <label for="title">Name:</label>
      </td>
      <td>
        <input type="text" id="title" name="title" value="<%=task.getTitle()%>">
      </td>
    </tr>
    <tr>
      <td>
        <label>Priority:</label>
      </td>
      <td>
        <select name="priority">
          <%
            Priority[] priorities = Priority.values();
            for (Priority priority : priorities) {
              if (task.getPriority() == priority) {%>
          <option value="<%=priority%>" selected><%=priority%>
          </option>
          <%
          } else {%>
          <option value="<%=priority%>"><%=priority%>
          </option>
          <%
              }
            }
          %>
        </select>
      </td>
    </tr>
    <tr>
      <td>
        <input type="submit" value="Update">
      </td>
    </tr>
  </table>
</form>

</body>
</html>
