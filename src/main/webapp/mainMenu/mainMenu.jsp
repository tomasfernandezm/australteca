<%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 29/3/17
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<p>

    Navigation Main Menu: [
    <a href="<%=response.encodeURL("/mainMenu/home.jsp")%>">Inicio</a>
    | <a href="<%=response.encodeURL("/mainMenu/subject.jsp")%>">Materias</a>
    | <a href="<%=response.encodeURL("/mainMenu/work.jsp")%>">Trabajo</a>
    | <a href="<%=response.encodeURL("/mainMenu/discussions.jsp")%>">Discuciones</a>
    | <a href="<%=response.encodeURL("/logout.jsp")%>">Cerrar Sesion</a>

    ]<p>
