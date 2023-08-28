
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Proveedor</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Crear Proveedor</h5>
            <form action="Proveedor" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">                
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  id="txtCodigo" type="text" name="Codigo" required class="validate" maxlength="">
                        <label for="txtCodigo">Codigo</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input id="txtNombre" type="text" name="Nombre" required class="validate" maxlength="30" placeholder="Ingrese su Nombre">
                        <label for="txtNombre">Nombre</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input id="txtTelefono" type="text" name="Telefono" required class="validate" maxlength="8" placeholder="Ingrese el Numero de Telefono">
                        <label for="txtTelefono">Telefono</label>
                    </div>
                    <div class="input-field col l4 s12">
                       <input id="txtDireccion" type="text" name="Direccion" required class="validate" maxlength="50" placeholder="Ingrese su Direccion">
                        <label for="txtDireccion">Direccion</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Proveedor" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>

