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
    <title>manager.UserManager confirmation</title>
</head>
<body>

<h1><%=MyConstants.MY_HOME_TITLE%></h1>

<%@page import="manager.UserManager"%>
<%@ page import="org.securityfilter.example.MyConstants" %>
<%@ page import="manager.UserManager" %>
<%@ page import="entity.User" %>
<jsp:useBean id="obj" class="entity.User"/>
<jsp:setProperty property="*" name="obj"/>

<%

        String name = request.getParameter("name");
        String lname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordC = request.getParameter("passwordC");
        String career = request.getParameter("career");

        int status = new UserManager().add(new User(name, lname, email, career, password, false, false));
    %>
<% if (status > 0) { %>
<p> Te has registrado correctamente!</p>
<a href="loginForm.jsp">Iniciar sesión</a>
<% } else { %>
<p> Ups! Algun problema ocurrió, intentalo de nuevo!</p>
<a href="loginForm.jsp">Registrarse</a>
<% } %>

</body>
</html>
