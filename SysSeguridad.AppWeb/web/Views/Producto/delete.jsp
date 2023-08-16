<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Producto"%>
<% Producto producto = (Producto) request.getAttribute("Producto");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Producto</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Eliminar Producto</h5>
            <form action="Producto" method="post">  
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="IdProducto" value="<%=producto.getIdProducto()%>">  
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  Id="txtNombre" type="text" value="<%=producto.getNombre()%>" disabled>
                        <label for="txtNombre">Nombre</label>
                    </div>                       
                    <div class="input-field col l4 s12">
                        <input  id="txtDescripcion" type="text" value="<%=producto.getDescripcion()%>" disabled>
                        <label for="txtApellido">Descripcion</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtPrecio" type="text" value="<%=producto.getPrecio()%>" disabled>
                        <label for="txtPrecio">Precio</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtExistencia" type="text" value="<%=producto.getExistencia()%>" disabled>
                        <label for="txtExistencia">Existencia</label>
                    </div>
                   <div class="input-field col l4 s12">
                        <input id="txtProveedor" type="text" value="<%=producto.getProveedor().getNombre()%>" disabled>
                        <label for="txtProveedor">Proveedor</label>
                    </div> 
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="Proveedor" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" /> 
                    
    </body>
</html>
