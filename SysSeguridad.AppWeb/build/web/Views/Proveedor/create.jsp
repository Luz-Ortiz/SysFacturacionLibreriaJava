
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Proveedor</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <style>
            body {
                background-color: #f5f5f5;
            }
            .container {
                margin-top: 20px;
            }
            .card {
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .btn-action {
                margin-right: 10px;
            }
            /* Estilos para el formulario */
            .input-field input[type="text"],
            .input-field input[type="password"],
            .input-field input[type="email"] {
                color: #000;
                border-bottom: 1px solid #2196F3;
                box-shadow: 0 1px 0 0 #2196F3;
            }
            .input-field input[type="text"]:focus,
            .input-field input[type="password"]:focus,
            .input-field input[type="email"]:focus {
                border-bottom: 2px solid #2196F3;
                box-shadow: 0 2px 0 0 #2196F3;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Crear Proveedor</h5>
            <div class="card">
                <form action="Proveedor" method="post">
                    <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                    <div class="row">
                        <div class="input-field col l4 s12">
                            <input  id="txtCodigo" type="text" name="codigo" required class="validate" maxlength="9" placeholder="Ingrese su Codigo">
                            <label for="txtCodigo">Codigo</label>
                        </div> 
                        <div class="input-field col l4 s12">
                            <input id="txtNombre" type="text" name="nombre" required class="validate" maxlength="30" placeholder="Ingrese su Nombre">
                            <label for="txtNombre">Nombre</label>
                        </div> 
                        <div class="input-field col l4 s12">
                            <input id="txtTelefono" type="text" name="telefono" required class="validate" maxlength="8" placeholder="Ingrese el Numero de Telefono">
                            <label for="txtTelefono">Telefono</label>
                        </div>
                        <div class="input-field col l4 s12">
                            <input id="txtDireccion" type="text" name="direccion" required class="validate" maxlength="50" placeholder="Ingrese su Direccion">
                            <label for="txtDireccion">Direccion</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col l12 s12">
                            <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                            <a href="Proveedor" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                        </div>
                    </div>
                </form>          
            </div>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
