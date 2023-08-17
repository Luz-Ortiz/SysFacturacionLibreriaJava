

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Proveedor"%>
<%@page import="sysseguridad.accesoadatos.ProveedorDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Proveedor> proveedores = ProveedorDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>
<select id="slProveedor" name="idProveedor">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Proveedor proveedor : proveedores) {%>
    <option <%=(id == proveedor.getIdProveedor()) ? "selected" : ""%>  value="<%=proveedor.getIdProveedor()%>"><%= proveedor.getNombre()%></option>
    <%}%>
</select>
<label for="idProveedor">Proveedor</label>
