
<%@ page import="org.example.model.Priority" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <title>Create new Task</title>

  <style>
    <%@include file="../styles/main.css"%>
  </style>

</head>
<body>
<%@include file="../pages/header.html"%>

<h1>Create new Task</h1>

<form action="create-task" method="post">
  <label><%=request.getAttribute("error") == null? "" : request.getAttribute("error")%></label> <br><br>
  <table class="table with invisible borders">
    <tr>
      <td>
        <label for="title">Name: </label>
      </td>
      <td>
        <input type="text" id="title" name="title" placeholder="Enter a task"
               value="<%=request.getParameter("title") == null ? "" : request.getParameter("title")%>"
        >
      </td>
    </tr>
    <tr>
      <td>
        <label for="priority">Priority: </label>
      </td>
      <td>
        <select id="priority" name="priority">
          <%
            Priority[] priorities = Priority.values();
            for (Priority priority : priorities) {
              if (priority.name().equals(request.getParameter("priority"))) {
          %>
          <option value="<%=priority%>" selected><%=priority%></option>
          <%
          } else {
          %>
          <option value="<%=priority%>"><%=priority%></option>
          <%
              }
            }
          %>
        </select>
      </td>
    </tr>
    <tr>
      <td>
        <input type="submit" value="Create">
      </td>
      <td>
        <input type="reset" value="Clear">
      </td>
    </tr>
  </table>

</form>


</body>
</html>
