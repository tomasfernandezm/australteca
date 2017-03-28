<%@ page import="org.securityfilter.example.Constants" %>
<%@ page import="org.securityfilter.example.MyConstants" %>
<%@ page import="entity.User" %><%--
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
<h1><%=MyConstants.MY_HOME_TITLE%></h1>

<%@include file="/menu.jsp" %>
    <form id="<%=MyConstants.REGISTER_FORM_ID%>" action="registerConfirmation.jsp" method="POST">

        Name:
        <input type="text" name= "name"/>

        Lastname:
        <input type="text" name= "lastname"/>

        Email:
        <input type="text" name= "email"/>

        Password:
        <input type="password" name="password" />

        Password confirmation:
        <input type="password" name="passwordC" />


        <select name="career">
            <option value="Ingenieria informatica" selected>Ingenieria informatica</option>
            <option value="Ingenieria indutrial">Ingenieria indutrial</option>
        </select>



    <input type="Submit" name="Unirme">


</form>

</body>
</html>
