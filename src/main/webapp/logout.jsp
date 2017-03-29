<%@ page import="org.securityfilter.example.Constants"%>
<%@ page import="org.securityfilter.example.MyConstants" %>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <meta http-equiv="Refresh" content="5;loginForm.jsp">
        <title><%=MyConstants.MY_HOME_TITLE%></title>
    </head>

    <body>
        <% session.invalidate(); %>
    <h2>La sesion se ha cerrado</h2>
    <h4>En 5 segundos seras redirigido a la paginal principal</h4>

    </body>

</html>