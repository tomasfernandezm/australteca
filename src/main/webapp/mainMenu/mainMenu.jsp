    <%--
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
            <a href="<%=response.encodeURL("/userListPost")%>" class="navbar-brand"><img class="logo" src="/images/logo.png"></a>
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                    <li class="activeHome2"><a href="<%=response.encodeURL("/userListPost")%>">Inicio</a></li>
                    <li class="activeSubject2"><a href="<%=response.encodeURL("/listSubjects")%>">Materias</a></li>
                    <li class="activeWork2"><a href="<%=response.encodeURL("/mainMenu/work.jsp")%>">Trabajo</a></li>
                    <li class="activeDiscussions2"><a href="<%=response.encodeURL("/mainMenu/discussions.jsp")%>">Discuciones</a></li>
                <%if(request.isUserInRole(Constants.ADMINISTRATOR)){%>
                    <li class="activeModerators2"><a href="<%=response.encodeURL("/postModerators")%>">Moderadores</a></li>
                <%}%>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <div class="btn-group">
                    <button type="button" class="btn btn-default dropdown-toggle settings-btn" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <%= request.getRemoteUser() %> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="<%= response.encodeURL("/userPost")%>">Ajustes</a></li>
                        <li><a href="<%= response.encodeURL("/logout.jsp")%>">Sign out</a></li>
                    </ul>
                </div>
            </ul>
        </div>
    </nav>







