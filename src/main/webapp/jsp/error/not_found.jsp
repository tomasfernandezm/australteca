<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>Australteca - 404</title>

        <link href="<c:url value="/css/error.css"/>" rel="stylesheet" type="text/css">
    </head>

    <body class="error404">
	    <div class="error-message-container">

		    <a href="#" class="logo"></a>

		    <div class="error-message">
			    <p class="title">No encontramos tu página</p>
			    <p class="message">Puede que hayamos movido o eliminado el link al que quisiste acceder</p>
			    <p class="small">Andá al
                    <a href="<c:url value="/jsp/loginForm.jsp"/>">login</a>
                    o
                    <a href="#">contactanos</a>
                    si no podés resolver tu problema</p>
		    </div>
	    </div>
    </body>
</html>