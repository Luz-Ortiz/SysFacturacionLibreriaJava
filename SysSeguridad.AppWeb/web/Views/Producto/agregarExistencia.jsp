<%-- 
    Document   : agregarExistencia
    Created on : 28 ago. 2023, 10:51:34
    Author     : MINEDUCYT
--%>

<%@page import="sysseguridad.entidadesdenegocio.Producto"%>
<% Producto producto = (Producto) request.getAttribute("producto");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Agregar Existencia</title>
    </head>
    <body>
         <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Existencia</h5>
            <form action="Producto" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="IdProducto" value="<%=producto.getIdProducto()%>">  
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtNombre" type="text" name="Nombre" value="<%=producto.getNombre()%>" required class="validate" maxlength="30" placeholder="Ingrese el Nombre" readonly>
                        <label for="txtNombre">Nombre</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtDescripcion" type="text" name="Descripcion" value="<%=producto.getDescripcion()%>" required class="validate" maxlength="30" placeholder="Ingrese la Descripcion" readonly>
                        <label for="txtDescripcion">Descripcion</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtPrecio" type="text" name="Precio" value="<%=producto.getPrecio()%>" required class="validate" maxlength="30" placeholder="Ingrese el precio" readonly>
                        <label for="txtPrecio">Precio</label>
                    </div>
                     <div class="input-field col l4 s12">
                        <input  id="txtExistencia" type="text" name="Existencia" value="<%=producto.getExistencia()%>" required class="validate" maxlength="30" placeholder="Ingrese la Existencia">
                        <label for="txtExistencia">Existencia</label>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Proveedor/select.jsp">                           
                            <jsp:param name="IdProveedor" value="<%=producto.getIdProveedor() %>" />  
                        </jsp:include>  
                        <span id="slProveedor_error" style="color:red" class="helper-text"></span>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Categoria/Select.jsp">                           
                            <jsp:param name="IdCategoria" value="<%=producto.getIdCategoria()%>" />  
                        </jsp:include>  
                        <span id="slCategoria_error" style="color:red" class="helper-text"></span>
                    </div>
                 </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Producto?accion=inventario" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />   
    
        

    </body>
</html>
