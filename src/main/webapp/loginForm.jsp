<%@ page import="org.securityfilter.example.Constants"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <title><%=Constants.LOGIN_TITLE%></title>
    </head>
    <body>

        <h1><%=Constants.LOGIN_TITLE%></h1>

        <%@include file="/menu.jsp" %>

        Login with username=<i><%=Constants.VALID_USERNAME%></i>
        and password=<i><%=Constants.VALID_PASSWORD%></i>.

        <form id="<%=Constants.LOGIN_FORM_ID%>" action="<%=response.encodeURL(Constants.LOGIN_FORM_ACTION)%>" method="POST">

            Username:
            <input type="text"
            name="<%=Constants.LOGIN_USERNAME_FIELD%>"
            value="<%=Constants.VALID_USERNAME%>"
            ><p>

            Password:
            <input type="password"
            name="<%=Constants.LOGIN_PASSWORD_FIELD%>"
            value="<%=Constants.VALID_PASSWORD%>"
            ><p>

            <input type="Submit">

        </form>
    </body>

</html>