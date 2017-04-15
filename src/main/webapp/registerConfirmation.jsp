<%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 28/3/17
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>org.australteca.dao.UserDAO confirmation</title>
</head>
<body>

<h1><%=Constants.MY_HOME_TITLE%></h1>

<%@ page import="org.australteca.Constants" %>

<% int status = (Integer) (request.getAttribute("<%=Constants.STATUS%>")); %>
<% if (status > 0) { %>
<p> Te has registrado correctamente!</p>
<a href="loginForm.jsp">Iniciar sesión</a>
<% } else { %>
<p> Ups! Algun problema ocurrió, intentalo de nuevo!</p>
<a href="loginForm.jsp">Registrarse</a>
<% } %>

</body>
</html>
