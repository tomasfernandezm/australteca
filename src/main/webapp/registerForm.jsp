<%@ page import="org.securityfilter.example.Constants" %><%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 26/3/17
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title><%=Constants.LOGIN_TITLE%></title>
</head>
<body>
<h1> Australteca </h1>
<%@include file="/menu.jsp" %>
    <form id="<%=Constants.LOGIN_FORM_ID%>" action="<%=response.encodeURL(Constants.LOGIN_FORM_ACTION)%>" method="POST">

        Email:
        <input type="text" name= "mail"/>

        Password:
        <input type="password" name="password" />

        Password confirmation:
        <input type="password" name="password2" />


        <select name="career">
            <option value="Ingenieria informatica" selected>Ingenieria informatica</option>
            <option value="Ingenieria indutrial">Ingenieria indutrial</option>
        </select>


    <input type="Submit" name="Unirme">

</form>

</body>
</html>
