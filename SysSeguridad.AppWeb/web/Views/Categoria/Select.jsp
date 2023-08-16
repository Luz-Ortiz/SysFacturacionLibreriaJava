<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Categoria"%>
<%@page import="sysseguridad.accesoadatos.CategoriaDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Categoria> categorias = CategoriaDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>
<select id="slCategoria" name="idCategoria">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Categoria categoria : categorias) {%>
    <option <%=(id == categoria.getIdCategoria()) ? "selected" : ""%>  value="<%=categoria.getIdCategoria()%>"><%= categoria.getNombre()%></option>
    <%}%>
</select>
<label for="idCategoria">Categoria</label>