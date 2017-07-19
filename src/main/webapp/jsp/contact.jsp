<%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 18/7/17
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Contact and team page</title>
        <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/mainMenu.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/contact.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>

        <div class="active-home">
            <%@include file="/jsp/mainMenu.jsp" %>
        </div>

        <%--<div class="container">--%>
            <%--<div class="row">--%>
                <%--<div class="col-xs-8 col-sm-8 col-md-3 col-lg-3 col-md-offset-2 col-lg-offset-2 col-sm-offset-2 col-xs-offset-2">--%>
                        <%--<div class="card">--%>
                            <%--<img src="../images/avatar.jpg" alt="tomifor" class=" img-contact">--%>
                            <%--<div class="text-center">--%>
                                <%--<h3>Tomas Forman</h3>--%>
                                <%--<p class="title">Co-Founder</p>--%>
                                <%--<p>Estudiante de ingenieria informatica.</p>--%>
                                <%--<p>Universidad Austral</p>--%>
                                <%--<button class="btn btn-contact center-block">Contactar</button>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                <%--</div>--%>

                <%--<div class="col-xs-8 col-sm-3 col-md-3 col-lg-3 col-lg-offset-2 col-xs-offset-2">--%>
                        <%--<div class="card">--%>
                            <%--<img src="../images/avatar.jpg" alt="tomifer" class="img-contact">--%>
                            <%--<div class="text-center">--%>
                                <%--<h3>Tomas Fernandez</h3>--%>
                                <%--<p class="title">Co-Founder</p>--%>
                                <%--<p>Estudiante de ingenieria informatica.</p>--%>
                                <%--<p>Universidad Austral</p>--%>
                                <%--<button class="btn btn-contact center-block">Contactar</button>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

            <div class="container">
                <div class="row">
                    <div class="col-xs-6 col-sm-6 col-md-3 col-lg-3 col-md-offset-2 col-lg-offset-2 col-sm-offset-3 col-xs-offset-3">
                        <div class="card">
                            <img src="../images/avatar.jpg" alt="tomifor" class=" img-contact img-responsive">
                            <div class="text-center">
                                <h3>Tomas Forman</h3>
                                <p class="title">Co-Founder</p>
                                <p>Estudiante de ingenieria informatica.</p>
                                <p>Universidad Austral</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-6 col-sm-6 col-md-3 col-lg-3 col-lg-offset-2 col-sm-offset-3 col-xs-offset-3">
                        <div class="card">
                            <img src="../images/avatar.jpg" alt="tomifer" class="img-contact img-responsive">
                            <div class="text-center">
                                <h3>Tomas Fernandez</h3>
                                <p class="title">Co-Founder</p>
                                <p>Estudiante de ingenieria informatica.</p>
                                <p>Universidad Austral</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <button class="btn btn-contact center-block" onclick="location.href='mailto:australteca@gmail.com?Subject=Consulta';">Contactar</button>
                </div>
            </div>




        <script type="text/javascript" src="<c:url value="/js/jquery-3.2.0.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
    </body>
</html>
