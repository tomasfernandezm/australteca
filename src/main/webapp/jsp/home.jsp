<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="org.australteca.Constants" %>
<%@ page import="static org.australteca.Constants.USER_SUBJECT_LIST" %>
<%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 29/3/17
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale1.0">
    <title><%=Constants.MY_HOME_TITLE%></title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/mainMenu.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/home.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/modalBox.css"/>" rel="stylesheet" type="text/css">

</head>
<body>
    <div class="active-home">
        <%@include file="/jsp/mainMenu.jsp" %>
    </div>

    <div class="container">
        <div class="col-sm-12">

            <!---------------- subjects ------------->
            <div class="bs-calltoaction bs-calltoaction-subjects">
                <div class="row">
                    <div class="panel-heading">
                        <h1 class="cta-title">Mis materias</h1>
                    </div>
                    <div class="panel-body">
                        <c:set var="subjectListParam" value="<%=USER_SUBJECT_LIST%>"/>
                        <c:set var="subjectList" value='${requestScope[subjectListParam]}' />
                        <c:forEach items="${subjectList}" var="subject">
                            <div class="col col-md-4">
                                <a href="<c:url value="/servlet/postSubject?subjectName=${subject.subjectName}"/>" class="btn btn-lg btn-block btn-primary"><c:out value="${subject.subjectName}"/></a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <!--------------- Work --------------->
            <div class="bs-calltoaction bs-calltoaction-work">
                <div class="row">
                    <div class="panel-heading">
                        <h1 class="cta-title">Mis Proyectos</h1>
                    </div>

                    <div class="panel-body">

                            <c:set var="publicationListParam" value="<%=Constants.USER_PUBLICATION_LIST%>"/>
                            <c:set var="publicationWrapperList" value="${requestScope[publicationListParam]}"/>
                            <c:forEach var="publicationWrapper" items="${publicationWrapperList}" varStatus="loop">
                                <c:set var="role" value="Trabajo"/>
                                <%--<c:if test="${publication.role == role}">--%>
                                    <div id="publicationPanel${loop.count}" class="col col-md-6">
                                        <div id="publication${loop.count}" class="favoriteWork">
                                            <div class="panel-heading work-heading clearfix">
                                                <h4 class="pull-left"><c:out value="${publicationWrapper.publication.name}"/> </h4>


                                                <c:set var="remoteUser" value="<%=request.getRemoteUser()%>"/>
                                                <c:if test="${publicationWrapper.publication.author.email != remoteUser}">
                                                <div class="checkbox_wrapper pull-right">
                                                    <input class="checkbox" type="checkbox" id="favoriteWork${loop.count}" checked onclick="changeFavorite('${publicationWrapper.publication.id}', 'favoriteWork${loop.count}', 'publicationPanel${loop.count}')">
                                                    <label></label>
                                                </div>
                                                </c:if>
                                                <c:if test="${publicationWrapper.publication.author.email == remoteUser}">
                                                    <button type="button" id="deleteButton" class="btn btn-default btnremovework pull-right"  onclick="removePublication('${publicationWrapper.publication.id}', 'publicationPanel${loop.count}')"><i class="glyphicon glyphicon-trash"></i></button>
                                                </c:if>


                                            </div>
                                            <div class="arrow-down"></div>
                                            <div class="panel-body">
                                                <div class="row">
                                                    <article>
                                                        ${publicationWrapper.htmlDescription}
                                                    </article>
                                                </div>
                                                <div class="row">
                                                    <div class="" id="show${publicationWrapper.publication.name}">
                                                        <c:if test="${publicationWrapper.publication.author.email != remoteUser}">
                                                        <button type="button" class="btn btn-send-mail pull-right" onclick="modalBox(document.getElementById('sendRequest'))">Enviar peticion</button>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                <%--</c:if>--%>
                            </c:forEach>

                    </div>
                </div>
            </div>


            <!------------- Activity------------>
            <div class="bs-calltoaction bs-calltoaction-statistics">
                <div class="row">
                    <div class="panel-heading">
                        <h1 class="activity-title">Mi actividad</h1>
                    </div>
                    <div class="panel-body">
                        <div class="col col-md-4 center-column">
                            <h4>Archivos compartidos</h4>
                            <div class="circle-file activity-1">
                                <c:set var="noteAmountParam" value="<%=Constants.AMOUNT_OF_UPLOADED_NOTES%>"/>
                                <p>${requestScope[noteAmountParam]}</p>
                            </div>
                        </div>
                        <div class="col col-md-4 center-column">
                            <h4>Comentarios hechos</h4>
                            <div class="circle-file activity-2">
                                <c:set var="commentaryAmountParam" value="<%=Constants.AMOUNT_OF_COMMENTARIES%>"/>
                                <p>${requestScope[commentaryAmountParam]}</p>
                            </div>
                        </div>
                        <div class="col col-md-4 center-column">
                            <h4>Materias moderadas</h4>
                            <div class="circle-file activity-3">
                                <c:set var="moderatedAmountParam" value="<%=Constants.AMOUNT_OF_MODERATED_SUBJECTS%>"/>
                                <p>${requestScope[moderatedAmountParam]}</p>
                            </div>
                        </div>

                    </div>
                </div>
            </div>


        </div>
    </div>

    <%--<!------ Modal Box send request ----->--%>
    <div id="sendRequest" class="modal">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="modal-content-2">
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
                                        <button type="submit" class="btn btn-send-mail pull-right" onclick="closeModal(document.getElementById('sendRequest'))">Enviar peticion</button>
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
    <script type="text/javascript" src="<c:url value="/js/home.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/modalBox.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/readmore.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/readMoreWork.js"/>"></script>

</body>
</html>
