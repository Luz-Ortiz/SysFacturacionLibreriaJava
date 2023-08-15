<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Producto"%>
<%@page import="sysseguridad.entidadesdenegocio.Proveedor"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Producto> producto = (ArrayList<Producto>) request.getAttribute("producto");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (producto== null) {
        producto = new ArrayList();
    } else if (producto.size() > numReg) {
        double divNumPage = (double) producto.size() / (double) numReg;
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                        <input  id="txtDescripcion" type="text" name="apellido">
                        <label for="txtApellido">Descripcion</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtPrecio" type="text" name="apellido">
                        <label for="txtApellido">Precio</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtExistentecia" type="text" name="apellido">
                        <label for="txtApellido">Existencia</label>
                    </div> 
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Producto/select.jsp">                           
                            <jsp:param name="IdProducto" value="0" />  
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
                                <th>Proveedor</th>   
                                <th>Categoria</th>   
                            </tr>
                        </thead>                       
                    <tbody>
                <% for (Producto Producto : producto) {
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
                       <td><%=Producto.getProveedor().getNombre()%></td> 
                       <td><%=Producto.getCategoria()%></td> 
                       <td>
                    <div style="display:flex">
                    <a href="Producto?accion=edit&id=<%=Producto.getIdProducto()%>" title="Modificar" class="waves-effect waves-light btn green">
                        <i class="material-icons">edit</i>
                    </a>
                    <a href="Producto?accion=details&id=<%=Producto.getIdProducto()%>" title="Ver" class="waves-effect waves-light btn blue">
                        <i class="material-icons">description</i>
                    </a>
                    <a href="Producto?accion=delete&id=<%=Producto.getIdProducto()%>" title="Eliminar" class="waves-effect waves-light btn red">
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
