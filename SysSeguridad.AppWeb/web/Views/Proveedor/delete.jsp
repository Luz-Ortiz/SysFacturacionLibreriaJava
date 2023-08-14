<%-- 
    Document   : delete
    Created on : 14 ago. 2023, 10:37:36
    Author     : Luz Ortiz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Proveedor"%>
<% Proveedor proveedor = (Proveedor) request.getAttribute("proveedor");%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Eliminar Proveedor</title>
</head>
<body>
    <jsp:include page="/Views/Shared/headerBody.jsp" />
    <main class="container">
        <h5>Eliminar Proveedor</h5>
        <form action="Proveedor" method="post">
            <input type="hidden" name="accion" value="delete">
            <input type="hidden" name="idproveedor" value="<%=proveedor.getIdProveedor()%>">
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled id="txtCodProveedor" type="text" value="<%=proveedor.getCodProveedor()%>">
                    <label for="txtCodProveedor">CodProveedor</label>
                </div>
                <div class="input-field col l8 s12">
                    <input disabled id="txtNombre" type="text" value="<%=proveedor.getNombre()%>">
                    <label for="txtNombre">Nombre</label>
                </div>
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <button type="submit" class="waves-effect waves-light btn red"><i class="material-icons right">delete</i>Eliminar Proveedor</button>
                    <a href="Proveedor" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>
                </div>
            </div>
        </form>
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />
</body>
</html>

