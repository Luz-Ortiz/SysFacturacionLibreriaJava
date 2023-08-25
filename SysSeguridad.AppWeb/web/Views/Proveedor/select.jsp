<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Proveedor"%>
<%@page import="sysseguridad.accesoadatos.ProveedorDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Proveedor> proveedores = ProveedorDAL.obtenerTodos();
    int IdProveedor = Integer.parseInt(request.getParameter("IdProveedor"));
%>
<select id="slProveedor" name="IdProveedor">
    <option <%=(IdProveedor == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Proveedor proveedor : proveedores) {%>
    <option <%=(IdProveedor == proveedor.getIdProveedor()) ? "selected" : ""%>  value="<%=proveedor.getIdProveedor()%>"><%= proveedor.getNombre()%></option>
    <%}%>
</select>
<label for="IdProveedor">Proveedor</label>
