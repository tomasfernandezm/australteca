<%@ page import="org.australteca.Constants" %>    <%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 29/3/17
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>

<META HTTP-EQUIV="refresh" CONTENT="<%= session.getMaxInactiveInterval() %>; URL=/loginForm.jsp" />

    <nav class="navbar navbar-inverse container-fluid" role="navigation">
        <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target=".navbar-ex1-collapse">
            <span class="sr-only">Desplegar navegacion</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <div class="navbar-header">
            <%--<a href="<%=response.encodeURL("/servlet/userListPost")%>" class="navbar-brand"><img class="logo" src="/images/logo.png"></a>--%>
            <svg height="50" width="150" href="<%=response.encodeURL("/servlet/userListPost")%>">:
                <text x="0" y="40" fill="black" style="font-family: SignPainter, georgia, times, serif; font-weight: normal; font-style: normal; font-size: 40px; fill:white">Australteca</text>
            </svg>

        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                    <li class="activeHome2"><a href="<%=response.encodeURL("/servlet/userListPost")%>">Inicio</a></li>
                    <li class="activeSubject2"><a href="<%=response.encodeURL("/servlet/listSubjects")%>">Materias</a></li>
                    <li class="activeWork2"><a href="<%=response.encodeURL("/servlet/listPublications")%>">Trabajo</a></li>
                <%if(request.isUserInRole(Constants.ADMINISTRATOR)){%>
                    <li class="activeModerators2"><a href="<%=response.encodeURL("/servlet/postModerators")%>">Administracion</a></li>
                <%}%>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <div class="btn-group">
                    <button type="button" class="btn btn-default dropdown-toggle settings-btn" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <%= request.getRemoteUser() %> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="<%= response.encodeURL("/servlet/userPost")%>">Ajustes</a></li>
                        <li><a href="<%= response.encodeURL("/jsp/logout.jsp")%>">Cerrar sesion</a></li>
                    </ul>
                </div>
            </ul>
        </div>
    </nav>







