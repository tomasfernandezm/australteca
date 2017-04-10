<%@ page import="org.securityfilter.example.MyConstants" %>
<%@ page import="servlet.ListSubjectServlet" %>
<%@ page import="java.util.List" %><%--
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
    <title><%=MyConstants.MY_HOME_TITLE%></title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/mainMenu.css" rel="stylesheet" type="text/css">
    <link href="/css/subject.css" rel="stylesheet" type="text/css">
    <link href="/css/modalBox.css" rel="stylesheet" type="text/css">
</head>
<body>

    <%@include file="/mainMenu/mainMenu.jsp" %>

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
                            <% List subjectWrapperList = (List)request.getAttribute("subjectWrappers");%>
                            <c:forEach items="${subjectWrapperList}" var="subject">
                                <tr>
                                    <td><c:out value="${subject.getSubject}"/></td>
                                    <td><c:out value="${subject.getFavorite}"/></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <form action="/addSubject" method="post">
                <div class="form-group">
                    <h3>Materia nueva</h3>
                    <div class="col-xs-6 col-sm-6 col-md-6">
                        <label id="subjectName">Nombre:</label>
                        <input class="input-group" type="text" name="subjectName">
                    </div>
                </div>
                <input type="Submit" name="addSubject" class="btn btn-default"  value="Agregar">

                <!-- tendría que ser relegado a un servlet -->
                <% if (request.getParameter("addSubject") != null){%>
                <% if ((boolean) request.getAttribute("operationSuccesfull")) { %>
                <p> Materia agregada!</p>
                <% } else { %>
                <p> Ups! Algun problema ocurrió, intentalo de nuevo!</p>
                <% }
                }%>
            </form>
        </div>
    </div>

    <script script type="text/javascript" src="/js/modalBox.js"></script>
    <script type="text/javascript" src="/js/jquery-3.2.0.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>



</body>
</html>
