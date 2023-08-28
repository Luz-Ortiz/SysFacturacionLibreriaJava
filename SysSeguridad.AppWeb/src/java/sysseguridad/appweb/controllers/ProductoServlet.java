
package sysseguridad.appweb.controllers;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;  // Importar la clase ArrayList
import sysseguridad.accesoadatos.ProductoDAL;
import sysseguridad.accesoadatos.ProveedorDAL;
import sysseguridad.accesoadatos.CategoriaDAL;// Importar la clase ProductoDAL de la capa de acceso a datos
import sysseguridad.entidadesdenegocio.Producto;// Importar la clase Producto de la capa de entidades de negocio
import sysseguridad.entidadesdenegocio.Proveedor;// Importar la clase Producto de la capa de entidades de negocio
import sysseguridad.entidadesdenegocio.Categoria;// Importar la clase Producto de la capa de entidades de negocio
import sysseguridad.appweb.utils.*;// Importar las clases SessionUser, Utilidad del paquete de utils



/**
 * En este Servlet, vamos a recibir todas las peticiones get y post que se
 * realice al Servlet Producto. Aprender conceptos básicos de servlets
 * http://www.jtech.ua.es/j2ee/2002-2003/modulos/servlets/apuntes/apuntes1_1.htm
 * Actualizamos la anotación WebServlet para cambiar el atributo urlPatterns
 * para acceder al Servlet Producto utilizando la siguiente Url: la del sitio web mas
 * /Producto
 */
@WebServlet(name = "ProductoServlet", urlPatterns = {"/Producto"})
public class ProductoServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="Metodos para procesar las solicitudes get o post del Servlet">
    /**
     * En este método vamos a obtener la información enviada, en una peticion
     * get o post, obteniendo los datos de los parámetros enviados de un
     * formulario o la url del navegador, enviar esa información a una instancia
     * de la entidad Producto
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get o post enviada al servlet Producto
     * @return Producto devolver la instancia de la entidad Producto con los valores
     * obtenidos del request
     */
      private Producto obtenerProducto(HttpServletRequest request) {
        // Obtener el parámetro accion del request
        String accion = Utilidad.getParameter(request, "accion", "index");
        Producto producto = new Producto();
         if (accion.equals("create") == false) { // Si la accion no es create.
            // Obtener el parámetro id del request  y asignar ese valor a la propiedad Id de Rol.
            producto.setIdProducto(Integer.parseInt(Utilidad.getParameter(request, "IdProducto", "0")));
        }
        producto.setIdCategoria(Integer.parseInt(Utilidad.getParameter(request, "IdCategoria", "0")));
        producto.setIdProveedor(Integer.parseInt(Utilidad.getParameter(request, "IdProveedor", "0")));
        // Obtener el parámetro nombre del request  y asignar ese valor a la propiedad Nombre de Usuario.
        producto.setNombre(Utilidad.getParameter(request, "Nombre", ""));
        // Obtener el parámetro apellido del request  y asignar ese valor a la propiedad Apellido de Usuario.
        producto.setDescripcion(Utilidad.getParameter(request, "Descripcion", ""));
        // Obtener el parámetro login del request  y asignar ese valor a la propiedad Login de Usuario.
        producto.setPrecio(Integer.parseInt(Utilidad.getParameter(request, "Precio", "0")));
        // Obtener el parámetro idRol del request  y asignar ese valor a la propiedad IdRol de Usuario.
        producto.setExistencia(Integer.parseInt(Utilidad.getParameter(request, "Existencia", "0")));
       

        
        if (accion.equals("index")) {
            // Obtener el parámetro top_aux del request  y asignar ese valor a la propiedad Top_aux de Usuario.
            producto.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            producto.setTop_aux(producto.getTop_aux() == 0 ? Integer.MAX_VALUE : producto.getTop_aux());
        }
      
        return producto;
    }


    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Producto, y el parámetro accion sea igual index. Este método se encargara de
     * enviar los datos de los Producto al jsp de index de Producto.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Producto
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Producto que utlizaremos para enviar el jsp
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
      private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Producto producto = new Producto(); // Crear una instancia  de la entidad de Usuario.
            producto.setTop_aux(10); // Agregar el Top_aux con el valor de 10 a la propiedad Top_aux de Usuario.
            // Ir a la capa de acceso a datos y buscar los registros de Usuario y asociar Rol.
            ArrayList<Producto> productos = ProductoDAL.buscarIncluirCategoriaYProveedor(producto);
            // Enviar los usuarios al jsp utilizando el request.setAttribute con el nombre del atributo usuario.
            request.setAttribute("productos", productos);
            // Enviar el Top_aux de Usuario al jsp utilizando el request.setAttribute con el nombre del atributo top_aux.
            request.setAttribute("top_aux", producto.getTop_aux());
            request.getRequestDispatcher("Views/Producto/index.jsp").forward(request, response); // Direccionar al jsp index de Usuario.
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); // Enviar al jsp de error si hay un Exception.
        }
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post, al servlet
     * Producto , y el parámetro accion sea igual index. Este método se encargara de
     * enviar los datos de los Producto al jsp de index de Producto
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Producto
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
  private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Producto producto = obtenerProducto(request); // Llenar la instancia de Usuario con los parámetros enviados en el request.
            // Ir a la capa de acceso a datos y buscar los registros de Usuario y asociar Rol.
            ArrayList<Producto> productos = ProductoDAL.buscarIncluirCategoriaYProveedor(producto);
            // Enviar los usuarios al jsp utilizando el request.setAttribute con el nombre del atributo usuario.
            request.setAttribute("produtos", productos);
            // Enviar el Top_aux de Usuario al jsp utilizando el request.setAttribute con el nombre del atributo top_aux.
            request.setAttribute("top_aux", producto.getTop_aux());
            request.getRequestDispatcher("Views/Producto/index.jsp").forward(request, response); // Direccionar al jsp index de Usuario.
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); // Enviar al jsp de error si hay un Exception.
        }
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Producto, y el parámetro accion sea igual create.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Producto
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // direccionar al jsp create de Producto
        request.getRequestDispatcher("Views/Producto/create.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Producto , y el parámetro accion sea igual create.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Producto
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
        private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Producto producto = obtenerProducto(request); // Llenar la instancia de Usuario con los parámetros enviados en el request
            // Enviar los datos de Usuario a la capa de accesoa a datos para que lo almacene en la base de datos el registro.
            int result = ProductoDAL.crear(producto);
            if (result != 0) { // Si el result es diferente a cero significa que los datos fueron ingresados correctamente.
                // Enviar el atributo accion con el valor index al jsp de index
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex para que nos direcciones al jsp index
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro registrar un nuevo registro
                Utilidad.enviarError("No se logro registrar un nuevo registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }

    }

    /**
     * En este método obtiene por Id un Producto desde la capa de acceso a datos el
     * Id se captura del request que se envio al servlet de Producto
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get o post enviada al servlet Producto
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
   private void requestObtenerPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        Producto producto = obtenerProducto(request);
        Producto producto_result = ProductoDAL.obtenerPorId(producto);
        
        if (producto_result.getIdProducto()> 0) {
          
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(producto_result.getIdCategoria());
            producto_result.setCategoria(CategoriaDAL.obtenerPorId(categoria)); // Suponiendo que tienes un método similar en CategoriaDAL
            
            Proveedor proveedor = new Proveedor();
            proveedor.setIdProveedor(producto_result.getIdProveedor());
            producto_result.setProveedor(ProveedorDAL.obtenerPorIdProveedor(proveedor)); // Suponiendo que tienes un método similar en ProveedorDAL
            
            request.setAttribute("producto", producto_result);
        } else {
            Utilidad.enviarError("El IdProducto:" + producto_result.getIdProducto() + " no existe en la tabla de Producto", request, response);
        }
    } catch (Exception ex) {
        Utilidad.enviarError(ex.getMessage(), request, response);
    }
}

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Producto , y el parámetro accion sea igual edit.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Producto
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviar el Producto al jsp de edit que se obtiene por IdProducto
        requestObtenerPorId(request, response);
        // Direccionar al jsp edit de Producto
        request.getRequestDispatcher("Views/Producto/edit.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Producto , y el parámetro accion sea igual edit.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Producto
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
     private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Producto producto = obtenerProducto(request); // Llenar la instancia de Usuario con los parámetros enviados en el request.
            // Enviar los datos de Usuario a la capa de accesoa a datos para modificar el registro.
            int result = ProductoDAL.modificar(producto);
            if (result != 0) { // Si el result es diferente a cero significa que los datos fueron modificado correctamente.
                // Enviar el atributo accion con el valor index al jsp de index.
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex para que nos direcciones al jsp index.
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro actualizar el registro.
                Utilidad.enviarError("No se logro actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Producto , y el parámetro accion sea igual details.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Producto
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviar el usuario al jsp de details que se obtiene por Id.
        requestObtenerPorId(request, response);
        // Direccionar al jsp details de Usuario.
        request.getRequestDispatcher("Views/Producto/details.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Producto , y el parámetro accion sea igual delete.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Producto
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
      private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviar el usuario al jsp de delete que se obtiene por Id.
        requestObtenerPorId(request, response);
        // Direccionar al jsp delete de Usuario.
        request.getRequestDispatcher("Views/Producto/delete.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Producto , y el parámetro accion sea igual delete.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Producto
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
     private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Producto producto = obtenerProducto(request); // Llenar la instancia de Usuario con los parámetros enviados en el request.
            // Enviar los datos de Usuario a la capa de accesoa a datos para que elimine el registro.
            int result = ProductoDAL.eliminar(producto);
            if (result != 0) { // Si el result es diferente a cero significa que los datos fueron eliminados correctamente.
                // Enviar el atributo accion con el valor index al jsp de index.
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);  // Ir al método doGetRequestIndex para que nos direccione al jsp index.
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro eliminar el registro.
                Utilidad.enviarError("No se logro eliminar el registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception.
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

      private void doGetRequestInventario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Producto producto = new Producto(); // Crear una instancia  de la entidad de Usuario.
            producto.setTop_aux(10); // Agregar el Top_aux con el valor de 10 a la propiedad Top_aux de Usuario.
            // Ir a la capa de acceso a datos y buscar los registros de Usuario y asociar Rol.
            ArrayList<Producto> productos = ProductoDAL.buscarIncluirCategoriaYProveedor(producto);
            // Enviar los usuarios al jsp utilizando el request.setAttribute con el nombre del atributo usuario.
            request.setAttribute("productos", productos);
            // Enviar el Top_aux de Usuario al jsp utilizando el request.setAttribute con el nombre del atributo top_aux.
            request.setAttribute("top_aux", producto.getTop_aux());
            request.getRequestDispatcher("Views/Producto/inventario.jsp").forward(request, response); // Direccionar al jsp index de Usuario.
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); // Enviar al jsp de error si hay un Exception.
        }
    }
      
     private void doPostRequestInventario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Producto producto = obtenerProducto(request); // Llenar la instancia de Usuario con los parámetros enviados en el request.
            // Ir a la capa de acceso a datos y buscar los registros de Usuario y asociar Rol.
            ArrayList<Producto> productos = ProductoDAL.buscarIncluirCategoriaYProveedor(producto);
            // Enviar los usuarios al jsp utilizando el request.setAttribute con el nombre del atributo usuario.
            request.setAttribute("produtos", productos);
            // Enviar el Top_aux de Usuario al jsp utilizando el request.setAttribute con el nombre del atributo top_aux.
            request.setAttribute("top_aux", producto.getTop_aux());
            request.getRequestDispatcher("Views/Producto/inventario.jsp").forward(request, response); // Direccionar al jsp index de Usuario.
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); // Enviar al jsp de error si hay un Exception.
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Este método es un override al método de la clase HttpServlet para recibir
     * todas las peticiones get que se realice al Servlet Producto
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Producto
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Producto que utlizaremos para enviar el jsp al
     * navegador web
     * @throws ServletException devolver una exception de un servlet
     * @throws IOException devolver una exception al leer o escribir un archivo
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Utilizar el método authorize de la clase SessionUser para validar que solo usuario con permiso
        // puedan acceder al servlet de Producto. Todo el codigo que este dentro  expresion Lambda, se ejecutara si el usuario tiene permitido
        // acceder a este Servlet 
        SessionUser.authorize(request, response, () -> { // Expresion Lambda  
            // Obtener el parámetro accion del request
            String accion = Utilidad.getParameter(request, "accion", "index");
            // Hacer un switch para decidir a cual metodo ir segun el valor que venga en el parámetro de accion.
            switch (accion) {
                case "index":
                    // Enviar el atributo accion al jsp de index.
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response); // Ir al método doGetRequestIndex.
                    break;
                case "create":
                    // Enviar el atributo accion al jsp de create.
                    request.setAttribute("accion", accion);
                    doGetRequestCreate(request, response); // Ir al metodo doGetRequestCreate.
                    break;
                case "edit":
                    // Enviar el atributo accion al jsp de edit.
                    request.setAttribute("accion", accion);
                    doGetRequestEdit(request, response);// Ir al metodo doGetRequestEdit.
                    break;
                case "delete":
                    // Enviar el atributo accion al jsp de delete.
                    request.setAttribute("accion", accion);
                    doGetRequestDelete(request, response); // Ir al metodo doGetRequestDelete.
                    break;
                case "details":
                    // Enviar el atributo accion al jsp de details.
                    request.setAttribute("accion", accion);
                    doGetRequestDetails(request, response); // Ir al metodo doGetRequestDetails.
                    break;
               case "inventario":
                    // Enviar el atributo accion al jsp de details.
                    request.setAttribute("accion", accion);
                    doGetRequestInventario(request, response); // Ir al metodo doGetRequestDetails.
                    break;
                default:
                    // Enviar el atributo accion al jsp de index.
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
            }
        });
    }

    /**
     * Este método es un override al método de la clase HttpServlet para recibir
     * todas las peticiones post que se realice al Servlet Producto
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Producto
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Producto que utlizaremos para enviar el jsp al
     * navegador web
     * @throws ServletException devolver una exception de un servlet
     * @throws IOException devolver una exception al leer o escribir un archivo
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Utilizar el método authorize de la clase SessionUser para validar que solo usuario con permiso
        // puedan acceder al servlet de Producto. Todo el codigo que este dentro  expresion Lambda,  se ejecutara si el usuario tiene permitido
        // acceder a este Servlet 
        SessionUser.authorize(request, response, () -> {
            // Obtener el parámetro accion del request.
            String accion = Utilidad.getParameter(request, "accion", "index");
            // Hacer un switch para decidir a cual metodo ir segun el valor que venga en el parámetro de accion.
            switch (accion) {
                case "index":
                    // Enviar el atributo accion al jsp de index.
                    request.setAttribute("accion", accion);
                    doPostRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
                    break;
                case "create":
                    // Enviar el atributo accion al jsp de create.
                    request.setAttribute("accion", accion);
                    doPostRequestCreate(request, response); // Ir al metodo doPostRequestCreate.
                    break;
                case "edit":
                    // Enviar el atributo accion al jsp de edit.
                    request.setAttribute("accion", accion);
                    doPostRequestEdit(request, response); // Ir al metodo doPostRequestEdit.
                    break;
                case "delete":
                    // Enviar el atributo accion al jsp de delete.
                    request.setAttribute("accion", accion);
                    doPostRequestDelete(request, response); // Ir al metodo doPostRequestDelete.
                    break;
                case "Inventario":
                    // Enviar el atributo accion al jsp de delete.
                    request.setAttribute("accion", accion);
                    doPostRequestInventario(request, response); // Ir al metodo doPostRequestDelete.
                    break;
                default:
                    // Enviar el atributo accion al jsp de index.
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
            }
        });
    }

    // </editor-fold>
}
