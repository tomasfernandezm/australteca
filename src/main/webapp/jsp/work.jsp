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
    <div class="container">
        <div class="row">
            <div class="col-sm-12 text-center">
                <button type="button" id="addWork" class="btn btn-default col-xs-12 col-sm-4 col-sm-offset-4 btnAddWork" onclick="modalBox(document.getElementById('addWorkModal'))">Agregar oferta</button>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-8 col-sm-offset-2 text-center">
                <ul class="nav nav-pills nav-justified">
                    <li class="active"><a href="#tab1default" data-toggle="tab">Trabajo</a></li>
                    <li class=""><a href="#tab2default" data-toggle="tab">Investigacion</a></li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-8 col-sm-offset-2">
                <form action="/servlet/listPublications" method="get" role="search">
                    <div class="input-group">
                        <input name="<%=Constants.PUBLICATION_NAME%>" type="text" class="form-control search-input" data-action="filter" placeholder="Buscar"/>
                        <div class="input-group-btn">
                            <button class="btn btn-default btn-search form-control" type="submit">Buscar</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-8 col-sm-offset-2">
                <div class="panel-body">
                    <div class="tab-content">
                        <!---------- WORK ---------->
                        <div class="tab-pane fade in active" id="tab1default">

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
                                        <div class="row">
                                            <div class="col col-md-12 discussionBox">
                                                <div id="show${workWrapper.publication.name}">
                                                    <article>
                                                        ${workWrapper.htmlDescription}
                                                    </article>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col col-md-12 discussionBox">
                                                <div  id="show${workWrapper.publication.name}">
                                                    <c:set var="remoteUser" value="<%=request.getRemoteUser()%>"/>
                                                    <c:if test="${workWrapper.publication.author.email != remoteUser}">
                                                        <%--<button type="button" class="btn btn-primary pull-right" onclick="sendPublicationPetition(${workWrapper.publication.id})">Enviar peticion</button>--%>
                                                        <button type="button" class="btn btn-send-mail pull-right" onclick="modalBox(document.getElementById('sendRequest'))">Enviar peticion</button>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            </c:forEach>

                            <div class="row">
                                <div class="col-sm-12 text-center">
                                <div class="btn-group">
                                    <c:set var="workPageNumberParam" value="<%=Constants.WORK_PAGE_NUMBER%>"/>
                                    <c:set var="workPageNumber" value="${requestScope[workPageNumberParam]}"/>
                                    <c:set var="investPageNumberParam" value="<%=Constants.INVESTIGATION_PAGE_NUMBER%>"/>
                                    <c:set var="investPageNumber" value="${requestScope[investPageNumberParam]}"/>
                                    <c:set var="workTotalPagesParam" value="<%=Constants.WORK_TOTAL_PAGES%>"/>
                                    <c:set var="investigationTotalPagesParam" value="<%=Constants.INVESTIGATION_TOTAL_PAGES%>"/>
                                    <c:set var="workTotalPages" value="${requestScope[workTotalPagesParam]}"/>
                                    <c:set var="investigationTotalPages" value="${requestScope[investigationTotalPagesParam]}"/>
                                    <c:set var="searchTitleParam" value="<%=Constants.SEARCH_PATTERN%>"/>
                                    <c:set var="searchTitle" value="${requestScope[searchTitleParam]}"/>
                                    <%--<p> ${workPageNumber}</p>--%>
                                    <c:if test="${!(workPageNumber eq 1)}">
                                        <form action="/servlet/listPublications" method="get">
                                            <input type="hidden" value="${workPageNumber-1}" name="<%=Constants.WORK_PAGE_NUMBER%>"/>
                                            <input type="hidden" value="${investPageNumber}" name="<%=Constants.INVESTIGATION_PAGE_NUMBER%>"/>
                                            <input type="hidden" name="<%=Constants.PUBLICATION_ROLE%>" value="<%=Constants.WORK_PUBLICATION%>"/>

                                            <c:if test="${searchTitle eq null}">
                                            <input type="hidden" name="<%=Constants.PUBLICATION_NAME%>" value="">
                                            </c:if>

                                            <c:if test="${!(searchTitle eq null)}">
                                                <input type="hidden" name="<%=Constants.PUBLICATION_NAME%>" value="${searchTitle}">
                                            </c:if>

                                            <button type="submit" class="btn btn-default btn-page-active">Anterior</button>
                                        </form>
                                    </c:if>
                                    <c:if test="${(workPageNumber eq 1)}">
                                        <button type="submit" class="btn btn-default btn-page-disabled disabled">Anterior</button>
                                    </c:if>

                                    <button type="button" class="btn btn-page-number"> ${workPageNumber}</button>


                                    <c:if test="${!(workPageNumber eq workTotalPages)}">
                                        <form action="/servlet/listPublications" method="get">
                                            <input type="hidden" value="${workPageNumber+1}" name="<%=Constants.WORK_PAGE_NUMBER%>"/>
                                            <input type="hidden" value="${investPageNumber}" name="<%=Constants.INVESTIGATION_PAGE_NUMBER%>"/>
                                            <input type="hidden" name="<%=Constants.PUBLICATION_ROLE%>" value="<%=Constants.WORK_PUBLICATION%>"/>
                                            <c:if test="${searchTitle eq null}">
                                                <input type="hidden" name="<%=Constants.PUBLICATION_NAME%>" value="">
                                            </c:if>
                                            <c:if test="${!(searchTitle eq null)}">
                                                <input type="hidden" name="<%=Constants.PUBLICATION_NAME%>" value="${searchTitle}">
                                            </c:if>
                                        <button type="submit" class="btn btn-default btn-page-active">Siguiente</button>
                                        </form>
                                    </c:if>
                                    <c:if test="${(workPageNumber eq workTotalPages)}">
                                        <button type="submit" class="btn btn-default btn-page-disabled disabled">Siguiente</button>
                                    </c:if>
                                </div>
                                </div>
                            </div>

                        </div>


                        <!------ INVESTIGATION  -------->

                        <div class="tab-pane fade" id="tab2default">
                            <c:set var="investigationListParam" value="<%=Constants.INVESTIGATION_PUBLICATION_LIST%>"/>
                            <c:set var="investigationList" value='${requestScope[investigationListParam]}' />
                            <c:forEach items="${investigationList}" var="investigationWrapper" varStatus="loop">

                                <div id="publicationDiv${loop.count}" class="bs-calltoaction bs-calltoaction-work">
                                    <div class="row">
                                        <div class="panel-heading work-heading clearfix">
                                            <div class="row">
                                                <h1 class="cta-title pull-left"> <c:out value="${investigationWrapper.publication.name}"/> </h1>
                                                <div class="pull-right">
                                                    <c:set var="remoteUser" value="<%=request.getRemoteUser()%>"/>
                                                    <c:if test="${investigationWrapper.publication.author.email != remoteUser}">
                                                        <div class="checkbox_wrapper pull-left">
                                                            <input class="checkbox" type="checkbox" id="publication${loop.count}" value="favorite" onclick="changeFavorite('${investigationWrapper.publication.id}', this.id)" <c:if test="${investigationWrapper.favorite}">checked</c:if>>
                                                            <label></label>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${investigationWrapper.publication.author.email == remoteUser}">
                                                        <button type="button" id="deleteButton" class="btn btn-default btnremovework"  onclick="removePublication('${investigationWrapper.publication.id}', 'publicationDiv${loop.count}')"><i class="glyphicon glyphicon-trash"></i></button>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="arrow-down"></div>
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col col-md-12 discussionBox">
                                                    <div id="show${investigationWrapper.publication.name}">
                                                        <article>
                                                            ${investigationWrapper.htmlDescription}
                                                        </article>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div id="show${investigationWrapper.publication.name}">
                                                    <div class="col col-md-12 discussionBox">
                                                        <c:set var="userEmail" value="${pageContext.request.remoteUser}"/>
                                                        <c:set var="remoteUser" value="<%=request.getRemoteUser()%>"/>
                                                        <c:if test="${investigationWrapper.publication.author.email == remoteUser}">
                                                            <%--<button type="button" class="btn btn-send-mail pull-right" onclick="sendPublicationPetition(${investigationWrapper.publication.id})">Enviar peticion</button>--%>
                                                            <button type="button" class="btn btn-send-mail pull-right" onclick="modalBox(document.getElementById('sendRequest'))">Enviar peticion</button>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                            <div class="row">
                                <div class="btn-group">
                                    <c:if test="${!(investPageNumber eq 1)}">
                                        <form action="/servlet/listPublications" method="get">
                                            <input type="hidden" value="${workPageNumber}" name="<%=Constants.WORK_PAGE_NUMBER%>"/>
                                            <input type="hidden" value="${investPageNumber-1}" name="<%=Constants.INVESTIGATION_PAGE_NUMBER%>"/>
                                            <input type="hidden" name="<%=Constants.PUBLICATION_ROLE%>"
                                            value="<%=Constants.INVESTIGATION_PUBLICATION%>"/>
                                            <button type="submit" class="btn btn-primary">Anterior</button>
                                        </form>
                                    </c:if>

                                    <c:if test="${!(investPageNumber eq investigationTotalPages)}">
                                        <form action="/servlet/listPublications" method="get">
                                            <input type="hidden" value="${workPageNumber+1}" name="<%=Constants.WORK_PAGE_NUMBER%>"/>
                                            <input type="hidden" value="${investPageNumber}" name="<%=Constants.INVESTIGATION_PAGE_NUMBER%>"/>
                                            <input type="hidden" name="<%=Constants.PUBLICATION_ROLE%>"
                                                   value="<%=Constants.INVESTIGATION_PUBLICATION%>"/>
                                            <button type="submit" class="btn btn-primary">Siguiente</button>
                                        </form>
                                    </c:if>
                                </div>
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


        <%--<!------ Modal Box add work or investigation ----->--%>

        <div id="addWorkModal" class="modal">
            <div class="modal-content">
                <span onclick="closeModal(document.getElementById('addWorkModal'))" class="close">&times;</span>
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12">
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
                                            <div class="col-lg-12">
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
        <script type="text/javascript" src="<c:url value="/js/readmore.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/js/readMoreWork.js"/>"></script>

    </body>
</html>
