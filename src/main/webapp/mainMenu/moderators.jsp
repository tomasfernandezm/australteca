<%@ page import="org.australteca.Constants" %>
<%@ page import="static org.australteca.Constants.ACCEPTED_LIST" %>
<%@ page import="static org.australteca.Constants.WAITING_LIST" %>
<%@ page import="static org.australteca.Constants.PROFESSOR_LAST_NAME_PARAM" %>
<%@ page import="static org.australteca.Constants.*" %><%--
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
        <link href="<c:url value="/css/modalBox.css"/>" rel="stylesheet" type="text/css">
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
                            <div>
                                <select id="mode" onchange="countryChange(this);" class="form-control">
                                    <option value="postulant">Postulantes</option>
                                    <option value="approved">Aprobados</option>
                                </select>
                            </div>
                            <div class="pull-right">
                                <span class="clickable filter" data-toggle="tooltip" data-container="body">
                                </span>
                            </div>
                        </div>
                        <div id="postulant-table-div">
                            <div class="panel-body">
                                <input type="text" class="form-control" id="dev-table-filter-1" data-action="filter" data-filters="#postulant-table" placeholder="Buscar" />
                            </div>
                            <table class="table" id="postulant-table">
                                <thead>
                                    <td>Email</td>
                                    <td>Materia</td>
                                </thead>
                                <tbody>
                                <c:set var="waitingListParam" value="<%=WAITING_LIST%>"/>
                                <c:set var="waitingList" value='${requestScope[waitingListParam]}' />
                                <c:forEach items="${waitingList}" var="postulation" varStatus="loop">
                                    <tr id="postulant${loop.count}">
                                        <td><c:out value="${postulation.user.firstName}" /></td>
                                        <td><c:out value="${postulation.subject.subjectName}"/></td>
                                        <td><button type="submit" class="btn btn-success" onclick="acceptAplication('${postulation.user.email}','${postulation.subject.subjectName}','postulant${loop.count}')">Aceptar</button> </td>
                                        <td><button type="submit" class="btn btn-danger" onclick="eliminateAplication('${postulation.user.email}','${postulation.subject.subjectName}','postulant${loop.count}')">Rechazar</button> </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <div id="approved-table-div">
                            <div class="panel-body">
                                <input type="text" class="form-control" id="dev-table-filter-2" data-action="filter" data-filters="#approved-table" placeholder="Buscar" />
                            </div>
                            <table class="table"  id="approved-table">
                                <thead>
                                <td>Email</td>
                                <td>Materia</td>
                                </thead>
                                <tbody>
                                    <c:set var="acceptedListParam" value="<%=ACCEPTED_LIST%>"/>
                                    <c:set var="acceptedList" value='${requestScope[acceptedListParam]}' />
                                    <c:forEach items="${acceptedList}" var="moderator" varStatus="loop">
                                <tr id="accepted${loop.count}">
                                    <td><c:out value="${moderator.user.firstName}" /></td>
                                    <td><c:out value="${moderator.subject.subjectName}"/></td>
                                    <td><button type="submit" class="btn btn-danger" onclick="eliminateAplication('${moderator.user.email}','${moderator.subject.subjectName}','accepted${loop.count}')">Eliminar</button> </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">

                                <h3>Administrador de Profesores</h3>


                                <button type="button" class="btn btn-primary" onclick="modalBox(document.getElementById('loadProfessorModal'))">Cargar profesor</button>



                            <div class="pull-right">
                                <span class="clickable filter" data-toggle="tooltip" data-container="body">
                                </span>
                            </div>
                        </div>
                        <div class="panel-body">
                            <input type="text" class="form-control" id="dev-table-filter-3" data-action="filter" data-filters="#professor-table" placeholder="Buscar" />
                        </div>
                            <div id="professor-table-div">
                                <table class="table" id="professor-table">
                                    <thead>
                                        <td>Nombre</td>
                                        <td>Email</td>
                                        <td></td>
                                    </thead>
                                    <tbody>

                                            <!---- ARREGLAR ESTOOOOO------>
                                            <c:set var="professorListParam" value="<%=Constants.PROFESSOR_LIST%>"/>
                                            <c:set var="professorList" value="${requestScope[professorListParam]}"/>
                                            <c:forEach items="${professorList}" var="professor" varStatus="loop">
                                                <tr id="professor${loop.count}">
                                                <td><c:out value="${professor.firstName} ${professor.lastName}"/></td>
                                                <td><c:out value="${professor.email}"/></td>
                                                <!------ boton eliminar de la base de datos ------>
                                                <td><button type="submit" class="btn btn-danger" onclick="removeProfessor('${professor.id}','professor${loop.count}')">Eliminar</button> </td>
                                                </tr>
                                            </c:forEach>
                                    </tbody>
                                </table>
                            </div>

                    </div>
                </div>
            </div>
        </div>

<!-------- modal box load professor -------->
        <div id="loadProfessorModal" class="modal">
            <div class="modal-content">
                <span onclick="closeModal(document.getElementById('loadProfessorModal'))" class="close">&times;</span>
                <div class="container-fluid">
                    <div class="row">
                        <div>
                            <div class="panel panel-default">
                                <div class="modal-header">
                                    Cargar profesor
                                </div>
                                <div class="panel-body">
                                    <form id="addProfessorForm" class="form-horizontal">
                                        <input id="subjectName" type="hidden" name="<%=Constants.SUBJECT_NAME_PARAM%>" value="<%=request.getParameter(Constants.SUBJECT_NAME_PARAM)%>" required>
                                        <div class="form-group">
                                            <label class="col-lg-3 control-label">Nombre:</label>
                                            <div class="col-lg-8">
                                                <input class="form-control" type="text" name="<%=PROFESSOR_NAME_PARAM%>" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-3 control-label">Apellido:</label>
                                            <div class="col-lg-8">
                                                <input class="form-control" type="text" name="<%=PROFESSOR_LAST_NAME_PARAM%>" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-lg-3 control-label">Email:</label>
                                            <div class="col-lg-8">
                                                <input class="form-control" type="email" pattern="^[A-Za-z0-9._%-]+@ing.austral.edu.ar" name="<%=PROFESSOR_EMAIL_PARAM%>" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Informacion:</label>
                                            <div class="col-md-8">
                                                <textarea class="form-control" type="text" name="<%=PROFESSOR_INFORMATION_PARAM%>" required></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label"></label>
                                            <div class="col-md-8 col-md-offset-10">
                                                <button type="submit" class="btn btn-primary" value="Agregar" onclick="addProfessor()">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <script type="text/javascript" src="<c:url value="/js/jquery-3.2.0.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/moderators.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/modalBox.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/search.js"/>"></script>
    </body>
</html>
