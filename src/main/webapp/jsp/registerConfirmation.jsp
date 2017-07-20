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
    <meta name="viewport" content="width=device-width, initial-scale1.0">
    <title>org.australteca.dao.UserDao confirmation</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../css/registerConfirmation.css" rel="stylesheet" type="text/css">
</head>
<body>


<%@ page import="org.australteca.Constants" %>
<%@ page import="static org.australteca.Constants.STATUS" %>



<div class="container-fluid">
    <div class="row centered-form  ">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="box">
                <%int status = (Integer) request.getAttribute(STATUS);%>
                <%if (status == 0) { %>
                <div class="box-icon-correct">
                    <i class="glyphicon glyphicon-ok"></i>
                </div>
                <div class="info">
                    <h4 class="text-center">Te has registrado correctamente!</h4>
                    <p>Gracias por unirte a la comunidad! Esperamos que puedas aprovecharla al maximo!</p>
                    <a href="/jsp/loginForm.jsp" class="btn">Iniciar sesion</a>
                </div>
                <%} else { %>
                <div class="box-icon-incorrect">
                    <i class="glyphicon glyphicon-remove"></i>
                </div>
                <div class="info">
                    <%if (status == 1){ %>
                    <h4 class="text-center">Ups! algo ocurrio!</h4>
                    <p>Las contraseñas no coinciden</p>
                    <p>Intentalo de nuevo</p>
                    <a href="loginForm.jsp" class="btn">Volver</a>
                    <%}else{ %>
                    <h4 class="text-center">Ups! algo ocurrio!</h4>
                    <p>Intentalo de nuevo</p>
                    <a href="/jsp/loginForm.jsp" class="btn">Volver</a>
                    <%}%>
                </div>
                <%} %>
            </div>
        </div>
    </div>
</div>
</body>
</html>
