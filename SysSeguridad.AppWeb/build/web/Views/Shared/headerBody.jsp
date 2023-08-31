<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.appweb.utils.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Menú Creativo</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .sidebar {
            height: 100%;
            width: 250px;
            position: fixed;
            top: -20px; /* Ajusta el margen superior */
            left: -250px;
            background-color: #84c3be; /* Nuevo color turquesa */
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

        .content {
            margin-left: 0;
            padding: 20px;
            transition: 0.5s;
        }

        .content.active {
            margin-left: 250px;
        }

        .menu-btn {
            position: fixed;
            top: 20px;
            left: 20px;
            background-color: #84c3be; /* Nuevo color turquesa */
            color: white;
            border: none;
            padding: 10px 15px;
            font-size: 20px;
            cursor: pointer;
            transition: 0.3s;
        }

        .menu-btn:hover {
            background-color: #87CEFA;
        }

        .material-icons {
            vertical-align: middle;
            margin-right: 10px;
        }
    </style>
</head>
<body>

<div class="sidebar" id="sidebar">
    <a href="javascript:void(0)" class="close-btn" onclick="closeSidebar()">
        <span class="material-icons">close</span>
    </a>
    <% if (SessionUser.isAuth(request)) {  %>
    <a href="Home"><span class="material-icons">home</span> Inicio</a>
    <a href="Usuario"><span class="material-icons">person</span> Usuario</a>
    <a href="Rol"><span class="material-icons">perm_identity</span> Rol</a>
    <a href="Categoria"><span class="material-icons">category</span> Categoría</a>
    <a href="Proveedor"><span class="material-icons">store</span> Proveedor</a>
    <a href="Producto"><span class="material-icons">shopping_cart</span> Producto</a>
    <a href="Producto?accion=inventario"><span class="material-icons">inventory</span> Inventario</a>
    <a href="Usuario?accion=cambiarpass"><span class="material-icons">vpn_key</span> Cambiar Contraseña</a>
    <a href="Usuario?accion=login"><span class="material-icons">exit_to_app</span> Cerrar Sesión</a>
    <%}%>
</div>

<button class="menu-btn" onclick="toggleSidebar()">
    <span class="material-icons">menu</span>
</button>

<div class="content" id="content">
    <!-- Contenido de la Página -->
</div>

<script>
    function toggleSidebar() {
        document.getElementById("sidebar").classList.toggle("active");
        document.getElementById("content").classList.toggle("active");
    }

    function closeSidebar() {
        document.getElementById("sidebar").classList.remove("active");
        document.getElementById("content").classList.remove("active");
    }
</script>

</body>
</html>
