<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="org.australteca.servlet.ListSubjectServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="org.australteca.Constants" %>
<%@ page import="javax.security.auth.login.Configuration" %>
<%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 29/3/17
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title><%=Constants.MY_HOME_TITLE%></title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/mainMenu.css" rel="stylesheet" type="text/css">
    <link href="/css/subject.css" rel="stylesheet" type="text/css">
    <link href="/css/modalBox.css" rel="stylesheet" type="text/css">

</head>
<body>
    <div class="active-subject">
        <%@include file="/mainMenu/mainMenu.jsp" %>
    </div>

    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <form action="/addSubject" method="post">
                <div class="form-group">
                    <h3>Materia nueva</h3>
                    <div class="col-xs-6 col-sm-6 col-md-6">
                        <label id="subjectName">Nombre:</label>
                        <input class="input-group" type="text" name="<%=Constants.SUBJECT_NAME_PARAM%>" required>
                    </div>
                </div>
                <input type="Submit" name="addSubject" class="btn btn-default"  value="Agregar">

                <!-- hay que emprolijarlo -->
                <% if (request.getParameter("addSubject") != null){%>

                <% if ((boolean) request.getAttribute(Constants.OPERATION_SUCCESFUL_PARAM)) { %>
                <p> Materia agregada!</p>
                <% } else { %>
                <p> Ups! Algun problema ocurrió, intentalo de nuevo!</p>
                <% }
                response.sendRedirect("/listSubjects");
                }%>
            </form>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Materias <button class="btn btn-default btn-xs pull-right" id="myBtn">Agregar materia</button>
                    </div>
                    <div class="panel-body">
                        <div class="container">
                            <div class="row">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <div id="imaginary_container">
                                        <div class="input-group stylish-input-group">
                                            <input type="text" class="form-control" >
                                            <span class="input-group-addon">
                                                <button type="submit">
                                                    <span>Buscar</span>
                                                </button>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="pre-scrollable" data-offset="50">
                        <table class="table">
                            <thead>
                            <td>Nombre</td>
                            <td>Favorita</td>
                            </thead>
                            <c:set var="subjectWrapperList" value='${requestScope["subjectWrappers"]}' />
                            <c:forEach items="${subjectWrapperList}" var="subject">
                                <tr>
                                    <td><c:out value="${subject.subject.subjectName}"/></td>
                                    <td><c:out value="${subject.favorite}"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>



    <script type="text/javascript" src="/js/modalBox.js"></script>
    <script type="text/javascript" src="/js/jquery-3.2.0.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>



</body>
</html>
