<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <jsp:include page="/Views/Shared/title.jsp" />
    <title>Eliminar Categoría</title>
    </head>
    <body>
      <jsp:include page="/Views/Shared/headerBody.jsp" />  
    <main class="container">   
        <h5>Eliminar Categoría</h5>
        <form action="Categoria" method="post">
            <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
            <input type="hidden" name="idCategoria" value="<%=request.getAttribute("idCategoria")%>">              
            <div class="row">
                <div class="input-field col l4 s12">
                    <label for="txtNombre">Nombre</label>
                    <input  id="txtNombre" type="text" name="nombre" required class="validate" maxlength="30" value="<%=request.getAttribute("nombre")%>" disabled>
                </div>                      
                <div class="input-field col l4 s12">
                    <label for="txtDescripcion">Descripción</label>
                    <input  id="txtDescripcion" type="text" name="descripcion" required class="validate" maxlength="30" value="<%=request.getAttribute("descripcion")%>" disabled>
                </div>  
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <button type="submit" class="waves-effect waves-light btn red"><i class="material-icons right">delete</i>Eliminar</button>
                    <a href="Categoria" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>
        </form>          
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" /> 
    </body>
</html>
