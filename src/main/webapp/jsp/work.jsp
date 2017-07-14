<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.australteca.Constants" %>
<%@ page import="static org.australteca.Constants.SUBJECT_NAME_PARAM" %><%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 29/3/17
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale1.0">
    <title><%=Constants.MY_HOME_TITLE%></title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/simplemde.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/mainMenu.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/work.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="active-work">
        <%@include file="/jsp/mainMenu.jsp" %>
    </div>

    <div class="container-fluid">
        <div class="row col-md-offset-3 col-lg-offset-3 col-sm-offset-3">
            <button type="button" id="addWork"class="btn btn-default btnAddWork" onclick="modalBox(document.getElementById('addWorkModal'))">Agregar oferta</button>
        </div>
        <div class="row">
        <div class="col-xs-6 col-sm-8 col-md-8 col-lg-8  col-sm-offset-2 col-md-offset-2 col-lg-offset-2">


                <ul class="nav nav-pills nav-justified">
                    <li class="active"><a href="#tab1default" data-toggle="tab">Trabajo</a></li>
                    <li class=""><a href="#tab2default" data-toggle="tab">Investigacion</a></li>
                </ul>
            </div>
        </div>

        <div class="col-xs-12 col-sm-8 col-md-8 col-sm-offset-2 col-md-offset-2">
            <div class="row centered-form">
                <div class="panel-body">
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="tab1default">
                            <!---------- WORK ---------->

                        <c:set var="investigationListParam" value="<%=Constants.WORK_PUBLICATION_LIST%>"/>
                        <c:set var="investigationList" value='${requestScope[investigationListParam]}' />
                        <c:forEach items="${investigationList}" var="workWrapper" varStatus="loop">

                            <div id="workDiv${loop.count}" class="bs-calltoaction bs-calltoaction-work">
                                <div class="row">
                                    <div class="panel-heading work-heading clearfix">
                                        <div class="row">

                                            <h1 class="cta-title pull-left"> <c:out value="${workWrapper.publication.name}"/> </h1>

                                            <div class="pull-right">
                                                <c:set var="remoteUser" value="<%=request.getRemoteUser()%>"/>
                                                <c:if test="${workWrapper.publication.author.email != remoteUser}">
                                                <div class="checkbox_wrapper pull-left">
                                                    <input class="checkbox" type="checkbox" id="publication${loop.count}" value="favorite" onclick="changeFavorite('${workWrapper.publication.id}', this.id)" <c:if test="${workWrapper.favorite}">checked</c:if>/>
                                                    <label></label>
                                                </div>
                                                </c:if>

                                                <c:if test="${workWrapper.publication.author.email == remoteUser}">
                                                    <button type="button" id="deleteButton" class="btn btn-default btnremovework"  onclick="removePublication('${workWrapper.publication.id}', 'workDiv${loop.count}')"><i class="glyphicon glyphicon-trash"></i></button>
                                                </c:if>

                                            </div>
                                        </div>
                                    </div>

                                    <div class="arrow-down"></div>

                                    <div class="panel-body">
                                        <div class="col col-md-12 discussionBox">
                                            <%--<div id="descriptionID" class="content hideContent">--%>
                                            <div id="show${workWrapper.publication.name}" class="content hideContent">
                                                ${workWrapper.htmlDescription}
                                            </div>
                                            <div class="panel-body showMore" id="show${workWrapper.publication.name}">

                                                <%--<h4>Sus tareas principales seran:</h4>
                                                <p><c:out value="${workWrapper.publication.tasks}"/></p>--%>

                                                <%--<h4>Seran requisitos excluyentes:</h4>
                                                <p><c:out value="${workWrapper.publication.requirements}"/></p>--%>

                                                    <%--<button type="button" class="btn btn-primary pull-right" onclick="modalBox(document.getElementById('sendRequest'))">Enviar peticion</button>--%>

                                                <c:set var="remoteUser" value="<%=request.getRemoteUser()%>"/>
                                                <c:if test="${workWrapper.publication.author.email != remoteUser}">
                                                <button type="button" class="btn btn-primary pull-right" onclick="sendPublicationPetition(${workWrapper.publication.id})">Enviar peticion</button>
                                                </c:if>
                                            </div>

                                            <%--<button type="button" id="showhide${loop.count}" class="btn btn-default pull-right" onclick="show('#show${workWrapper.publication.name}',this.id)"><i>Mostrar mas</i></button>--%>
                                            <%--<button type="button" id="showhide${loop.count}" class="btn btn-default pull-right" onclick="show('#show${workWrapper.publication.name}',this.id)"><i>Mostrar mas</i></button>--%>
                                            <div class="show-more">
                                            <button type="button" id="showhide${loop.count}" class="btn btn-default pull-right" ><i>Mostrar mas</i></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>
                        </div>

                        <!------ INVESTIGATION  -------->

                        <div class="tab-pane fade" id="tab2default">

                            <c:set var="investigationListParam" value="<%=Constants.INVESTIGATION_PUBLICATION_LIST%>"/>
                            <c:set var="investigationList" value='${requestScope[investigationListParam]}' />
                            <c:forEach items="${investigationList}" var="investigationWrapper" varStatus="loop">

                                <div id="publicationDiv${loop.count}" class="bs-calltoaction bs-calltoaction-work">
                                    <div class="row">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="pull-left">
                                                    <h1 class="cta-title"> <c:out value="${investigationWrapper.publication.name}"/> </h1>
                                                </div>
                                                <div class="pull-right">
                                                    <c:if test="${investigationWrapper.publication.author.email != remoteUser}">
                                                    <div class="checkbox_wrapper pull-left">
                                                        <input class="checkbox" type="checkbox" id="publication${loop.count}" value="favorite" onclick="changeFavorite('${investigationWrapper.publication.id}', this.id)" <c:if test="${investigationWrapper.favorite}">checked</c:if>>
                                                        <label></label>
                                                    </div>
                                                    </c:if>

                                                    <c:set var="remoteUser" value="<%=request.getRemoteUser()%>"/>
                                                    <c:if test="${investigationWrapper.publication.author.email == remoteUser}">
                                                        <button type="button" id="deleteButton" class="btn btn-default btnremovework pull-right"  onclick="removePublication('${investigationWrapper.publication.id}', 'publicationDiv${loop.count}')"><i class="glyphicon glyphicon-trash"></i></button>
                                                    </c:if>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel-body">
                                            <div class="col col-md-12 discussionBox">
                                                <div class="panel-heading">
                                                    ${investigationWrapper.htmlDescription}
                                                </div>


                                                <div class="panel-body " id="show${investigationWrapper.publication.name}">

                                                    <c:set var="userEmail" value="${pageContext.request.remoteUser}"/>
                                                    <c:set var="remoteUser" value="<%=request.getRemoteUser()%>"/>
                                                    <c:if test="${investigationWrapper.publication.author.email == remoteUser}">
                                                    <button type="button" class="btn btn-primary pull-right" onclick="sendPublicationPetition(${investigationWrapper.publication.id})">Enviar peticion</button>
                                                    </c:if>

                                                </div>

                                                <button type="button" id="buttonShow${investigationWrapper.publication.name}" class="btn btn-default pull-right" onclick="show('#show${investigationWrapper.publication.name}', document.getElementById(this))"><i>Mostrar mas</i> </button>

                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </c:forEach>

                        </div>



                    </div>
                </div>
            </div>
        </div>


        <!------ Modal Box send request ----->
        <div id="sendRequest" class="modal">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div class="modal-content">
                    <span onclick="closeModal(document.getElementById('sendRequest'))" class="close">&times;</span>
                    <div class="container-fluid">
                        <div class="row">
                            <form action="" method="post">
                                <div class="form-group">
                                    <div class="modal-header">
                                        <h3>Peticion de trabajo</h3>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row form-group">
                                            <label class="col-lg-3 control-label">Asunto</label>
                                            <div class="col-lg-5">
                                                <input class="form-control" type="text" name="" required>
                                            </div>
                                        </div>
                                        <div class=" row form-group">
                                            <label class="col-lg-3 control-label">Descripcion</label>
                                            <div class="col-lg-8">
                                                <textarea class="form-control" type="text" name="" required></textarea>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <button type="submit" class="btn btn-primary foo pull-right" onclick="closeModal(document.getElementById('sendRequest'))">Enviar peticion</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

        <!------ Modal Box add work or investigation ----->

    <div id="addWorkModal" class="modal">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="modal-content">
                    <span onclick="closeModal(document.getElementById('addWorkModal'))" class="close">&times;</span>
                    <div class="container-fluid">
                        <div class="row">
                            <form action="" method="post">
                                <div class="form-group">
                                    <div class="modal-header">
                                        <h3>Peticion de trabajo</h3>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row form-group">
                                            <label class="col-lg-3 control-label">Tipo</label>
                                            <div class="col-lg-5">
                                                <label>
                                                    <select id="publicationRole" class="select form-control" name="AgregarName">
                                                        <option value="AgregarValue1" selected>Trabajo</option>
                                                        <option value="AgregarValue2">Investigacion</option>
                                                    </select>
                                                </label>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <label class="col-lg-3 control-label">Nombre empresa</label>
                                            <div class="col-lg-5">
                                                <input id="nameInput" class="form-control" type="text" name="" required>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-12 markDown">
                                                <textarea id="descriptionTextarea"></textarea>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <button type="button" class="btn btn-primary pull-right" onclick="addPublication(); closeModal(document.getElementById('addWorkModal'));">Enviar peticion</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>




    <script type="text/javascript" src="<c:url value="/js/jquery-3.2.0.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/simplemde.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/work.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/modalBox.js"/>"></script>


</body>
</html>
