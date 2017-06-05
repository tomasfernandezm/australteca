<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="org.australteca.servlet.subject.SubjectListServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="org.australteca.Constants" %>
<%@ page import="javax.security.auth.login.Configuration" %>
<%@ page import="org.australteca.servlet.command.enums.SubjectEnums" %>
<%@ page import="static org.australteca.Constants.REMOVE_FAVORITE" %>
<%@ page import="static org.australteca.Constants.MAKE_FAVORITE" %>
<%@ page import="static org.australteca.Constants.*" %>
<%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 29/3/17
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale1.0">
    <title><%=Constants.MY_HOME_TITLE%></title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/mainMenu.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/subject.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/modalBox.css"/>" rel="stylesheet" type="text/css">

</head>
<body>
    <div class="active-subject">
        <%@include file="/mainMenu/mainMenu.jsp" %>
    </div>

    <!--------- Add subject modal box ---------->

    <div id="addSubjectBox" class="modal">
        <div class="modal-content">
            <span onclick="closeModal(document.getElementById('addSubjectBox'))" class="close">&times;</span>
            <form action="<c:url value="/addSubject"/>" method="post">
                <div class="form-group">
                    <div class="modal-header">
                    <h3>Materia nueva</h3>
                    </div>
                    <div class="row modal-body">
                        <div class="col-sm-2">
                            <label id="subjectName">Nombre:</label>
                        </div>
                        <div class="col-sm-2">
                            <input class="input-group" type="text" name="<%=SUBJECT_NAME_PARAM%>" required>
                        </div>
                        <div class="pull-right">
                        <input type="Submit" name="addSubject" class="btn btn-primary"  value="Agregar">
                        </div>
                    </div>
                </div>
                <% if (request.getParameter("addSubject") != null){%>

                <% if ((boolean) request.getAttribute(OPERATION_SUCCESFUL_PARAM)) { %>
                    <p> Materia agregada!</p>
                <% } else { %>
                    <p> Ups! Algun problema ocurrió, intentalo de nuevo!</p>
                <% }
                response.sendRedirect("/listSubjects");
                }%>
            </form>
        </div>
    </div>


    <!------- List of subjects -------->

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1>Materias</h1>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <% if (request.isUserInRole("admin")) { %>
                        <button type="button" class="standardButton btn btn-default btn-xs" onclick="modalBox(document.getElementById('addSubjectBox'))">
                            <i class="glyphicon glyphicon-plus"></i></button>
                        <% } %>
                        <div class="pull-right">
							<span class="clickable filter" data-toggle="tooltip" data-container="body">
								<i class="glyphicon glyphicon-search"></i>
							</span>
                        </div>
                    </div>

                    <div class="panel-body">
                        <input type="text" class="form-control" id="dev-table-filter" data-action="filter" data-filters="#dev-table" placeholder="Buscar" />
                    </div>
                        <table class="table"  id="dev-table">
                            <thead>
                                <td>Nombre</td>
                                <td>Favorita</td>
                                <% if (request.isUserInRole("admin")) { %>
                                <td>Eliminar</td>
                                <% } %>
                            </thead>
                            <tbody>
                                <c:set var="subjectWrapperList" value='${requestScope["subjectWrappers"]}' />
                                <c:forEach items="${subjectWrapperList}" var="subjectWrapper">
                                    <tr>
                                        <td><a href="${pageContext.request.contextPath}/postSubject?<%=SUBJECT_NAME_PARAM%>=${subjectWrapper.subject.subjectName}">
                                            <c:out value="${subjectWrapper.subject.subjectName}"/></a>
                                        </td>
                                         <td>
                                             <%--<label class="checkbox"><i class="glyphicon glyphicon-heart"></i>--%>
                                            <div class="checkbox_wrapper">
                                                 <input class="checkbox" id="subject_favorite" type="checkbox" value="${subjectWrapper.subject.subjectName}" onclick=changeFavorite(this.value)
                                                     <c:if test="${subjectWrapper.favorite}">checked</c:if>>
                                                 <%--<label></label>--%>
                                            </div>
                                         </td>
                                        <% if (request.isUserInRole("admin")) { %>
                                        <td>
                                            <form action="<c:url value="/subjectDelete"/>" method="post">
                                                <button type="submit" name="<%=SUBJECT_NAME_PARAM%>" value="<c:out value="${subjectWrapper.subject.subjectName}" />" class="standardButton btn btn-default btn-xs"><i class="glyphicon glyphicon-trash"></i></button>
                                            </form>
                                        </td>
                                        <% } %>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                </div>
            </div>
        </div>
    </div>




    <script type="text/javascript" src="<c:url value="/js/modalBox.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery-3.2.0.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/subjects.js"/>"></script>



</body>
</html>
