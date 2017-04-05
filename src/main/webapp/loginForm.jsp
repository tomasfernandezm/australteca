<%@ page import="org.securityfilter.example.Constants"%>
<%@ page import="org.securityfilter.example.MyConstants" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title><%=MyConstants.MY_HOME_TITLE%></title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="css/loginForm.css" rel="stylesheet" type="text/css">
</head>

<body>

<nav class="navbar navbar-inverse container-fluid" role="navigation">
  <div>
    <div class="navbar-header "> 
         <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target=".navbar-ex1-collapse">
          <span class="sr-only">Desplegar navegacion</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
    	<a class="navbar-brand"><%=MyConstants.MY_HOME_TITLE%></a> 
    </div>
    
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <form class="navbar-form navbar-right" id="<%=Constants.LOGIN_FORM_ID%>" action="<%=response.encodeURL(Constants.LOGIN_FORM_ACTION)%>" method="POST">
          <div class="form-group">
            <label id="email"for="email">Email</label>
            <input type="text" name="<%=Constants.LOGIN_USERNAME_FIELD%>">
          </div>
          <div class="form-group">
            <label for "password">Password</label>
            <input type="password" name="<%=Constants.LOGIN_PASSWORD_FIELD%>">
          </div>
          <input type="Submit" class="btn btn-default btn-xs btn-outline-primary"  value="Iniciar sesion">
        </form>
    </div>
  </div>
</nav>




<form id="<%=MyConstants.REGISTER_FORM_ID%>" action="registerConfirmation.jsp" method="POST">
  <div>
  Name:
  <input type="text" name= "name" required/>
  Lastname:
  <input type="text" name= "lastname" required/>
  <div/>
  <div> Email:
    <input type="email" name= "email" pattern="^[A-Za-z0-9._%-]+@ing.austral.edu.ar"
                           placeholder="ejemplo@ing.austral.edu.ar" required/>
  </div>
  <div> Password:
    <input type="password" name="password" pattern=".{0}|.{8,}"   required title="Mínimo 8 caracteres"/>
  </div>
  <div> Password confirmation:
    <input type="password" name="passwordC" pattern=".{0}|.{8,}"   required title="Mínimo 8 caracteres"/>
  </div>
  <div>
    <select name="career">
      <option value="Ingenieria informatica" selected>Ingenieria informatica</option>
      <option value="Ingenieria indutrial">Ingenieria indutrial</option>
    </select>
  </div>
  <input type="Submit" value="Unirme">
</form>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>