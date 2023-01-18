<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>

    <style>
        <%@include file="../styles/main.css"%>
    </style>

</head>

<body>

<%@include file="../pages/header.html" %>
<br>

<h1><%=response.getStatus()%>   Task with ID '<%=request.getParameter("id")%>' not found in To-Do List!</h1>

</body>
</html>