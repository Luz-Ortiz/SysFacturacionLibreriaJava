
package sysseguridad.accesoadatos;

import java.util.*; // Utilizar la utileria de java https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html
import java.sql.*;
import sysseguridad.entidadesdenegocio.*; // Agregar la referencia al proyecto de entidades de negocio y poder utilizar las entidades de Rol y Usuario
import java.math.BigDecimal;
import static sysseguridad.accesoadatos.UsuarioDAL.asignarDatosResultSet;

public class ProductoDAL {  // Clase para poder realizar consulta de Insertar, modificar, eliminar, obtener datos de la tabla Producto
    
    // Metodo para obtener los campos a utilizar en la consulta SELECT de la tabla de Producto
    static String obtenerCampos() {
    return "p.IdProducto,p.IdCategoria,p.IdProveedor,p.Nombre,p.Descripcion,p.Precio,p.Existencia";
 }
        // Metodo para obtener el SELECT a la tabla Rol y el TOP en el caso que se utilice una base de datos SQL SERVER
    private static String obtenerSelect(Producto pProducto) {
        String sql;
        sql = "SELECT ";
        if (pProducto.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            // Agregar el TOP a la consulta SELECT si el gestor de base de datos es SQL SERVER y "getTop_aux" es mayor a cero
            sql += "TOP " + pProducto.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Producto p"); // Agregar los campos de la tabla de Producto mas el FROM a la tabla Producto con su alias "p"
        return sql;
    }
    
    // Metodo para obtener Order by a la consulta SELECT de la tabla Producto y ordene los registros de mayor a menor 
     private static String agregarOrderBy(Producto pProducto) {
    String sql = " ORDER BY p.IdProducto DESC";
        if (pProducto.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            // Agregar el LIMIT a la consulta SELECT de la tabla de Producto en el caso que getTop_aux() sea mayor a cero y el gestor de base de datos
            // sea MYSQL
            sql += " LIMIT " + pProducto.getTop_aux() + " ";
        }
        return sql;
    }
      // Metodo para poder insertar un nuevo registro en la tabla de 
    public static int crear(Producto pProducto) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "INSERT INTO Producto(IdProveedor,IdCategoria,Nombre,Descripcion,Precio,Existencia,) VALUES(?,?,?,?,?,?)"; // Definir la consulta INSERT a la tabla de Producto utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pProducto.getIdProveedor());
                ps.setInt(2, pProducto.getIdCategoria());
                ps.setString(3, pProducto.getNombre()); 
                ps.setString(4, pProducto.getDescripcion());
                ps.setBigDecimal(5, pProducto.getPrecio());
                ps.setInt(6, pProducto.getExistencia());
                result = ps.executeUpdate(); // Ejecutar la consulta INSERT en la base de datos
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda 
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion en el caso que suceda 
        }
        return result; // Retornar el numero de fila afectadas en el INSERT en la base de datos 
    }
    
    // Metodo para poder actualizar un registro en la tabla de Producto
    public static int modificar(Producto pProducto) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "UPDATE Producto SET Nombre=? Descripcion=? precio=? Existencia=? IdProveedor=? IdCategoria=? WHERE IdProducto=?"; // Definir la consulta UPDATE a la tabla de Producto utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pProducto.getIdProveedor());// Agregar el parametro a la consulta donde estan el simbolo ? #1 
                ps.setInt(2, pProducto.getIdCategoria());
                ps.setString(3, pProducto.getNombre()); 
                ps.setString(4, pProducto.getDescripcion());
                ps.setBigDecimal(5, pProducto.getPrecio());
                ps.setInt(6, pProducto.getExistencia());
                result = ps.executeUpdate(); // Ejecutar la consulta UPDATE en la base de datos
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda 
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion en el caso que suceda 
        }
        return result; // Retornar el numero de fila afectadas en el UPDATE en la base de datos 
    }
    
    
        // Metodo para poder eliminar un registro en la tabla de Rol
    public static int eliminar(Producto pProducto) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "DELETE FROM Producto WHERE IdProducto=?";  // Definir la consulta DELETE a la tabla de Producto utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pProducto.getIdProducto()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                result = ps.executeUpdate();  // Ejecutar la consulta DELETE en la base de datos
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close();  // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda 
        }
        return result; // Retornar el numero de fila afectadas en el DELETE en la base de datos 
    }  
        // Metodo para llenar las propiedades de la entidad de Producto con los datos que viene en el ResultSet,
    // el metodo nos ayudara a no preocuparlos por los indices al momento de obtener los valores del ResultSet
    static int asignarDatosResultSet(Producto pProducto, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT r.Id(indice 1),r.Nombre(indice 2) * FROM Producto
        pIndex++;
        pProducto.setIdProducto(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pProducto.setIdProveedor(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pProducto.setIdCategoria(pResultSet.getInt(pIndex)); // index 3
        pIndex++;
        pProducto.setNombre(pResultSet.getString(pIndex)); // index 4
        pIndex++;
        pProducto.setDescripcion(pResultSet.getString(pIndex)); // index 5
        pIndex++;
        pProducto.setPrecio(pResultSet.getBigDecimal(pIndex)); // index 6
        pIndex++;
        pProducto.setExistencia(pResultSet.getInt(pIndex)); // index 7
        return pIndex;
    }
        // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Producto
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Producto> pProducto) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Producto
                Producto producto = new Producto(); 
                asignarDatosResultSet(producto, resultSet, 0); // Llenar las propiedaddes de la Entidad Producto con los datos obtenidos de la fila en el ResultSet
                pProducto.add(producto); // Agregar la entidad Productoal ArrayList de Producto
            }
            resultSet.close(); // Cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }

    
    //Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Producto y JOIN a la tabla de Categoria y Proveedor
    private static void obtenerDatosIncluirProveedor(PreparedStatement pPS, ArrayList<Producto> pProducto) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            HashMap<Integer, Proveedor> ProveedorMap = new HashMap(); //crear un HashMap para automatizar la creacion de instancias de la clase Proveedor
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Producto JOIN a la tabla de Rol
                Producto producto = new Producto();
                 // Llenar las propiedaddes de la Entidad Producto con los datos obtenidos de la fila en el ResultSet
                int index = asignarDatosResultSet(producto, resultSet, 0);
                if (ProveedorMap.containsKey(producto.getIdProducto()) == false) { // verificar que el HashMap aun no contenga el Rol actual
                    Proveedor proveedor = new Proveedor();
                    // en el caso que el Proveedor no este en el HashMap se asignara
                    ProveedorDAL.asignarDatosResultSet(proveedor, resultSet, index);
                    ProveedorMap.put(proveedor.getIdProveedor(), proveedor); // agregar el Rol al  HashMap
                    producto.setProveedor(proveedor); // agregar el Proveedor al Usuario
                } else {
                    // En el caso que el Proveedor existe en el HashMap se agregara automaticamente al Usuario
                    producto.setProveedor(ProveedorMap.get(producto.getIdProveedor())); 
                }
                pProducto.add(producto); // Agregar el Usuario de la fila actual al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    
    
    
    
     // Metodo para obtener por IdPoducto un registro de la tabla de Producto
    public static Producto obtenerPorIdProducto(Producto pProducto) throws Exception {
        Producto Producto = new Producto();
        ArrayList<Producto> producto = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pProducto); // Obtener la consulta SELECT de la tabla Rol
            sql += " WHERE p.IdProducto=?"; // Concatenar a la consulta SELECT de la tabla Rol el WHERE 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pProducto.getIdProducto()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                obtenerDatos(ps, producto); // Llenar el ArrayList de Producto con las fila que devolvera la consulta SELECT a la tabla de Rol
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close();  // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (producto.size() > 0) { // Verificar si el ArrayList de Producto trae mas de un registro en tal caso solo debe de traer uno
            Producto = producto.get(0); // Si el ArrayList de Producto trae un registro o mas obtenemos solo el primero 
        }
        return Producto; // Devolver el Producto encontrado por IdProducto 
    }
// Metodo para obtener todos los registro de la tabla de Producto
    public static ArrayList<Producto> obtenerTodos() throws Exception {
        ArrayList<Producto> producto;
        producto = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {// Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(new Producto());  // Obtener la consulta SELECT de la tabla Producto
            sql += agregarOrderBy(new Producto());  // Concatenar a la consulta SELECT de la tabla Producto el ORDER BY por IdProducto 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                obtenerDatos(ps, producto); // Llenar el ArrayList de Rol con las fila que devolvera la consulta SELECT a la tabla de Rol
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return producto; // Devolver el ArrayList de Producto
    }
    // Metodo para asignar los filtros de la consulta SELECT de la tabla de Producto de forma dinamica
    static void querySelect(Producto pProducto, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // Obtener el PreparedStatement al cual aplicar los parametros
        if (pProducto.getIdProducto() > 0) { // Verificar si se va incluir el campo IdProducto en el filtro de la consulta SELECT de la tabla de Producto
            pUtilQuery.AgregarWhereAnd(" p.IdProducto=? "); // Agregar el campo IdProducto al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) { 
                // Agregar el parametro del campo IdProducto a la consulta SELECT de la tabla de Rol
                statement.setInt(pUtilQuery.getNumWhere(), pProducto.getIdProducto()); 
            }
        }
             // verificar si se va incluir el campo IdProveedor en el filtro de la consulta SELECT de la tabla de Proveedor
        if (pProducto.getIdProveedor() > 0) {
            pUtilQuery.AgregarWhereAnd(" p.IdProveedor=? "); // agregar el campo IdProveedor al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdProveedor a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pProducto.getIdProveedor());
            }
            
            // verificar si se va incluir el campo IdProveedor en el filtro de la consulta SELECT de la tabla de Proveedor
        if (pProducto.getIdCategoria() > 0) {
            pUtilQuery.AgregarWhereAnd(" p.IdCategoria=? "); // agregar el campo IdCategoria al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdProveedor a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pProducto.getIdCategoria());
            }
            
        // Verificar si se va incluir el campo Nombre en el filtro de la consulta SELECT de la tabla de Proveedor
        if (pProducto.getNombre() != null && pProducto.getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.Nombre LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Producto
                statement.setString(pUtilQuery.getNumWhere(), "%" + pProducto.getNombre() + "%"); 
            }
            
            // Verificar si se va incluir el campo Nombre en el filtro de la consulta SELECT de la tabla de Proveedor
        if (pProducto.getDescripcion() != null && pProducto.getDescripcion().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.Descripcion LIKE ? "); // Agregar el campo Descripcion al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Producto
                statement.setString(pUtilQuery.getNumWhere(), "%" + pProducto.getDescripcion() + "%"); 
            }
            
            if (pProducto.getPrecio() != null) {
            pUtilQuery.AgregarWhereAnd(" p.Precio = ? "); // Agregar el campo Precio al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
            // Agregar el parametro del campo Precio a la consulta SELECT de la tabla de Producto
            statement.setBigDecimal(pUtilQuery.getNumWhere(), pProducto.getPrecio());
           }
            
            if (pProducto.getExistencia() > 0) {
            pUtilQuery.AgregarWhereAnd(" p.Existencia=? "); // agregar el campo IdProveedor al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdProveedor a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pProducto.getExistencia());
            }
        }
    }

    // Metodo para obtener todos los registro de la tabla de Producto que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Producto
    public static ArrayList<Producto> buscar(Producto pProducto) throws Exception {
        ArrayList<Producto> Producto = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pProducto); // Obtener la consulta SELECT de la tabla Producto
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0); 
            querySelect(pProducto, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Producto 
            sql = utilQuery.getSQL(); 
            sql += agregarOrderBy(pProducto); // Concatenar a la consulta SELECT de la tabla Producto el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pProducto, utilQuery);  // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Producto
                obtenerDatos(ps, Producto); // Llenar el ArrayList de Producto con las fila que devolvera la consulta SELECT a la tabla de Producto
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return Producto; // Devolver el ArrayList de Producto
    }
}

