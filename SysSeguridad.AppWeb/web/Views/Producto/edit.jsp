<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Producto"%>
<% Producto producto = (Producto) request.getAttribute("producto");%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Editar Producto</title>
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
        .btn-action:hover {
            background-color: #1E88E5 !important;
        }
        .btn-action i {
            font-weight: bold;
        }
        .sidebar {
            height: 100%;
            width: 250px;
            position: fixed;
            top: -20px;
            left: -250px;
            background: linear-gradient(to right, #2E8B57, #87CEEB);
            overflow-x: hidden;
            transition: 0.5s;
            padding-top: 40px;
            z-index: 1;
        }
        .sidebar.active {
            left: 0;
        }
        .sidebar a {
            padding: 15px;
            text-decoration: none;
            font-size: 20px;
            color: white;
            display: block;
            transition: 0.3s;
        }
        .sidebar a:hover {
            color: #3f888f;
        }
    </style>
</head>
<body>
    <!-- Encabezado personalizado -->
    <jsp:include page="/Views/Shared/headerBody.jsp" />  

    <!-- Menú desplegable -->
    <div class="sidebar" id="sidebar">
        <!-- Contenido del menú -->
    </div>
    <!-- Botón del menú -->
    <button class="menu-btn" onclick="toggleSidebar()">
        <span class="material-icons">menu</span>
    </button>

    <!-- Contenido principal -->
    <main class="container">   
        <h5>Editar Producto</h5>
        <div class="card">
            <form action="Producto" method="post" onsubmit="return validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="IdProducto" value="<%=producto.getIdProducto()%>">  
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="Nombre" value="<%=producto.getNombre()%>" required class="validate" maxlength="30" placeholder="Ingrese el Nombre">
                        <label for="txtNombre">Nombre</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtDescripcion" type="text" name="Descripcion" value="<%=producto.getDescripcion()%>" required class="validate" maxlength="30" placeholder="Ingrese la Descripcion">
                        <label for="txtDescripcion">Descripcion</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtPrecio" type="text" name="Precio" value="<%=producto.getPrecio()%>" required class="validate" maxlength="30" placeholder="Ingrese el precio">
                        <label for="txtPrecio">Precio</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtExistencia" type="text" name="Existencia" value="<%=producto.getExistencia()%>" required class="validate" maxlength="30" placeholder="Ingrese la Existencia">
                        <label for="txtExistencia">Existencia</label>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Proveedor/select.jsp">                           
                            <jsp:param name="IdProveedor" value="<%=producto.getIdProveedor() %>" />  
                        </jsp:include>  
                        <span id="slProveedor_error" style="color:red" class="helper-text"></span>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Categoria/Select.jsp">                           
                            <jsp:param name="IdCategoria" value="<%=producto.getIdCategoria()%>" />  
                        </jsp:include>  
                        <span id="slCategoria_error" style="color:red" class="helper-text"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="submit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Producto" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </div>
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />   

    <!-- Script para el menú desplegable -->
    <script>
        function toggleSidebar() {
            document.getElementById('sidebar').classList.toggle('active');
        }
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <!-- Agrega aquí tus scripts personalizados si es necesario -->
</body>
</html>
