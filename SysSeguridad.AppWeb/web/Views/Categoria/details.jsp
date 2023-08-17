<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Categoria"%>
<% Categoria categoria = (Categoria) request.getAttribute("categoria");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Detalle de Categoria</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Detalle de Categoria</h5>
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  IdCategoria="txtNombre" type="text" value="<%=categoria.getNombre()%>">
                    <label for="txtNombre">Nombre</label>
                </div>
            </div>
            <div class="row">        
                     <div class="input-field col l4 s12">
                    <input disabled IdCategoria="txtDescripcion" type="text" value="<%=categoria.getDescripcion()%>">
                    <label for="Descripcion">Descripcion</label>
            </div> 
            <div class="row">
                <div class="col l12 s12">
                    <a href="Categoria?accion=edit&IdCategoria=<%=categoria.getIdCategoria()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                    <a href="Categoria" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>         
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
