
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>SecurityFilter Example Application: Login Error Page</title>
    </head>

    <body>
        <%--<h1>SecurityFilter Example Application: Login Error Page</h1>
        Bad username/password combination, please <a href="<%=response.encodeURL("loginForm.jsp")%>">try again</a>.--%>

        <% session.setAttribute("loginSucceded", false);%>
        <% response.sendRedirect("/loginForm.jsp");%>
    </body>

</html>