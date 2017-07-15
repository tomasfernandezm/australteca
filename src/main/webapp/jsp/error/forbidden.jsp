<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>Australteca - 403</title>

        <link href="<c:url value="/css/error.css"/>" rel="stylesheet" type="text/css">
    </head>

    <body class="error404">
        <div class="error-message-container">

            <a href="#" class="logo"></a>

            <div class="error-message">
                <p class="title">Epa, no podes ver este contenido</p>
                <p class="message"></p>
                <p class="small">Anda al
                    <a href="<c:url value="<%=request.getHeader("Referer")%>"/>">login</a>
                    o
                    <a href="#">contactanos: help@australteca.com.ar</a>
                    si no podes resolver tu problema</p>
            </div>
        </div>
    </body>
</html>
