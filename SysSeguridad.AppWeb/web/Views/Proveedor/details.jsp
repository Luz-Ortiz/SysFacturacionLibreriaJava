<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Proveedor"%>
<% Proveedor proveedor = (Proveedor) request.getAttribute("proveedor");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalle de Proveedor</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalle de Proveedor</h5>
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtCodigo" type="text" value="<%=proveedor.getCodigo()%>">
                    <label for="txtCodigo">Codigo</label>
                </div> 
                <div class="input-field col l4 s12">
                    <input disabled  id="txtNombre" type="text" value="<%=proveedor.getNombre()%>">
                    <label for="txtNombre">Nombre</label>
                </div>  
                <div class="input-field col l4 s12">
                    <input disabled  id="txtTelefono" type="text" value="<%=proveedor.getTelefono()%>">
                    <label for="txtTelefono">Telefono</label>
                </div> 
                <div class="input-field col l4 s12">
                    <input disabled  id="txtDireccion" type="text" value="<%=proveedor.getDireccion()%>">
                    <label for="txtDireccion">Direccion</label>
                </div>     
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <a href="Proveedor?accion=edit&IdProveedor=<%=proveedor.getIdProveedor()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                    <a href="Proveedor" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>         
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
