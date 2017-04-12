
<%@ page import="org.australteca.Constants" %><%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 29/3/17
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title><%=Constants.MY_HOME_TITLE%></title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/mainMenu.css" rel="stylesheet" type="text/css">

    <% ;%>
</head>
<body>
    <div class="active-home">
        <%@include file="/mainMenu/mainMenu.jsp" %>
    </div>

    <script type="text/javascript" src="/js/jquery-3.2.0.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
</body>
</html>
