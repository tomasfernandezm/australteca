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
    <meta http-equiv="content-language" content="es">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale1.0">
    <title><%=Constants.MY_HOME_TITLE%></title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/mainMenu.css" rel="stylesheet" type="text/css">
    <link href="/css/star.css" rel="stylesheet" type="text/css">
    <link href="/css/modalBox.css" rel="stylesheet" type="text/css">
    <link href="/css/subjectExample.css" rel="stylesheet" type="text/css">
    <link href="/css/writeBox.css" rel="stylesheet" type="text/css">
    <link href="/css/comment.css" rel="stylesheet" type="text/css">
    <link href="/css/professor.css" rel="stylesheet" type="text/css">


</head>
<body>
<%@include file="/mainMenu/mainMenu.jsp" %>


<div class="container">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <h2 id = "subjectName-h2"><%= request.getAttribute(SUBJECT_NAME_PARAM)%></h2>
            </div>
            <div class="pull-right">
            <div id= "subject_score" class="col-lg-3 col-md-3 col-sm-3 col-xs-4">
                <h2 id="subject_score_h2"><%= request.getAttribute(SUBJECT_SCORE)%></h2>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-8">
                <span class="starRating">
                  <input id="rating5" type="radio" name="rating" value="5" onclick="changeRating('<%=request.getAttribute(SUBJECT_NAME_PARAM)%>',this.value)">
                  <label for="rating5">5</label>
                  <input id="rating4" type="radio" name="rating" value="4" onclick="changeRating('<%=request.getAttribute(SUBJECT_NAME_PARAM)%>',this.value)">
                  <label for="rating4">4</label>
                  <input id="rating3" type="radio" name="rating" value="3" onclick="changeRating('<%=request.getAttribute(SUBJECT_NAME_PARAM)%>',this.value)">
                  <label for="rating3">3</label>
                  <input id="rating2" type="radio" name="rating" value="2" onclick="changeRating('<%=request.getAttribute(SUBJECT_NAME_PARAM)%>',this.value)">
                  <label for="rating2">2</label>
                  <input id="rating1" type="radio" name="rating" value="1" onclick="changeRating('<%=request.getAttribute(SUBJECT_NAME_PARAM)%>',this.value)">
                  <label for="rating1">1</label>
                </span>
            </div>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel with-nav-tabs panel-default">
                <div class="panel-heading">
                    <ul class="nav nav-tabs" id="myTab">
                        <li class="active"><a href="#tab1default" data-toggle="tab">Apuntes</a></li>
                        <li><a href="#tab2default" data-toggle="tab">Profesores</a></li>
                        <li><a href="#tab3default" data-toggle="tab">Comentarios</a></li>
                        <% if (request.isUserInRole("user")) { %>
                        <button type="submit"  class="btn btn-success pull-right" onclick="addModeratorPostulation('<%=request.getRemoteUser()%>','<%=request.getAttribute(SUBJECT_NAME_PARAM)%>')">Aplicar a moderador</button>
                        <% } %>
                        <!---- agregar botones moderador ------>



                    </ul>
                </div>
                <div class="panel-body">
                    <div class="tab-content">
                        <!---------------- Notes tab ----------------->
                        <div class="tab-pane fade in active" id="tab1default">
                            <button type="button" onclick="modalBox(document.getElementById('noteModal'))" id="addNoteBtn" class="btn btn-primary center-block">Agregar apunte</button>
                            <div class="pre-scrollable" data-offset="50">
                                <table class="table"  id="dev-table">
                                    <thead>
                                        <td>Nombre</td>
                                        <td></td>
                                        <td>Tipo</td>
                                        <td>Fecha</td>
                                        <td>Descargas</td>
                                        <td>Subido por</td>
                                        <% if (request.isUserInRole("user")) { %>
                                            <td></td>
                                        <% } %>
                                    </thead>

                                    <c:set var="notesListParam" value="<%=SUBJECT_NOTES_LIST%>"/>
                                    <c:set var="noteList" value='${requestScope[notesListParam]}' />
                                    <c:set var="subjectName" value="<%=SUBJECT_NAME_PARAM%>"/>
                                    <c:forEach items="${noteList}" var="note">
                                        <tr>
                                            <td><c:out value="${note.name}"/></td>
                                            <form action="/noteDownload" method="post">
                                                <td><button type="submit" class="btn downloadButton"><i class="glyphicon glyphicon-download"></i></button> </td>
                                                <input type="hidden" name="<%=Constants.NOTE_ID_PARAM%>" value="${note.id}">
                                                <input type="hidden" name="<%=Constants.NOTE_NAME_PARAM%>" value="${note.name}">
                                                <input type="hidden" name="<%=Constants.NOTE_FORMAT_PARAM%>" value="${note.format}">
                                            </form>
                                            <td><c:out value="${note.type}"/></td>
                                            <td><abbr class="timeago" title="<c:out value="${note.getFormatDate()}"/>"></abbr></td>
                                            <td><c:out value="${note.downloads}"/></td>
                                            <td><c:out value="${note.author.firstName} ${note.author.lastName}"/></td>
                                            <% if (request.isUserInRole("user")) { %>
                                            <form action="/noteDelete" method="post">
                                                <td><button type="submit" class="btn trashButton"><i class="glyphicon glyphicon-trash"></i></button></td>
                                                <input type="hidden" name="<%=Constants.NOTE_ID_PARAM%>" value="${note.id}">
                                                <input type="hidden" name="<%=SUBJECT_NAME_PARAM%>" value="<%=request.getAttribute(SUBJECT_NAME_PARAM)%>"/>

                                            </form>
                                            <% } %>

                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>

                        <!----------- PROFESSOR tab ----------->
                        <div class="tab-pane fade" id="tab2default">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-8 col-md-8 col-sm-8 col-lg-offset-4 col-md-offset-4 col-sm-offset-4">
                                        <% if (request.isUserInRole("admin")) { %>

                                            <div class="btn-group btn-professor">
                                                <button type="button" onclick="modalBox(document.getElementById('loadProfessorModal'))" id="loadProfessor"class="btn btn-primary">Cargar profesor</button>
                                                <button type="button" onclick="modalBox(document.getElementById('addProfessorModal'))" id="addProfessor"class="btn btn-primary">Agregar profesor</button>
                                            </div>
                                        <% } %>
                                    </div>
                                </div>
                            </div>


                            <!-- for each de profesores -->

                            <c:set var="professorWrapperListParam" value="<%=Constants.SUBJECT_PROFESSOR_WRAPPER_LIST%>"/>
                            <c:set var="professorWrapperList" value="${requestScope[professorWrapperListParam]}"/>
                            <c:forEach items="${professorWrapperList}" var="professorWrapper">
                                <c:if test="${professorWrapper.favorite}">
                                    <div class="row" id="${professorWrapper.professor.email}">
                                        <div class="boxContent">
                                            <div class="col col-md-4">
                                                <h3><c:out value="${professorWrapper.professor.firstName} ${professorWrapper.professor.lastName}"/> </h3>
                                                <p><c:out value="${professorWrapper.professor.email}"/></p>
                                                <p><c:out value="${professorWrapper.professor.information}"/></p>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>


                        <!------------ Comments tab ------------>
                        <div class="tab-pane fade" id="tab3default">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-11 col-md-11 col-sm-11">
                                        <div class="widget-area no-padding blank">
                                            <div class="status-upload">
                                                <!----- writting box ------->
                                                    <textarea id="textarea" name="<%=Constants.COMMENTARY%>" placeholder="Que estas pensando?" ></textarea>
                                                    <button type="submit" class="btn btn-success green" onclick="addComment('${requestScope[subjectName]}','<%=request.getRemoteUser()%>')"><i class="glyphicon glyphicon-share"></i>Compartir</button>
                                                </form>
                                                <div id = "article-div" class="col-xs-12"><hr></div>

                                                <!------- List of comments ------->
                                                    <c:set var="commentaryListParam" value="<%=SUBJECT_COMMENTARY_LIST%>"/>
                                                    <c:set var="commentaryList" value='${requestScope[commentaryListParam]}' />
                                                    <c:set var="subjectName" value="<%=SUBJECT_NAME_PARAM%>"/>
                                                    <c:forEach items="${commentaryList}" var="commentary" varStatus="loop">
                                                    <article id="commentary${loop.count}" class="row">
                                                        <div class="col-lg-2 col-md-2 col-sm-2 hidden-xs">
                                                            <figure class="thumbnail">

                                                                <img src="/userPostPhoto?<%=Constants.USER_EMAIL_PARAM%>=${commentary.author.email}" onerror="if (this.src != 'images/avatar.jpg') this.src = 'images/avatar.jpg';"class="img-responsive avatar img-circle" alt="avatar">

                                                                <figcaption class="text-center"><c:out value="${commentary.author.firstName}"/></figcaption>
                                                            </figure>
                                                        </div>
                                                        <div class="col-md-9 col-sm-9 col-xs-9">
                                                            <div class="panel panel-default arrow left">
                                                                <div class="panel-body">
                                                                    <header class="text-left"></header>
                                                                        <div class="comment-user"><i class="glyphicon glyphicon-user"></i><c:out value="${commentary.author.email}"/></div>
                                                                        <abbr class="timeago" title="<c:out value="${commentary.getFormatDate2()}"/>"></abbr>
                                                                    </header>
                                                                    <c:set var="remoteUser" value="<%=request.getRemoteUser()%>"/>
                                                                    <c:if test="${commentary.author.email == remoteUser}">

                                                                    <button type="submit" class="btn pull-right remove" onclick="removeComment('${commentary.id}','${commentary.subject.subjectName}','commentary${loop.count}')"><i class="glyphicon glyphicon-remove"></i></button>
                                                                    </c:if>
                                                                    <div class="comment-post">
                                                                        <p>
                                                                            <c:out value="${commentary.commentary}"/>
                                                                        </p>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </article>
                                                </c:forEach>
                                                    <!-------- finish -------->
                                                </section>

                                            </div><!-- Status Upload  -->
                                        </div><!-- Widget Area -->
                                    </div>
                                </div>
                            </div>
                        </div>



                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


<!-- modal box for notes-->

<div id="noteModal" class="modal">
    <div class="modal-content">
        <span onclick="closeModal(document.getElementById('noteModal'))" class="close">&times;</span>
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
                                                <input type="hidden" name="<%=NOTE_FORMAT_PARAM%>" value="txt">
                                            </div>
                                        </div>
                                        <div class="col-xs-6 col-sm-6 col-md-6">
                                            <label class="btn btn-success btn-file form-control">
                                                Buscar <input type="file" id="filePreview" style="display: none;" name="fileName">
                                            </label>
                                        </div>
                                    </div>
                                    <div class="filePreview">
                                        <img id="imagePreview" src="/images/file.svg" alt="filePreview" class="fileImg">
                                    </div>


                                    <div class="form-group">
                                        <select class="select form-control" name="<%=NOTE_TYPE_PARAM%>">
                                            <option value="<%=NOTE_TYPE_TEORIA%>" selected>Teoria</option>
                                            <li class="divider"></li>
                                            <option value="<%=NOTE_TYPE_GUIA%>" selected>Guia</option>
                                            <li class="divider"></li>
                                            <option value="<%=NOTE_TYPE_GUIA_RESUELTA%>" selected>Guia resuelta</option>
                                            <li class="divider"></li>
                                            <option value="<%=NOTE_TYPE_PARCIAL%>" selected>Parcial</option>
                                            <li class="divider"></li>
                                            <option value="<%=NOTE_TYPE_FINAL%>" selected>Final</option>
                                            <li class="divider"></li>
                                            <option value="<%=NOTE_TYPE_RESUMEN%>" selected>Resumen</option>
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



<!-- modal box for load professor -->

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
                                <form action="<c:url value="/loadProfessor"/>" class="form-horizontal"  method="POST" role="form">
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
                                            <input class="form-control" type="text" name="<%=PROFESSOR_INFORMATION_PARAM%>" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-3 control-label"></label>
                                        <div class="col-md-8 col-md-offset-10">
                                            <input type="submit" class="btn btn-primary" value="Agregar">
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


<!-- modal box add professor -->

<div id="addProfessorModal" class="modal">
    <div class="modal-content">
        <span onclick="closeModal(document.getElementById('addProfessorModal'))" class="close">&times;</span>

    <div class="container-fluid">
        <div class="row">
            <div class="panel panel-default">
                <div class="modal-header">
                    Profesores
                </div>
                <div class="modal-body">
                    <!-------- lista de profesores que se pueden agregar (cambie los nombres pero chequea) --------->
                    <table class="table">
                        <thead>
                        <td>Nombre</td>
                        </thead>
                        <!------ foreach si esta en la materia cambiar a boton eliminar ------->
                        <tbody>
                            <c:forEach items="${professorWrapperList}" var="professorWrapper" varStatus="loop">
                                <tr id="professorList${loop.count}">
                                    <td><c:out value="${professorWrapper.professor.firstName} ${professorWrapper.professor.lastName}"/> </td>
                                    <!--- boton agregar ---->
                                    <%--<c:if test="${!professorWrapper.belongsInSubject}">--%>
                                    <td><button id="${professorWrapper.professor.email}button" class="btn btn-success" type="submit" onclick="addProfessorToSubject('${professorWrapper.professor.id}','${requestScope[subjectName]}')">Agregar</button></td>
                                    <%--</c:if>--%>
                                    <!--- boton eliminar ---->
                                    <%--<c:if test="${professorWrapper.belongsInSubject}">--%>
                                    <td><button id="${professorWrapper.professor.email}button"class="btn btn-danger" type="submit" onclick="removeProfessorFromSubject('${professorWrapper.professor.id}','${requestScope[subjectName]}')">Eliminar</button></td>
                                    <%--</c:if>--%>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</div>



<script type="text/javascript" src="<c:url value="/js/modalBox.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-3.2.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/tootip.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-timeago.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/filePreview.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/subjectExample.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/timeago.js"/>"></script>


</body>
</html>
