<%-- 
    Document   : edit
    Created on : 14 ago. 2023, 10:39:35
    Author     : Luz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Proveedor"%>
<% Proveedor proveedor = (Proveedor) request.getAttribute("proveedor");%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Editar Proveedor</title>
</head>
<body>
    <jsp:include page="/Views/Shared/headerBody.jsp" />
    <main class="container">
        <h5>Editar Proveedor</h5>
        <form action="Proveedor" method="post">
            <input type="hidden" name="accion" value="update">
            <input type="hidden" name="idproveedor" value="<%=proveedor.getIdProveedor()%>">
            <div class="row">
                <div class="input-field col l4 s12">
                    <input id="txtCodProveedor" type="text" name="codProveedor" required class="validate" maxlength="10" value="<%=proveedor.getCodProveedor()%>">
                    <label for="txtCodProveedor">CodProveedor</label>
                </div>
                <div class="input-field col l4 s12">
                    <input id="txtNombre" type="text" name="nombre" required class="validate" maxlength="30" value="<%=proveedor.getNombre()%>">
                    <label for="txtNombre">Nombre</label>
                </div>
                <div class="input-field col l4 s12">
                    <input id="txtEmpresa" type="text" name="empresa" required class="validate" maxlength="50" value="<%=proveedor.getEmpresa()%>">
                    <label for="txtEmpresa">Empresa</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col l6 s12">
                    <input id="txtTelefono" type="text" name="telefono" required class="validate" maxlength="20" value="<%=proveedor.getTelefono()%>">
                    <label for="txtTelefono">Teléfono</label>
                </div>
                <div class="input-field col l6 s12">
                    <input id="txtDireccion" type="text" name="direccion" required class="validate" maxlength="100" value="<%=proveedor.getDireccion()%>">
                    <label for="txtDireccion">Dirección</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col l12 s12">
                    <textarea id="txtDescripcion" name="descripcion" class="materialize-textarea" required maxlength="200"><%=proveedor.getDescripcion()%></textarea>
                    <label for="txtDescripcion">Descripción</label>
                </div>
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <button type="submit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar Cambios</button>
                    <a href="Proveedor" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>
                </div>
            </div>
        </form>
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />
</body>
</html>
