<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Usuario"%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Producto</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Crear Producto</h5>
            <form action="Producto" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  IdProducto="txtNombre" type="text" name="nombre" required class="validate" maxlength="30">
                        <label for="txtNombre">Nombre</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtDescripcion" type="text" name="Descipcion" required class="validate" maxlength="30">
                        <label for="txtDescrpcion">Descripcion</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtPrecio" type="text" name="Precio" required class="validate" maxlength="30">
                        <label for="txtPrecio">Precio</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtExistencia" type="text" name="Existencia" required class="validate" maxlength="30">
                        <label for="txtExistencia">Nombre</label>
                    </div>
                    <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Proveedor/select.jsp">                           
                            <jsp:param name="IdProveedor" value="0" />  
                        </jsp:include>  
                        <span id="slProveedor_error" style="color:red" class="helper-text"></span>
                    </div>
                    <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Producto" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
         <jsp:include page="/Views/Shared/footerBody.jsp" />   
        <script>
            function validarFormulario() {
                var result = true;
                var slProveedor = document.getElementById("slProveedor");
                var slProveedor_error = document.getElementById("slProveedor_error");
                if (slProveedor.value == 0) {
                    slProveedor_error.innerHTML = "El Proveedor es obligatorio";
                    result = false;
                } else {
                    slProveedor_error.innerHTML = "";
                }
                
                return result;
            }
        </script>
    </body>
</html>
