<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Producto"%>
<%@page import="sysseguridad.accesoadatos.ProductoDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Producto> productos = ProductoDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>
<select id="slProducto" name="IdProducto">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Producto producto : productos) {%>
    <option <%=(id == producto.getIdProducto()) ? "selected" : ""%>  value="<%=producto.getIdProducto()%>"><%= producto.getNombre()%></option>
    <%}%>
</select>
<label for="IdProducto">Producto</label>
