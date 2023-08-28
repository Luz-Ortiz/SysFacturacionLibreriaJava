<%-- 
    Document   : Inventario
    Created on : 28 ago. 2023, 08:36:30
    Author     : MINEDUCYT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Producto"%>
<%@page import="sysseguridad.entidadesdenegocio.Categoria"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Producto> productos = (ArrayList<Producto>) request.getAttribute("productos");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (productos == null) {
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
<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Inventario</title>
</head>
<body>
    <jsp:include page="/Views/Shared/headerBody.jsp" />  
    <main class="container">   
        <h5>Inventario</h5>
        <form action="Producto" method="post">
            <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
            <div class="row">
                <div class="input-field col l4 s12">
                    <input  id="txtNombre" type="text" name="nombre">
                    <label for="txtNombre">Nombre</label>
                </div>
                <!-- Botón desplegable -->
                <a class="btn dropdown-trigger" href="#" data-target="dropdown1">Desplegable<i class="material-icons right">arrow_drop_down</i></a>

                <!-- Estructura del menú desplegable -->
                <ul id="dropdown1" class="dropdown-content">
                    <li>
                        <div class="input-field col l4 s12">
                            <jsp:include page="/Views/Categoria/Select.jsp">
                                <jsp:param name="IdCategoria" value="0" />
                            </jsp:include>
                        </div>
                    </li>
                    <li>
                        <div class="input-field col l4 s12">
                            <jsp:include page="/Views/Proveedor/select.jsp">
                                <jsp:param name="IdProveedor" value="0" />
                            </jsp:include>
                        </div>
                    </li>
                </ul>

            </div>
        </form>
        <div class="row">
            <div class="col l12 s12">
                <div style="overflow: auto">
                    <table class="paginationjs">
                        <thead>
                            <tr>                                     
                                <th>Nombre</th>  
                                <th>Categoria</th>                     
                                <th>Acciones</th> 

                            </tr>
                        </thead>                       
                        <tbody>
                            <% for (Producto Producto : productos) {
                                    int tempNumPage = numPage;
                                    if (numPage > 1) {
                                        countReg++;
                                        double divTempNumPage = (double) countReg / (double) numReg;
                                        tempNumPage = (int) Math.ceil(divTempNumPage);
                                    }

                            %>
                            <tr data-page="<%= tempNumPage%>">                                    
                                <td><%=Producto.getNombre()%></td>  
                                <td><%=Producto.getCategoria().getNombre()%></td> 
                                <td>
                                    <div style="display:flex">
                                        <a href="Producto?accion=edit&IdProducto=<%=Producto.getIdProducto()%>" title="agregarExistencias" class="waves-effect waves-light btn green">
                                            <i class="material-icons">edit</i>
                                        </a>
                                    </div>                                                                    
                                </td>                                   
                            </tr>
                            <%}%>                                                       
                        </tbody>
                    </table>
                </div>                  
            </div>
        </div>             
        <div class="row">
            <div class="col l12 s12">
                <jsp:include page="/Views/Shared/paginacion.jsp">
                    <jsp:param name="numPage" value="<%= numPage%>" />                        
                </jsp:include>
            </div>
        </div>
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />

</body>

</html>
