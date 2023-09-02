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
    <title>Buscar Categoria</title>
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
    <div class="content" id="content">
        <div class="container">
            <h4 class="center-align">Buscar Categoria</h4>
            <div class="card">
                <form action="Categoria" method="post">
                    <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">
                    <div class="row">
                        <div class="input-field col l6 s12">
                            <input id="txtNombre" type="text" name="nombre">
                            <label for="txtNombre">Nombre</label>
                        </div>
                        <div class="input-field col l3 s12">
                            <button type="submit" class="btn waves-effect waves-light blue">
                                <i class="material-icons left">search</i>Buscar
                            </button>
                        </div>
                        <div class="input-field col l3 s12">
                            <a href="Categoria?accion=create" class="btn waves-effect waves-light blue">
                                <i class="material-icons left">add</i>Crear
                            </a>
                        </div>
                    </div>
                </form>
            </div>
            <table class="striped centered responsive-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Descripcion</th>
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
                                <a href="Categoria?accion=edit&IdCategoria=<%=categoria.getIdCategoria()%>" class="btn-action btn-floating btn-small waves-effect waves-light green">
                                    <i class="material-icons">edit</i>
                                </a>
                                <a href="Categoria?accion=details&IdCategoria=<%=categoria.getIdCategoria()%>" class="btn-action btn-floating btn-small waves-effect waves-light blue">
                                    <i class="material-icons">description</i>
                                </a>
                                <a href="Categoria?accion=delete&IdCategoria=<%=categoria.getIdCategoria()%>" class="btn-action btn-floating btn-small waves-effect waves-light red">
                                    <i class="material-icons">delete</i>
                                </a>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
            <div class="row">
                <div class="col l12 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%= numPage%>" />
                    </jsp:include>
                </div>
            </div>
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
