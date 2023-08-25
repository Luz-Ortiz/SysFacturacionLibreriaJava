<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sysseguridad.entidadesdenegocio.Categoria"%>
<%@page import="sysseguridad.accesoadatos.CategoriaDAL"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Categoria> categorias = CategoriaDAL.obtenerTodos();
    int IdCategoria = Integer.parseInt(request.getParameter("IdCategoria"));
%>
<select id="slCategoria" name="IdCategoria">
    <option <%=(IdCategoria == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Categoria categoria : categorias) {%>
    <option <%=(IdCategoria == categoria.getIdCategoria()) ? "selected" : ""%>  value="<%=categoria.getIdCategoria()%>"><%= categoria.getNombre()%></option>
    <%}%>
</select>
<label for="IdCategoria">Categoria</label>