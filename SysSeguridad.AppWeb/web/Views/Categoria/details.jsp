<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Categoria"%>
<% Categoria categoria = (Categoria) request.getAttribute("categoria");%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Detalle de Categoria</title>
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
        /* Estilos para los botones de acción */
        .btn-action:hover {
            background-color: #1E88E5 !important; /* Cambia el color en el hover */
        }
        .btn-action i {
            font-weight: bold; /* Agrega un peso más fuerte a los iconos */
        }
        /* Estilos para el menú */
        .sidebar {
            height: 100%;
            width: 250px;
            position: fixed;
            top: -20px; /* Ajusta el margen superior */
            left: -250px;
            background: linear-gradient(to right, #2E8B57, #87CEEB);
            overflow-x: hidden;
            transition: 0.5s;
            padding-top: 40px; /* Ajusta el margen superior */
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
        <h4 class="center-align">Detalle de Categoria</h4>
        <div class="card">
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled id="txtNombre" type="text" value="<%=categoria.getNombre()%>">
                    <label for="txtNombre">Nombre</label>
                </div>
            </div>
            <div class="row">        
                <div class="input-field col l4 s12">
                    <input disabled id="txtDescripcion" type="text" value="<%=categoria.getDescripcion()%>">
                    <label for="txtDescripcion">Descripcion</label>
                </div> 
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <a href="Categoria?accion=edit&IdCategoria=<%=categoria.getIdCategoria()%>" class="waves-effect waves-light btn blue">
                        <i class="material-icons right">edit</i>Ir modificar
                    </a>                        
                    <a href="Categoria" class="waves-effect waves-light btn blue">
                        <i class="material-icons right">list</i>Cancelar
                    </a>                          
                </div>
            </div>         
        </div>
    </main>

    <!-- Script para el menú desplegable -->
    <script>
        function toggleSidebar() {
            document.getElementById('sidebar').classList.toggle('active');
        }
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
