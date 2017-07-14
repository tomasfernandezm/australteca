<%@ page import="org.australteca.Constants"%>


<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="Refresh" content="0;loginForm.jsp">
        <title><%=Constants.MY_HOME_TITLE%></title>
    </head>

    <body>
        <script>
            signOut();
        </script>
        <% session.invalidate(); %>
    <script type="text/javascript" src="/js/googleOAuth.js"></script>
    </body>
</html>