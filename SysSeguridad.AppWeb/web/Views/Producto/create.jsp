<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Producto"%>
<%@page import="sysseguridad.entidadesdenegocio.Categoria"%>
<%@page import="sysseguridad.entidadesdenegocio.Proveedor"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Crear Producto</title>
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
        /* Estilos para el formulario */
        .input-field input[type="text"] {
            border-bottom: 1px solid #1E88E5;
            box-shadow: 0 1px 0 0 #1E88E5;
        }
        /* Estilos para los select */
        .select-wrapper input.select-dropdown {
            border-bottom: 1px solid #1E88E5 !important;
            box-shadow: 0 1px 0 0 #1E88E5 !important;
        }
        /* Estilos para los botones */
        .btn-blue {
            background-color: #1E88E5;
        }
        .btn-blue:hover {
            background-color: #155FA0;
        }
        .btn-red {
            background-color: #E53935;
        }
        .btn-red:hover {
            background-color: #D32F2F;
        }
    </style>
</head>
<body>
    <!-- Encabezado personalizado -->
    <jsp:include page="/Views/Shared/headerBody.jsp" />
    <main class="container">   
        <h5>Crear Producto</h5>
        <div class="card">
            <form action="Producto" method="post" onsubmit="return validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input id="txtNombre" type="text" name="Nombre" required class="validate" maxlength="30" placeholder="Ingrese su nombre">
                        <label for="txtNombre">Nombre</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input id="txtDescripcion" type="text" name="Descripcion" required class="validate" maxlength="30" placeholder="Ingrese su descripción">
                        <label for="txtDescripcion">Descripción</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input id="txtPrecio" type="text" name="Precio" required class="validate" maxlength="30" placeholder="Ingrese el precio">
                        <label for="txtPrecio">Precio</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input id="txtExistencia" type="text" name="Existencia" required class="validate" maxlength="30" placeholder="Ingrese la cantidad en existencia">
                        <label for="txtExistencia">Existencias</label>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Proveedor/select.jsp">                           
                            <jsp:param name="IdProveedor" value="0" />  
                        </jsp:include>  
                        <span id="slProveedor_error" style="color:red" class="helper-text"></span>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Categoria/Select.jsp">                           
                            <jsp:param name="IdCategoria" value="0" />  
                        </jsp:include>  
                        <span id="slCategoria_error" style="color:red" class="helper-text"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="submit" class="waves-effect waves-light btn btn-blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Producto" class="waves-effect waves-light btn btn-red"><i class="material-icons right">list</i>Cancelar</a>
                    </div>
                </div>
            </form>
        </div>
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var elems = document.querySelectorAll('select');
            var instances = M.FormSelect.init(elems);
        });
    </script>
</body>
</html>
