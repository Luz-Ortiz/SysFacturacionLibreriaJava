<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Proveedor"%>
<% Proveedor proveedor = (Proveedor) request.getAttribute("proveedor");%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Detalle de Proveedor</title>
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
            animation: fadeIn 0.5s ease-in;
        }
        @keyframes fadeIn {
            0% {
                opacity: 0;
                transform: translateY(-20px);
            }
            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }
        /* Estilos para los botones */
        .btn-action {
            margin-right: 10px;
        }
        .btn-action:hover {
            background-color: #1E88E5 !important;
        }
        .btn-action i {
            font-weight: bold;
        }
        /* Estilos para el menú */
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
        /* Otros estilos */
        /* ... (si tienes otros estilos personalizados) */
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
        <h5>Detalle de Proveedor</h5>
        <div class="row">
            <div class="input-field col l4 s12">
                <input disabled id="txtCodigo" type="text" value="<%=proveedor.getCodigo()%>">
                <label for="txtCodigo">Código</label>
            </div> 
            <div class="input-field col l4 s12">
                <input disabled id="txtNombre" type="text" value="<%=proveedor.getNombre()%>">
                <label for="txtNombre">Nombre</label>
            </div>  
            <div class="input-field col l4 s12">
                <input disabled id="txtTelefono" type="text" value="<%=proveedor.getTelefono()%>">
                <label for="txtTelefono">Teléfono</label>
            </div> 
            <div class="input-field col l4 s12">
                <input disabled id="txtDireccion" type="text" value="<%=proveedor.getDireccion()%>">
                <label for="txtDireccion">Dirección</label>
            </div>     
        </div>
        <div class="row">
            <div class="col l12 s12">
                <a href="Proveedor?accion=edit&IdProveedor=<%=proveedor.getIdProveedor()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                <a href="Proveedor" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
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
