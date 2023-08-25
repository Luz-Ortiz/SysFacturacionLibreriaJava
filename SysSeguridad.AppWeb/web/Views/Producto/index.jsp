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
    </head>
    <body>
         <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Buscar Producto</h5>
            <form action="Producto" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="nombre">
                        <label for="txtNombre">Nombre</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtDescripcion" type="text" name="Descripcion">
                        <label for="txtDescrpcion">Descripcion</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtPrecio" type="text" name="Precio">
                        <label for="txtPrecio">Precio</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtExistentecia" type="text" name="Existencia">
                        <label for="txtExistencia">Existencia</label>
                    </div> 
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Categoria/Select.jsp">                           
                            <jsp:param name="IdCategoria" value="0" />  
                        </jsp:include>                        
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Proveedor/select.jsp">                           
                            <jsp:param name="IdProveedor" value="0" />  
                        </jsp:include>                        
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Shared/selectTop.jsp">
                            <jsp:param name="top_aux" value="<%=top_aux%>" />                        
                        </jsp:include>                        
                    </div> 
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">search</i>Buscar</button>
                        <a href="Producto?accion=create" class="waves-effect waves-light btn blue"><i class="material-icons right">add</i>Crear</a>                          
                    </div>
                    
                </div>
            </form>
                
                
         <div class="row">
            <div class="col l12 s12">
                <div style="overflow: auto">
                    <table class="paginationjs">
                        <thead>
                            <tr>                                     
                                <th>Nombre</th>  
                                <th>Descripcion</th> 
                                <th>Precio</th>  
                                <th>Existencia</th> 
                                 <th>Proveedores</th>
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
                       <td><%=Producto.getDescripcion()%></td>
                       <td><%=Producto.getPrecio()%></td> 
                        <td><%=Producto.getExistencia()%></td> 
                       <td><%=Producto.getProveedor().getNombre()%></td> 
                       <td><%=Producto.getCategoria().getNombre()%></td> 
                       <td>
                    <div style="display:flex">
                    <a href="Producto?accion=edit&IdProducto=<%=Producto.getIdProducto()%>" title="Modificar" class="waves-effect waves-light btn green">
                        <i class="material-icons">edit</i>
                    </a>
                    <a href="Producto?accion=details&IdProducto=<%=Producto.getIdProducto()%>" title="Ver" class="waves-effect waves-light btn blue">
                        <i class="material-icons">description</i>
                    </a>
                    <a href="Producto?accion=delete&IdProducto=<%=Producto.getIdProducto()%>" title="Eliminar" class="waves-effect waves-light btn red">
                    <i class="material-icons">delete</i>
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
