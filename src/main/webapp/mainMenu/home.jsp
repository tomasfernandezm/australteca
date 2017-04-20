
<%@ page import="org.australteca.Constants" %><%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 29/3/17
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale1.0">
    <title><%=Constants.MY_HOME_TITLE%></title>
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/mainMenu.css" rel="stylesheet" type="text/css">
    <link href="/css/home.css" rel="stylesheet" type="text/css">

</head>
<body>
    <div class="active-home">
        <%@include file="/mainMenu/mainMenu.jsp" %>
    </div>

    <div class="container">
        <div class="col-sm-12">

            <div class="bs-calltoaction bs-calltoaction-subjects">
                <div class="row">
                    <div class="panel-heading">
                        <h1 class="cta-title">Mis materias</h1>
                    </div>
                    <div class="panel-body">
                        <!--Este bloque de codigo va dentro del foreach-->
                        <div class="col col-md-4">
                            <a href="#" class="btn btn-lg btn-block btn-primary">Analisis 4</a>
                        </div>
                        <!--fin del bloque-->
                        <div class="col col-md-4">
                            <a href="#" class="btn btn-lg btn-block btn-primary">Fisica</a>
                        </div>
                        <div class="col col-md-4">
                            <a href="#" class="btn btn-lg btn-block btn-primary">Laboratorio 1</a>
                        </div>
                        <div class="col col-md-4">
                            <a href="#" class="btn btn-lg btn-block btn-primary">Teologia</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="bs-calltoaction bs-calltoaction-discussions">
                <div class="row">
                    <div class="panel-heading">
                        <h1 class="cta-title">Mis Discuciones</h1>
                    </div>
                    <div class="panel-body">


                    </div>
                </div>
            </div>

            <div class="bs-calltoaction bs-calltoaction-work">
                <div class="row">
                    <div class="panel-heading">
                        <h1 class="cta-title">Mis trabajos</h1>
                    </div>
                    <div class="panel-body">


                    </div>
                </div>
            </div>

            <div class="bs-calltoaction bs-calltoaction-statistics">
                <div class="row">
                    <div class="panel-heading">
                        <h1 class="cta-title">Estadisticas</h1>
                    </div>
                    <div class="panel-body">
                        <div class="col col-md-4">
                            <h4>Archivos compartidos</h4>
                            <div class="circle-file">
                                <p>12</p>
                            </div>
                        </div>
                        <div class="col col-md-4">
                            <p>Discuciones empezadas</p>
                        </div>
                        <div class="col col-md-4">
                            <p>Archivos descargados</p>
                        </div>

                    </div>
                </div>
            </div>


        </div>
    </div>


    <script type="text/javascript" src="/js/jquery-3.2.0.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/home.js"></script>
</body>
</html>
