<%@ page import="org.securityfilter.example.MyConstants" %><%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 29/3/17
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>

<!--
<p>

    Navigation Main Menu: [
    <a href="<%=response.encodeURL("/mainMenu/home.jsp")%>">Inicio</a>
    | <a href="<%=response.encodeURL("/mainMenu/subject.jsp")%>">Materias</a>
    | <a href="<%=response.encodeURL("/mainMenu/work.jsp")%>">Trabajo</a>
    | <a href="<%=response.encodeURL("/mainMenu/discussions.jsp")%>">Discuciones</a>
    | <a href="<%=response.encodeURL("/logout.jsp")%>">Cerrar Sesion</a>

    ]<p>
  -->

    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand"><%=MyConstants.MY_HOME_TITLE%></a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="<%=response.encodeURL("/mainMenu/home.jsp")%>">Inicio</a></li>
                <li><a href="<%=response.encodeURL("/mainMenu/subject.jsp")%>">Materias</a></li>
                <li><a href="<%=response.encodeURL("/mainMenu/work.jsp")%>">Trabajo</a></li>
                <li><a href="<%=response.encodeURL("/mainMenu/discussions.jsp")%>">Discuciones</a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<%=response.encodeURL("/logout.jsp")%>">Cerrar Sesion</a></li>
            </ul>
        </div>
    </nav>



