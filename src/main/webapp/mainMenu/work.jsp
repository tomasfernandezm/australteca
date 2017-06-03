<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.australteca.Constants" %><%--
  Created by IntelliJ IDEA.
  User: tomasforman
  Date: 29/3/17
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale1.0">
    <title><%=Constants.MY_HOME_TITLE%></title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/mainMenu.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/work.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/css/modalBox.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="active-work">
        <%@include file="/mainMenu/mainMenu.jsp" %>
    </div>

    <div class="container-fluid">
        <div class="col-xs-8 col-sm-8 col-md-7 col-xs-offset-4 col-sm-offset-4 col-md-offset-5">
            <div class="row">
                <ul class="nav nav-pills">
                    <li class="active"><a href="#tab1default" data-toggle="tab">   Trabajo   </a></li>
                    <li><a href=    "#tab2default" data-toggle="tab">Investigacion</a></li>
                </ul>
            </div>
        </div>
        <div class="col-xs-12 col-sm-8 col-md-8 col-sm-offset-2 col-md-offset-2">
            <button type="button" id="addWork"class="btn btn-default col-md-12" onclick="modalBox(document.getElementById('addWorkModal'))"><i>Agregar</i> </button>

            <div class="row centered-form">
                <div class="panel-body">
                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="tab1default">

                            <!---------- Trabajo 1 ejemplo ---------->

                            <div class="bs-calltoaction bs-calltoaction-work">
                                <div class="row">
                                    <div class="panel-heading">
                                        <h1 class="cta-title">Falabella</h1>
                                    </div>
                                    <div class="panel-body">
                                        <div class="col col-md-12 discussionBox">
                                            <div class="panel-heading">
                                                <p>Descripcion: En Falabella nos encontramos en la busqueda de un Category Manager Marketing SEO y SEM
                                                    para nuestras oficinas de Olivos.
                                                    Quien ocupe la posicion, debera contar con un perfil dinamico y proactivo,
                                                    con marcada orientacion al cliente y a resultados.</p>
                                            </div>


                                            <div class="panel-body showMore" id="show" hidden>

                                                   <h4>Sus tareas principales seran:</h4>
                                                <p>
                                                    - Coordinar la implementacion de campanas con agencia digital y pedidos al equipo de diseño.

                                                    - Redaccion de textos de campanas.

                                                    - Generacion de informes y reportes de campanas.

                                                    - Trackeos de campanas de proveedores.
                                                </p>

                                                   <h4>Seran requisitos excluyentes:</h4>
                                                <p>
                                                    - Graduados o proximos a graduarse de las carreras de marketing, publicidad, comunicacion, administracion de empresas o afines.
                                                    - Excelente manejo de Excel
                                                    - Buen manejo del idioma Ingles

                                                    Si buscas trabajar en un excelente clima laboral, posibilidades de crecimiento, desarrollo y capacitacion, postulate a nuestro aviso sin omitir tu remuneracion bruta pretendida.

                                                </p>

                                                <button type="button" class="btn btn-primary pull-right" onclick="modalBox(document.getElementById('sendRequest'))">Enviar peticion</button>


                                            </div>

                                            <button type="button" id="showandhide"class="btn btn-default pull-right"><i>Mostrar mas</i> </button>

                                        </div>
                                    </div>
                                </div>
                            </div>





                        </div>
                        <div class="tab-pane fade" id="tab2default">
                            <button type="button" id="addInvestigation"class="btn btn-default col-md-12" onclick="modalBox(document.getElementById('addWorkModal'))"><i>Agregar</i> </button>
                            Copiar igual que trabajo


                        </div>



                    </div>
                </div>
            </div>
        </div>


        <!------ Modal Box send request ----->
        <div id="sendRequest" class="modal">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="modal-content">
                    <span onclick="closeModal(document.getElementById('sendRequest'))" class="close">&times;</span>
                    <div class="container-fluid">
                        <div class="row">
                            <form action="" method="post">
                                <div class="form-group">
                                    <div class="modal-header">
                                        <h3>Peticion de trabajo</h3>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row form-group">
                                            <label class="col-lg-3 control-label">Asunto</label>
                                            <div class="col-lg-5">
                                                <input class="form-control" type="text" name="" required>
                                            </div>
                                        </div>
                                        <div class=" row form-group">
                                            <label class="col-lg-3 control-label">Descripcion</label>
                                            <div class="col-lg-8">
                                                <textarea class="form-control" type="text" name="" required></textarea>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <button type="submit" class="btn btn-primary foo pull-right">Enviar peticion</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!------ Modal Box add work ----->
        <div id="addWorkModal" class="modal">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="modal-content">
                    <span onclick="closeModal(document.getElementById('addWorkModal'))" class="close">&times;</span>
                    <div class="container-fluid">
                        <div class="row">
                            <form action="" method="post">
                                <div class="form-group">
                                    <div class="modal-header">
                                        <h3>Peticion de trabajo</h3>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row form-group">
                                            <label class="col-lg-3 control-label">Nombre empresa</label>
                                            <div class="col-lg-5">
                                                <input class="form-control" type="text" name="" required>
                                            </div>
                                        </div>
                                        <div class=" row form-group">
                                            <label class="col-lg-3 control-label">Descripcion</label>
                                            <div class="col-lg-8">
                                                <textarea class="form-control" type="text" name="" required></textarea>
                                            </div>
                                        </div>
                                        <div class=" row form-group">
                                            <label class="col-lg-3 control-label">Requisitos</label>
                                            <div class="col-lg-8">
                                                <textarea class="form-control" type="text" name="" required></textarea>
                                            </div>
                                        </div>
                                        <div class=" row form-group">
                                            <label class="col-lg-3 control-label">Tareas principales</label>
                                            <div class="col-lg-8">
                                                <textarea class="form-control" type="text" name="" required></textarea>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <button type="button" class="btn btn-primary pull-right" data-toggle="popover"
                                                    title="Seguro desea enviar?" data-content="hola" value="Agregar">Enviar peticion</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <!------ Modal Box add work ----->
        <div id="addInvestigationModal" class="modal">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <div class="modal-content">
                    <span onclick="closeModal(document.getElementById('addInvestigationModal'))" class="close">&times;</span>
                    <div class="container-fluid">
                        <div class="row">
                            <form action="" method="post">
                                <div class="form-group">
                                    <div class="modal-header">
                                        <h3>Peticion de investigacion</h3>
                                    </div>
                                    <div class="modal-body">
                                        <div class="row form-group">
                                            <label class="col-lg-3 control-label">Nombre empresa</label>
                                            <div class="col-lg-5">
                                                <input class="form-control" type="text" name="" required>
                                            </div>
                                        </div>
                                        <div class=" row form-group">
                                            <label class="col-lg-3 control-label">Descripcion</label>
                                            <div class="col-lg-8">
                                                <textarea class="form-control" type="text" name="" required></textarea>
                                            </div>
                                        </div>
                                        <div class=" row form-group">
                                            <label class="col-lg-3 control-label">Requisitos</label>
                                            <div class="col-lg-8">
                                                <textarea class="form-control" type="text" name="" required></textarea>
                                            </div>
                                        </div>
                                        <div class=" row form-group">
                                            <label class="col-lg-3 control-label">Tareas principales</label>
                                            <div class="col-lg-8">
                                                <textarea class="form-control" type="text" name="" required></textarea>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <button type="button" class="btn btn-primary pull-right" data-toggle="popover"
                                                    title="Seguro desea enviar?" data-content="hola" value="Agregar">Enviar peticion</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>





    </div>








    <script type="text/javascript" src="<c:url value="/js/jquery-3.2.0.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/work.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/modalBox.js"/>"></script>


</body>
</html>
