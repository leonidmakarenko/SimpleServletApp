<%@ page import="org.example.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Read existing Task</title>

  <style>
    <%@include file="../styles/main.css"%>
  </style>

</head>
<body>

<%@include file="../pages/header.html" %>

<h2>Read existing Task</h2>
<br>

<% Task task = (Task) request.getAttribute("task"); %>

<hr>
<table>
  <tr>
    <td><b>ID:</b></td>
    <td><%=task.getId()%></td>
  </tr>

  <tr>
    <td><b>TITLE:</b></td>
    <td><%=task.getTitle()%></td>
  </tr>

  <tr>
    <td><b>PRIORITY:</b></td>
    <td><%=task.getPriority()%></td>
  </tr>

</table>

<table border="" width="100%" class="buttons">
  <td align="center" width="50%"><a href="/edit-task?id=<%=task.getId()%>"> Edit </a></td>
  <td align="center" width="50%"><a href="/delete-task?id=<%=task.getId()%>">Delete</a></td>
</table>

</body>
</html>
