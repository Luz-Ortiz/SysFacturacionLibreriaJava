<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Proveedor"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Proveedor> proveedores = (ArrayList<Proveedor>) request.getAttribute("proveedores");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if (proveedores == null) {
        proveedores = new ArrayList();
    } else if (proveedores.size() > numReg) {
        double divNumPage = (double) proveedores.size() / (double) numReg;
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
        <title>Buscar Proveedor</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Buscar Proveedor</h5>
            <form action="Proveedor" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <div class="row">
                   <div class="input-field col l6 s12">
                        <input  id="txtCodigo" type="text" name="codigo">
                        <label for="txtCodigo">Codigo</label>
                    </div>  
                    <div class="input-field col l6 s12">
                        <input  id="txtNombre" type="text" name="nombre">
                        <label for="txtNombre">Nombre</label>
                    </div>           
                    <div class="input-field col l6 s12">
                        <input  id="txtTelefono" type="text" name="telefono">
                        <label for="txtTelefono">Telefono</label>
                    </div>  
                    <div class="input-field col l6 s12">
                        <input  id="txtDireccion" type="text" name="direccion">
                        <label for="txtDireccion">Direccion</label>
                    </div>  
                    <div class="input-field col l3 s12">   
                        <jsp:include page="/Views/Shared/selectTop.jsp">
                            <jsp:param name="top_aux" value="<%=top_aux%>" />                        
                        </jsp:include>                        
                    </div> 
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">search</i>Buscar</button>
                        <a href="Proveedor?accion=create" class="waves-effect waves-light btn blue"><i class="material-icons right">add</i>Crear</a>                          
                    </div>
                </div>
            </form>

            <div class="row">
                <div class="col l12 s12">
                    <div style="overflow: auto">
                        <table class="paginationjs">
                            <thead>
                                <tr>
                                    <th>Codigo</th> 
                                    <th>Nombre</th> 
                                    <th>Telefono</th> 
                                    <th>Direccion</th> 
                                    <th>Acciones</th>
                                </tr>
                            </thead>                       
                            <tbody>                           
                                <% for (Proveedor proveedor : proveedores) {
                                        int tempNumPage = numPage;
                                        if (numPage > 1) {
                                            countReg++;
                                            double divTempNumPage = (double) countReg / (double) numReg;
                                            tempNumPage = (int) Math.ceil(divTempNumPage);
                                        }
                                %>
                                <tr data-page="<%= tempNumPage%>">
                                    <td><%=proveedor.getCodigo()%></td> 
                                    <td><%=proveedor.getNombre()%></td>
                                    <td><%=proveedor.getTelefono()%></td>
                                    <td><%=proveedor.getDireccion()%></td>
                                    <td>
                                        <div style="display:flex">
                                            <a href="Proveedor?accion=edit&IdProveedor=<%=proveedor.getIdProveedor()%>" title="Modificar" class="waves-effect waves-light btn green">
                                                <i class="material-icons">edit</i>
                                            </a>
                                            <a href="Proveedor?accion=details&IdProveedor=<%=proveedor.getIdProveedor()%>" title="Ver" class="waves-effect waves-light btn blue">
                                                <i class="material-icons">description</i>
                                            </a>
                                            <a href="Proveedor?accion=delete&IdProveedor=<%=proveedor.getIdProveedor()%>" title="Eliminar" class="waves-effect waves-light btn red">
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