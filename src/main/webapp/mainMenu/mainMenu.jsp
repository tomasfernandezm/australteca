<%@ page import="org.securityfilter.example.MyConstants" %><%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 29/3/17
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>


    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand"><img class="logo" src="/images/logo.png"></a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="<%=response.encodeURL("/mainMenu/home.jsp")%>">Inicio</a></li>
                <li><a href="<%=response.encodeURL("/listSubjects")%>">Materias</a></li>
                <li><a href="<%=response.encodeURL("/mainMenu/work.jsp")%>">Trabajo</a></li>
                <li><a href="<%=response.encodeURL("/mainMenu/discussions.jsp")%>">Discuciones</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <div class="btn-group">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <%= request.getRemoteUser() %> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="<%= response.encodeURL("/mainMenu/userSettings.jsp")%>">Ajustes</a></li>
                        <li><a href="<%= response.encodeURL("/logout.jsp")%>">Sign out</a></li>
                    </ul>
                </div>
            </ul>
        </div>
    </nav>




