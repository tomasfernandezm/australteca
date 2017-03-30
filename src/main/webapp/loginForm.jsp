<%@ page import="org.securityfilter.example.Constants"%>
<%@ page import="org.securityfilter.example.MyConstants" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <title><%=MyConstants.MY_HOME_TITLE%></title>
    </head>
    <body>

        <h1><%=MyConstants.MY_HOME_TITLE%></h1>


        <form id="<%=Constants.LOGIN_FORM_ID%>" action="<%=response.encodeURL(Constants.LOGIN_FORM_ACTION)%>" method="POST">

            Email:
            <input type="text"
            name="<%=Constants.LOGIN_USERNAME_FIELD%>">

            Password:
            <input type="password" name="<%=Constants.LOGIN_PASSWORD_FIELD%>">

            <input type="Submit" value="Iniciar sesion">

        </form>


        <form id="<%=MyConstants.REGISTER_FORM_ID%>" action="registerConfirmation.jsp" method="POST">
                <div>
                    Name:
                    <input type="text" name= "name" required/>

                    Lastname:
                    <input type="text" name= "lastname" required/>
                <div/>

                <div>
                    Email:
                    <input type="email" name= "email" pattern="^[A-Za-z0-9._%-]+@ing.austral.edu.ar"
                           placeholder="ejemplo@ing.austral.edu.ar" required/>
                </div>

                <div>
                    Password:
                    <input type="password" name="password" pattern=".{0}|.{8,}"   required title="Mínimo 8 caracteres"/>
                </div>
                <div>
                    Password confirmation:
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




    </body>

</html>