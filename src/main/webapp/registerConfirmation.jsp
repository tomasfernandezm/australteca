<%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 28/3/17
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dao.UserDAO confirmation</title>
</head>
<body>

<h1><%=MyConstants.MY_HOME_TITLE%></h1>
<%@ page import="org.securityfilter.example.MyConstants" %>

<% int status = (Integer) (request.getAttribute("status")); %>
<% if (status > 0) { %>
<p> Te has registrado correctamente!</p>
<a href="loginForm.jsp">Iniciar sesión</a>
<% } else { %>
<p> Ups! Algun problema ocurrió, intentalo de nuevo!</p>
<a href="loginForm.jsp">Registrarse</a>
<% } %>

</body>
</html>
