
<%@ page import="org.australteca.Constants" %><%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 29/3/17
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale1.0">
    <title><%=Constants.MY_HOME_TITLE%></title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/mainMenu.css" rel="stylesheet" type="text/css">
    <link href="/css/home.css" rel="stylesheet" type="text/css">

    <% ;%>
</head>
<body>
    <div class="active-home">
        <%@include file="/mainMenu/mainMenu.jsp" %>
    </div>

    <div class="container">
        <div class="col-sm-12">

            <div class="bs-calltoaction bs-calltoaction-default">
                <div class="row">
                    <div class="col-md-9 cta-contents">
                        <h1 class="cta-title">Mis materias</h1>
                    </div>
                    <div class="col-md-8">
                        <a href="#" class="btn btn-lg btn-block btn-primary">Go for It!</a>
                    </div>
                    <div class="col">
                        <a href="#" class="btn btn-lg btn-block btn-primary">Go for It!</a>
                    </div>
                    <div class="col">
                        <a href="#" class="btn btn-lg btn-block btn-primary">Go for It!</a>
                    </div>
                </div>
            </div>

            <div class="bs-calltoaction bs-calltoaction-primary">
                <div class="row">
                    <div class="col-md-9 cta-contents">
                        <h1 class="cta-title">Mis discuciones</h1>
                        <div class="cta-desc">
                            <p>Describe the action here.</p>
                            <p>Describe the action here.</p>
                            <p>Describe the action here.</p>
                        </div>
                    </div>
                    <div class="col-md-3 cta-button">
                        <a href="#" class="btn btn-lg btn-block btn-primary">Go for It!</a>
                    </div>
                </div>
            </div>

            <div class="bs-calltoaction bs-calltoaction-info">
                <div class="row">
                    <div class="col-md-9 cta-contents">
                        <h1 class="cta-title">Mis trabajos</h1>
                        <div class="cta-desc">
                            <p>Describe the action here.</p>
                            <p>Describe the action here.</p>
                            <p>Describe the action here.</p>
                        </div>
                    </div>
                    <div class="col-md-3 cta-button">
                        <a href="#" class="btn btn-lg btn-block btn-info">Go for It!</a>
                    </div>
                </div>
            </div>

            <div class="bs-calltoaction bs-calltoaction-success">
                <div class="row">
                    <div class="col-md-9 cta-contents">
                        <h1 class="cta-title">Estadisticas</h1>
                        <div class="cta-desc">
                            <p>Describe the action here.</p>
                            <p>Describe the action here.</p>
                            <p>Describe the action here.</p>
                        </div>
                    </div>
                    <div class="col-md-3 cta-button">
                        <a href="#" class="btn btn-lg btn-block btn-info">Go for It!</a>
                    </div>
                </div>
            </div>


        </div>
    </div>


    <script type="text/javascript" src="/js/jquery-3.2.0.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/home.js"></script>
</body>
</html>
