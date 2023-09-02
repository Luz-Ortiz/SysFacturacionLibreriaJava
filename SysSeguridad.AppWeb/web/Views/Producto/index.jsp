<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Producto"%>
<%@page import="sysseguridad.entidadesdenegocio.Proveedor"%>
<%@page import="sysseguridad.entidadesdenegocio.Categoria"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Producto> productos = (ArrayList<Producto>) request.getAttribute("productos");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (productos== null) {
        productos = new ArrayList();
    } else if (productos.size() > numReg) {
        double divNumPage = (double) productos.size() / (double) numReg;
        numPage = (int) Math.ceil(divNumPage);
    }
    String strTop_aux = request.getParameter("top_aux");
    int top_aux = 10;
    if (strTop_aux != null && strTop_aux.trim().length() > 0) {
        top_aux = Integer.parseInt(strTop_aux);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Buscar Producto</title>
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
    <div class="content" id="content">
        <div class="container">
            <h4 class="center-align">Buscar Producto</h4>
            <div class="card">
                <form action="Producto" method="post">
                    <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">
                    <div class="row">
                        <div class="input-field col l6 s12">
                            <input id="txtNombre" type="text" name="nombre">
                            <label for="txtNombre">Nombre</label>
                        </div>
                        <div class="input-field col l3 s6">
                            <button type="submit" class="btn waves-effect waves-light">Buscar<i class="material-icons left">search</i></button>
                        </div>
                        <div class="input-field col l3 s6">
                            <a href="Producto?accion=create" class="btn waves-effect waves-light"><i class="material-icons left">add</i>Crear</a>
                        </div>
                    </div>
                </form>
            </div>
            <table class="highlight centered responsive-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Descripción</th>
                        <th>Precio</th>
                        <th>Existencia</th>
                        <th>Proveedores</th>
                        <th>Categoría</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Producto producto : productos) { %>
                        <tr>
                            <td><%=producto.getIdProducto()%></td>
                            <td><%=producto.getNombre()%></td>
                            <td><%=producto.getDescripcion()%></td>
                            <td><%=producto.getPrecio()%></td>
                            <td><%=producto.getExistencia()%></td>
                            <td><%=producto.getProveedor().getNombre()%></td>
                            <td><%=producto.getCategoria().getNombre()%></td>
                            <td>
                                <a href="Producto?accion=edit&IdProducto=<%=producto.getIdProducto()%>" class="btn-action btn-floating btn-small waves-effect waves-light green"><i class="material-icons">edit</i></a>
                                <a href="Producto?accion=details&IdProducto=<%=producto.getIdProducto()%>" class="btn-action btn-floating btn-small waves-effect waves-light blue"><i class="material-icons">description</i></a>
                                <a href="Producto?accion=delete&IdProducto=<%=producto.getIdProducto()%>" class="btn-action btn-floating btn-small waves-effect waves-light red"><i class="material-icons">delete</i></a>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Script para el menú desplegable -->
    <script>
        function toggleSidebar() {
            document.getElementById('sidebar').classList.toggle('active');
        }
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</body>
</html>
