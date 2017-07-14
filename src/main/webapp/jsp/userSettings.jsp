<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.australteca.Constants" %>
<%@ page import="static org.australteca.Constants.STATUS" %>
<%@ page import="static org.australteca.Constants.NAME_PARAM" %>
<%@ page import="static org.australteca.Constants.LAST_NAME_PARAM" %>
<%@ page import="static org.australteca.Constants.*" %><%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 10/4/17
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale1.0">
    <title>User settings</title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/mainMenu.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/userSettings.css"/>" rel="stylesheet" type="text/css">
</head>
<body>

<%@include file="/jsp/mainMenu.jsp" %>


<div class="container">
    <h1>Editar perfil</h1>
    <hr>
    <div class="row">
        <!-- left column -->
        <div class="col-md-3">
            <div class="text-center">
             <!--   <object data="/images/avatar.jpg" class="img-circle" type="image/png"> -->
                    <img src="/servlet/userPostPhoto?<%=Constants.USER_EMAIL_PARAM%>=<%=request.getRemoteUser()%>" onerror="if (this.src != '/images/avatar.jpg') this.src = '/images/avatar.jpg';"class="avatar img-circle" alt="avatar">
            <!--    </object> -->
                <form action="<c:url value="/servlet/uploadPhoto"/>" method="post" enctype="multipart/form-data">
                    <label class="btn btn-default btn-file">
                        Subir Foto <input id="fileField" type="file" style="display: none;" name="photo">
                    </label>
                </form>
            </div>
        </div>

        <!-- edit form column -->
        <div class="col-md-6 personal-info">

            <h3>Informacion personal</h3>

            <form class="form-horizontal" action="<c:url value="/servlet/userModification"/>" method="post" role="form">
                <div class="form-group">
                    <label class="col-lg-3 control-label">Nombre:</label>
                    <div class="col-lg-8">
                        <input class="form-control" type="text" name="<%= NAME_PARAM%>" placeholder="<%=request.getAttribute(NAME_PARAM)%>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Apellido:</label>
                    <div class="col-lg-8">
                        <input class="form-control" type="text" name="<%= LAST_NAME_PARAM%>" placeholder="<%=request.getAttribute(LAST_NAME_PARAM)%>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Email:</label>
                    <div class="col-lg-8">
                        <input class="form-control" type="email" pattern="^[A-Za-z0-9._%-]+@ing.austral.edu.ar" name="<%=EMAIL_PARAM%>" placeholder="<%=request.getAttribute(EMAIL_PARAM)%>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Nueva contrase&ntilde;a:</label>
                    <div class="col-md-8">
                        <input class="form-control" type="password" name="<%=PASSWORD_PARAM%>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Confirmar contrase&ntilde;a:</label>
                    <div class="col-md-8">
                        <input class="form-control" type="password" name="<%= PASSWORD_CONFIRMATION_PARAM%>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Carrera:</label>
                    <div class="col-md-8">
                        <select class="select form-control" name="<%=CAREER_PARAM%>">
                            <option value="Ingenieria informatica" selected>Ingenieria informatica</option>
                            <li class="divider"></li>
                            <option value="Ingenieria indutrial">Ingenieria indutrial</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label"></label>
                    <div class="col-md-8">
                        <input type="submit" class="btn btn-primary" value="Guardar">
                        <input type="reset" class="btn btn-default" value="Reset">
                    </div>
                </div>
            </form>
            <div class="form-group">
                <label class="col-md-3 control-label"></label>
                <div class="col-md-8">
                    <form action="/userDelete" method="post">
                        <input type="submit" class="btn btn-danger btn-block" value="Eliminar cuenta">
                    </form>
                </div>
            </div>

        </div>
        <% Integer status = (Integer) request.getAttribute(STATUS);%>
        <% if(status != null && status == 0) {
            // mostrar mensaje
            response.sendRedirect("/servlet/logout.jsp");
        }%>
    </div>
</div>


<script type="text/javascript" src="<c:url value="/js/jquery-3.2.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/userSettings.js"/>"></script>
</body>
</html>
