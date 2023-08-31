<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Categoria"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Categoria>categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (categorias == null) {
        categorias = new ArrayList();
    } else if (categorias.size() > numReg) {
        double divNumPage = (double) categorias.size() / (double) numReg;
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
    <title>Buscar Categoría</title>
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
        ./* Estilos para los botones de acción */
.btn-action {
    font-weight: bold;
    border-radius: 50%;
    padding: 6px;
    margin: 0 4px;
    transition: background-color 0.3s, color 0.3s;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 30px;
    height: 30px;
}
.btn-action i {
    font-size: 20px;
}
.btn-action:hover {
    background-color: #1565C0;
    color: white;
}
.btn-modify {
    background-color: #FFC107;
}
.btn-view {
    background-color: #4CAF50;
}
.btn-delete {
    background-color: #E53935;
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
            <h4 class="center-align">Buscar Categoría</h4>
            <div class="card">
                <form action="Categoria" method="post">
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
                            <a href="Categoria?accion=create" class="btn waves-effect waves-light"><i class="material-icons left">add</i>Crear</a>
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
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Categoria categoria : categorias) { %>
                        <tr>
                            <td><%=categoria.getIdCategoria()%></td>
                            <td><%=categoria.getNombre()%></td>
                            <td><%=categoria.getDescripcion()%></td>
                            <td>
                                <a href="Categoria?accion=edit&IdCategoria=<%=categoria.getIdCategoria()%>" class="btn-action btn-floating btn-small waves-effect waves-light blue" title="Modificar"><i class="material-icons">edit</i></a>
                                <a href="Categoria?accion=details&IdCategoria=<%=categoria.getIdCategoria()%>" class="btn-action btn-floating btn-small waves-effect waves-light green" title="Ver"><i class="material-icons">visibility</i></a>
                                <a href="Categoria?accion=delete&IdCategoria=<%=categoria.getIdCategoria()%>" class="btn-action btn-floating btn-small waves-effect waves-light red" title="Eliminar"><i class="material-icons">delete</i></a>
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
