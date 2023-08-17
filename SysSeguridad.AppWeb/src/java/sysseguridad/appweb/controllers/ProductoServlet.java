
package sysseguridad.appweb.controllers;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;  // Importar la clase ArrayList
import sysseguridad.accesoadatos.ProductoDAL; // Importar la clase ProductoDAL de la capa de acceso a datos
import sysseguridad.accesoadatos.ProveedorDAL;
import sysseguridad.entidadesdenegocio.Producto;// Importar la clase Producto de la capa de entidades de negocio
import sysseguridad.appweb.utils.*;// Importar las clases SessionUser, Utilidad del paquete de utils
import sysseguridad.entidadesdenegocio.Proveedor;
import sysseguridad.entidadesdenegocio.Categoria;



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
        Producto Producto = new Producto();
 
         Producto.setIdProveedor(Integer.parseInt(Utilidad.getParameter(request, "IdProveedor", "0")));
         Producto.setIdCategoria(Integer.parseInt(Utilidad.getParameter(request, "IdCategoria", "0")));
        // Obtener el parámetro nombre del request   y asignar ese valor a la propiedad Nombre de Producto.
        Producto.setNombre(Utilidad.getParameter(request, "Nombre", ""));
        Producto.setDescripcion(Utilidad.getParameter(request, "Descrpcion", ""));
        //Producto.setPrecio(Utilidad.perseFloat(request, "Precio", ""));
        Producto.setPrecio(Integer.parseInt(Utilidad.getParameter(request, "Precio", "")));
        
        Producto.setExistencia(Integer.parseInt(Utilidad.getParameter(request, "Existencia", "10")));
        if (accion.equals("index")) {  // Si accion es index.
            // Obtener el parámetro top_aux del request  y asignar ese valor a la propiedad Top_aux de Producto.
            Producto.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            // Utilizando un operador ternario, colocar en el Top_aux, si  es igual a cero enviar en el Top_aux, el valor maximo de un entero 
            // en java, para obtener todos los registro, en el caso contrario obtener la cantidad de registros
            // que se obtiene en el parámetro top_aux del request.
            Producto.setTop_aux(Producto.getTop_aux() == 0 ? Integer.MAX_VALUE : Producto.getTop_aux());
        }
        // Devolver la instancia de la entidad Producto con los valores obtenidos del request.
        return Producto;
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
            Producto Producto = new Producto(); // Crear una instancia  de la entidad de Producto.
            Producto.setTop_aux(10); // Agregar el Top_aux con el valor de 10 a la propiedad Top_aux de Producto.
            ArrayList<Producto> producto = ProductoDAL.buscarIncluirProveedor(Producto); // Ir a la capa de acceso a datos y buscar los registros de .
            // El request.setAttribute se utiliza para enviar datos desde un servlet a un jsp.

            request.setAttribute("Producto", producto); // Enviar los Producto al jsp utilizando el request.setAttribute con el nombre del atributo roles.
            // Enviar el Top_aux de Producto al jsp utilizando el request.setAttribute con el nombre del atributo top_aux.
            request.setAttribute("top_aux", Producto.getTop_aux());
            // El request.getRequestDispatcher nos permite direccionar a un jsp desde un servlet.              
            request.getRequestDispatcher("Views/Producto/index.jsp").forward(request, response); // Direccionar al jsp index de Rol.
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
             Producto producto = obtenerProducto(request); // Llenar la instancia de Producto con los parámetros enviados en el request 
            ArrayList<Producto> Producto = ProductoDAL.buscar(producto); // Buscar los Producto que cumple con los datos enviados en el request
            request.setAttribute("Producto", Producto); // Enviar los Producto al jsp utilizando el request.setAttribute con el nombre del atributo roles
            // Enviar el Top_aux de Producto al jsp utilizando el request.setAttribute con el nombre del atributo top_aux
            request.setAttribute("top_aux", producto.getTop_aux());
            request.getRequestDispatcher("Views/Producto/index.jsp").forward(request, response); // Direccionar al jsp index de Rol
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception 
            Utilidad.enviarError(ex.getMessage(), request, response);
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
            Producto producto = obtenerProducto(request); // Llenar la instancia de Producto con los parámetros enviados en el request.
            // Enviar los datos de Producto a la capa de accesoa a datos para que lo almacene en la base de datos el registro.
            int result = ProductoDAL.crear(producto);
            if (result != 0) { // Si el result es diferente a cero significa que los datos fueron ingresados correctamente.
                // Enviar el atributo accion con el valor index al jsp de index
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); // ir al metodo doGetRequestIndex para que nos direcciones al jsp index
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
    private void requestObtenerPorIdProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Producto producto = obtenerProducto(request); // Llenar la instancia de Usuario con los parámetros enviados en el request.
            Producto producto_result =ProductoDAL.obtenerPorId(producto); // Obtener desde la capa de acceso a datos el usuario por IdProducto.
            if (producto_result.getIdProducto() > 0) { // Si el Id es mayor a cero.
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(producto_result.getIdProveedor());
                // Obtener desde la capa de acceso a datos el Producto por Id y asignarlo al usuario.
                producto_result.setProveedor(ProveedorDAL.obtenerPorId(proveedor));
                // Enviar el atributo usuario con el valor de los datos del usuario de nuestra base de datos a un jsp
                request.setAttribute("producto", producto_result);
            } else {
                // Enviar al jsp de error el siguiente mensaje. El Id: ? no existe en la tabla de Producto
                Utilidad.enviarError("El IdProducto:" + producto.getIdProducto() + " no existe en la tabla de Producto", request, response);
            }
        } catch (Exception ex) {
            // enviar al jsp de error si hay un Exception
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
        requestObtenerPorIdProducto(request, response);
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
            Producto producto = obtenerProducto(request); // Llenar la instancia de Producto con los parámetros enviados en el request.
            // Enviar los datos de Producto a la capa de accesoa a datos para modificar el registro.
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
        // Enviar el Producto al jsp de details que se obtiene por IdProducto.
        requestObtenerPorIdProducto(request, response);
        // Direccionar al jsp details de Producto.
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
        // Enviar el Producto al jsp de delete que se obtiene por Id.
        requestObtenerPorIdProducto(request, response);
        // Direccionar al jsp delete de Producto.
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
            Producto producto = obtenerProducto(request); // Llenar la instancia de Producto con los parámetros enviados en el request.
            // Enviar los datos de Producto a la capa de accesoa a datos para que elimine el registro.
            int result = ProductoDAL.eliminar(producto);
            if (result != 0) {// Si el result es diferente a cero significa que los datos fueron eliminados correctamente.
                // Enviar el atributo accion con el valor index al jsp de index.
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); // Ir al método doGetRequestIndex para que nos direccione al jsp index.
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro eliminar el registro.
                Utilidad.enviarError("No se logro eliminar el registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception.
            Utilidad.enviarError(ex.getMessage(), request, response);
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
                default:
                    // Enviar el atributo accion al jsp de index.
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
            }
        });
    }

    // </editor-fold>
}
