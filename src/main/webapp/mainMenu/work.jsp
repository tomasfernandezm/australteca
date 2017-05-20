<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.australteca.Constants" %><%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 29/3/17
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale1.0">
    <title><%=Constants.MY_HOME_TITLE%></title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/mainMenu.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="active-work">
        <%@include file="/mainMenu/mainMenu.jsp" %>
    </div>



<div class="panel-heading">
    <ul class="nav nav-pills">
        <li role="presentation" class="active" data-toggle="tab" ><a href="#workPill">Trabajo</a></li>
        <li role="presentation"><a href="#investigationPill">Investigacion</a></li>
    </ul>
</div>
    <div class="panel-body">
            <div class="tab-pane fade in active" id="tab1default1">
                <div class="" id="workPill">
                    <h2>HOLA</h2>
                </div>
            </div>
            <div class="tab-pane fade in active" id="tab1default2">
                <div id="investigationPill">
                    <h2>CHAU</h2>
                </div>
            </div>
    </div>



    <script type="text/javascript" src="/js/jquery-3.2.0.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>


</body>
</html>
