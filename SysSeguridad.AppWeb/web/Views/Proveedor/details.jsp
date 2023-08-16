<%-- 
    Document   : details
    Created on : 14 ago. 2023, 10:37:10
    Author     : Luz Ortiz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Proveedor"%>
<% Proveedor proveedor = (Proveedor) request.getAttribute("proveedor");%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Detalles de Proveedor</title>
</head>
<body>
    <jsp:include page="/Views/Shared/headerBody.jsp" />
    <main class="container">
        <h5>Detalles de Proveedor</h5>
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
            <div class="input-field col l6 s12">
                <input disabled id="txtEmpresa" type="text" value="<%=proveedor.getEmpresa()%>">
                <label for="txtEmpresa">Empresa</label>
            </div>
            <div class="input-field col l6 s12">
                <input disabled id="txtTelefono" type="text" value="<%=proveedor.getTelefono()%>">
                <label for="txtTelefono">Teléfono</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col l12 s12">
                <textarea disabled id="txtDescripcion" class="materialize-textarea"><%=proveedor.getDescripcion()%></textarea>
                <label for="txtDescripcion">Descripción</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col l12 s12">
                <input disabled id="txtDireccion" type="text" value="<%=proveedor.getDireccion()%>">
                <label for="txtDireccion">Dirección</label>
            </div>
        </div>
        <div class="row">
            <div class="col l12 s12">
                <a href="Proveedor?accion=edit&idproveedor=<%=proveedor.getIdProveedor()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Editar Proveedor</a>
                <a href="Proveedor" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Volver a la Lista</a>
            </div>
        </div>
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />
</body>
</html>

