<%@ page import="org.australteca.Constants" %><%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 9/5/17
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale1.0">
        <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
        <link href="<c:url value="/css/mainMenu.css"/>" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="active-moderators">
            <%@include file="/mainMenu/mainMenu.jsp" %>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12">
                    <div class="panel panel-default">

                        <div class="panel-heading">
                            <div class="col-lg-10">
                            <h3>Administrador de moderadores</h3>
                            </div>
                            <div class="">
                                <select id="mode" onchange="countryChange(this);" class="form-control">
                                    <option value="postulant">Postulantes</option>
                                    <option value="approved">Aprobados</option>
                                </select>
                            </div>
                        </div>

                        <div id="postulant-table">
                        <table class="table">
                            <thead>
                                <td>Email</td>
                                <td>Materia</td>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>tomas.forman@ing.austral.edu.ar</td>
                                    <td>Fisica 2</td>
                                    <td><button type="submit" class="btn btn-success">Aceptar</button> </td>
                                    <td><button type="submit" class="btn btn-danger">Rechazar</button> </td>
                                </tr>
                            </tbody>
                        </table>
                        </div>

                        <div id="approved-table">
                            <table class="table"  id="dev-table">
                                <thead>
                                <td>Email</td>
                                <td>Materia</td>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>nicolas.madeo@ing.austral.edu.ar</td>
                                    <td>Analisis 4</td>
                                    <td><button type="submit" class="btn btn-danger">Eliminar</button> </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </div>




        <script type="text/javascript" src="<c:url value="/js/jquery-3.2.0.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/moderators.js"/>"></script>
    </body>
</html>
