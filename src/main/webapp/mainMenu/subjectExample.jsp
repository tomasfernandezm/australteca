<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="org.australteca.Constants" %>
<%@ page import="static org.australteca.Constants.SUBJECT_NAME_PARAM" %>
<%@ page import="static org.australteca.Constants.NAME_PARAM" %>
<%@ page import="static org.australteca.Constants.LAST_NAME_PARAM" %>
<%@ page import="static org.australteca.Constants.*" %><%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 6/4/17
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale1.0">
    <title><%=Constants.MY_HOME_TITLE%></title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/mainMenu.css" rel="stylesheet" type="text/css">
    <link href="/css/star.css" rel="stylesheet" type="text/css">
    <link href="/css/modalBox.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@include file="/mainMenu/mainMenu.jsp" %>





<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2><%= request.getAttribute(SUBJECT_NAME_PARAM)%></h2>
            <span class="starRating">
              <input id="rating5" type="radio" name="rating" value="5">
              <label for="rating5">5</label>
              <input id="rating4" type="radio" name="rating" value="4">
              <label for="rating4">4</label>
              <input id="rating3" type="radio" name="rating" value="3">
              <label for="rating3">3</label>
              <input id="rating2" type="radio" name="rating" value="2">
              <label for="rating2">2</label>
              <input id="rating1" type="radio" name="rating" value="1">
              <label for="rating1">1</label>
            </span>
            <div class="panel with-nav-tabs panel-default">
                <div class="panel-heading">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab1default" data-toggle="tab">Apuntes</a></li>
                        <li><a href="#tab2default" data-toggle="tab">Profesores</a></li>
                        <li><a href="#tab3default" data-toggle="tab">Comentarios</a></li>
                    </ul>
                </div>
                <div class="panel-body">
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="tab1default">
                            <button type="button" id="myBtn" class="btn btn-primary center-block">Agregar apunte</button>
                            <c:set var="notesListParam" value="<%=SUBJECT_NOTES_LIST%>"/>
                            <c:set var="noteList" value='${requestScope[notesListParam]}' />
                            <c:forEach items="${noteList}" var="note">
                                <div class="col col-md-4">
                                    <a href="#"><c:out value="${note.name}"/></a>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="tab-pane fade" id="tab2default">
                            <% if (request.isUserInRole("user")) { %>
                            <button type="button" id="myBtn2"class="btn btn-primary center-block">Agregar profesor</button>
                            <% } %>
                        </div>
                        <div class="tab-pane fade" id="tab3default">Default 3</div>
                        <div class="tab-pane fade" id="tab4default">Default 4</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- modal box for notes-->

<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <form id="agregar" action="/upload" method="POST" enctype="multipart/form-data">
            <div class="container-fluid">
                <div class="row centered-form  ">
                    <div >
                        <div class="panel panel-default">
                            <div class="modal-header">
                                Agregar apunte
                            </div>
                            <div class="panel-body">
                                <form role="form">
                                    <div class="row">
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <div class="form-group">
                                                <input type="text" name= "<%=NOTE_NAME_PARAM%>" class="form-control input-sm" placeholder="Nombre" required/>
                                                <input type="hidden" name="<%=SUBJECT_NAME_PARAM%>" value="<%=request.getAttribute(SUBJECT_NAME_PARAM)%>"/>
                                            </div>
                                        </div>
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <label class="btn btn-success btn-file form-control">
                                                Buscar <input type="file" style="display: none;" name="fileName">
                                            </label>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <select class="select form-control" name="<%=NOTE_TYPE_PARAM%>">
                                            <option value="agregar" selected>Teoria</option>
                                            <li class="divider"></li>
                                            <option value="agregar" selected>Guia</option>
                                            <li class="divider"></li>
                                            <option value="agregar" selected>Guia resuelta</option>
                                            <li class="divider"></li>
                                            <option value="agregar" selected>Parcial</option>
                                            <li class="divider"></li>
                                            <option value="agregar" selected>Final</option>
                                            <li class="divider"></li>
                                            <option value="agregar" selected>Resumen</option>
                                            <li class="divider"></li>
                                        </select>
                                    </div>
                                    <div class="form-group text-center">

                                    </div>
                                    <div class="form-group">
                                        <input type="submit" value="Compartir!" class="form-control btn btn-info btn-block">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>



<!-- modal box for professor -->

<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <form id="agregar2" action="agregar" method="POST">
            <div class="container-fluid">
                <div class="row centered-form  ">
                    <div >
                        <div class="panel panel-default">
                            <div class="modal-header">
                                Agregar profesor
                            </div>
                            <div class="panel-body">
                                <!-- form -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>



<script type="text/javascript" src="/js/modalBox.js"></script>
<script type="text/javascript" src="/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>

</body>
</html>
