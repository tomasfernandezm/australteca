<%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 10/4/17
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User settings</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/mainMenu.css" rel="stylesheet" type="text/css">
</head>
<body>

<%@include file="/mainMenu/mainMenu.jsp" %>


<div class="container">
    <h1>Editar perfil</h1>
    <hr>
    <div class="row">
        <!-- left column -->
        <div class="col-md-3">
            <div class="text-center">
                <img src="//placehold.it/100" class="avatar img-circle" alt="avatar">
                <label class="btn btn-default btn-file">
                    Browse <input type="file" style="display: none;">
                </label>
            </div>
        </div>

        <!-- edit form column -->
        <div class="col-md-9 personal-info">

            <h3>Informacion personal</h3>

            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-lg-3 control-label">Nombre:</label>
                    <div class="col-lg-8">
                        <input class="form-control" type="text" name="name">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Apellido:</label>
                    <div class="col-lg-8">
                        <input class="form-control" type="text" name="lastName">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Email:</label>
                    <div class="col-lg-8">
                        <input class="form-control" type="email" pattern="^[A-Za-z0-9._%-]+@ing.austral.edu.ar" name="email">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Password:</label>
                    <div class="col-md-8">
                        <input class="form-control" type="password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">New Password:</label>
                    <div class="col-md-8">
                        <input class="form-control" type="password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Confirmar contrasena nueva:</label>
                    <div class="col-md-8">
                        <input class="form-control" type="password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Cambiar carrera:</label>
                    <div class="col-md-8">
                        <select class="select form-control" name="career">
                            <option value="Ingenieria informatica" selected>Ingenieria informatica</option>
                            <li class="divider"></li>
                            <option value="Ingenieria indutrial">Ingenieria indutrial</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label"></label>
                    <div class="col-md-8">
                        <input type="button" class="btn btn-primary" value="Save Changes">
                        <span></span>
                        <input type="reset" class="btn btn-default" value="Cancel">
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>


<script type="text/javascript" src="/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
</body>
</html>
