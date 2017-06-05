
<%@ page import="org.australteca.Constants" %><%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 1/4/17
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale1.0">
    <title><%=Constants.MY_HOME_TITLE%></title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/mainMenu.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="active-discussions">
        <%@include file="/mainMenu/mainMenu.jsp" %>
    </div>


    <script type="text/javascript" src="/js/jquery-3.2.0.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>


</body>
</html>
