<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Categoria"%>
<% Categoria categoria = (Categoria) request.getAttribute("categoria");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Categoria</title>

    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Editar Categoria</h5>
            <form action="Categoria" method="post">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
                <input type="hidden" name="IdCategoria" value="<%=categoria.getIdCategoria()%>">   
                <div class="row">
                    <div class="input-field col l4 s12">
                        <input  IdCategoria="txtNombre" type="text" name="Nombre" value="<%=categoria.getNombre()%>" required class="validate" maxlength="30" placeholder="Ingrese el Nombre">
                        <label for="txtNombre">Nombre</label>
                    </div>     
                        
                    <div class="input-field col l4 s12">
                        <input  IdCategoria="txtDescripcion" type="text" name="Descripcion" value="<%=categoria.getDescripcion()%>" required class="validate" maxlength="30" placeholder="Ingrese el Nombre">
                        <label for="txtNombre">Descripcion</label>
                    </div>    
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Categoria" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
    </body>
</html>
