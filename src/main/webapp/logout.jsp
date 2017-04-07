<%@ page import="org.securityfilter.example.Constants"%>
<%@ page import="org.securityfilter.example.MyConstants" %>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <meta http-equiv="Refresh" content="0;loginForm.jsp">
        <title><%=MyConstants.MY_HOME_TITLE%></title>
    </head>

    <body>
        <% session.invalidate(); %>
    </body>

</html>