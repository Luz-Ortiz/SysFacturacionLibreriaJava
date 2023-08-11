/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sysseguridad.accesoadatos;

/**
 *
 * @author MINEDUCYT
 */
import java.util.*; 
import java.sql.*;
import sysseguridad.entidadesdenegocio.*;

public class ProveedorDAL {
   // Clase para poder realizar consulta de Insertar, modificar, eliminar, obtener datos de la tabla Proveedor
    
    // Metodo para obtener los campos a utilizar en la consulta SELECT de la tabla de Proveedor
    static String obtenerCampos() {
    return "p.IdProveedor,p.CodProveedor,p.Nombre,p.Empresa,p.Telefono,p.Descripcion,p.Direccion";
 }
        // Metodo para obtener el SELECT a la tabla Proveedor y el TOP en el caso que se utilice una base de datos SQL SERVER
    private static String obtenerSelect(Proveedor pProveedor) {
        String sql;
        sql = "SELECT ";
        if (pProveedor.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            // Agregar el TOP a la consulta SELECT si el gestor de base de datos es SQL SERVER y "getTop_aux" es mayor a cero
            sql += "TOP " + pProveedor.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Proveedor p"); // Agregar los campos de la tabla de Proveedor mas el FROM a la tabla Producto con su alias "p"
        return sql;
    }
    
    // Metodo para obtener Order by a la consulta SELECT de la tabla Proveedor y ordene los registros de mayor a menor 
     private static String agregarOrderBy(Proveedor pProveedor) {
        String sql = " ORDER BY p.IdProveedor DESC";
        if (pProveedor.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            // Agregar el LIMIT a la consulta SELECT de la tabla de Proveedor en el caso que getTop_aux() sea mayor a cero y el gestor de base de datos
            // sea MYSQL
            sql += " LIMIT " + pProveedor.getTop_aux() + " ";
        }
        return sql;
    }
      // Metodo para poder insertar un nuevo registro en la tabla de 
    public static int crear(Proveedor pProveedor) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "INSERT INTO Proveedor (CodProveedor,Nombre,Empresa,Telefono,Descripcion,Direccion) VALUES(?,?,?,?,?,?)"; // Definir la consulta INSERT a la tabla de Producto utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pProveedor.getCodProveedor());
                ps.setString(2, pProveedor.getNombre()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
                ps.setString(3, pProveedor.getEmpresa());
                ps.setString(4, pProveedor.getTelefono());
                ps.setString(5, pProveedor.getDescripcion());
                ps.setString(6, pProveedor.getDireccion());
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
    public static int modificar(Proveedor pProveedor) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "UPDATE Proveedor SET CodProveedor=? Nombre=? Empresa =?  Telefono=? Descripcion=? Direccion=? WHERE IdProveedor=?"; // Definir la consulta UPDATE a la tabla de Producto utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pProveedor.getCodProveedor());
                ps.setString(2, pProveedor.getNombre());// Agregar el parametro a la consulta donde estan el simbolo ? #1  
                ps.setString(3, pProveedor.getEmpresa()); // Agregar el parametro a la consulta donde estan el simbolo ? #2 
                ps.setString(4, pProveedor.getTelefono());
                ps.setString(5, pProveedor.getDescripcion());
                ps.setString(6, pProveedor.getDireccion());
                ps.setInt(7, pProveedor.getIdProveedor());
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
    public static int eliminar(Proveedor pProveedor) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "DELETE FROM Proveedor WHERE IdProveedor=?";  // Definir la consulta DELETE a la tabla de Producto utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pProveedor.getIdProveedor()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
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
    static int asignarDatosResultSet(Proveedor pProveedor, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT r.Id(indice 1),r.Nombre(indice 2) * FROM Producto
         pIndex++;
        pProveedor.setIdProveedor(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        
        pProveedor.setCodProveedor(pResultSet.getInt(pIndex));
        pIndex++;
        
        pProveedor.setNombre(pResultSet.getString(pIndex)); // index 2
        pIndex++;
        
        pProveedor.setEmpresa(pResultSet.getString(pIndex));
        pIndex++;
        
        pProveedor.setTelefono(pResultSet.getString(pIndex));
        pIndex++;
        
        pProveedor.setDescripcion(pResultSet.getString(pIndex));
        pIndex++;
        
        pProveedor.setDireccion(pResultSet.getString(pIndex));
        pIndex++;
        
        return pIndex;
    }
        // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Producto
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Proveedor> pProveedor) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Producto
                Proveedor proveedor = new Proveedor(); 
                asignarDatosResultSet(proveedor, resultSet, 0); // Llenar las propiedaddes de la Entidad Producto con los datos obtenidos de la fila en el ResultSet
                pProveedor.add(proveedor); // Agregar la entidad Productoal ArrayList de Producto
            }
            resultSet.close(); // Cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }

     // Metodo para obtener por Id un registro de la tabla de Rol 
    public static Proveedor obtenerPorId(Proveedor pProveedor) throws Exception {
        Proveedor Proveedor = new Proveedor();
        ArrayList<Proveedor> proveedor = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pProveedor); // Obtener la consulta SELECT de la tabla Rol
            sql += " WHERE p.IdProveedor=?"; // Concatenar a la consulta SELECT de la tabla Rol el WHERE 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pProveedor.getIdProveedor()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                obtenerDatos(ps, proveedor); // Llenar el ArrayList de Rol con las fila que devolvera la consulta SELECT a la tabla de Rol
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close();  // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (proveedor.size() > 0) { // Verificar si el ArrayList de Producto trae mas de un registro en tal caso solo debe de traer uno
            Proveedor = proveedor.get(0); // Si el ArrayList de Producto trae un registro o mas obtenemos solo el primero 
        }
        return Proveedor; // Devolver el rol encontrado por IdProducto 
    }
// Metodo para obtener todos los registro de la tabla de Rol
    public static ArrayList<Proveedor> obtenerTodos() throws Exception {
        ArrayList<Proveedor> proveedor;
        proveedor = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {// Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(new Proveedor());  // Obtener la consulta SELECT de la tabla Rol
            sql += agregarOrderBy(new Proveedor());  // Concatenar a la consulta SELECT de la tabla Rol el ORDER BY por Id 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                obtenerDatos(ps, proveedor); // Llenar el ArrayList de Rol con las fila que devolvera la consulta SELECT a la tabla de Rol
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return proveedor; // Devolver el ArrayList de Producto
    }
    // Metodo para asignar los filtros de la consulta SELECT de la tabla de Producto de forma dinamica
    static void querySelect(Proveedor pProveedor, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // Obtener el PreparedStatement al cual aplicar los parametros
        if (pProveedor.getIdProveedor() > 0) { // Verificar si se va incluir el campo IdProducto en el filtro de la consulta SELECT de la tabla de Producto
            pUtilQuery.AgregarWhereAnd(" p.IdProveedor=? "); // Agregar el campo IdProducto al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) { 
                // Agregar el parametro del campo IdProducto a la consulta SELECT de la tabla de Rol
                statement.setInt(pUtilQuery.getNumWhere(), pProveedor.getIdProveedor()); 
            }
        }
       if (pProveedor.getCodProveedor() > 0) { // Verificar si se va incluir el campo IdProducto en el filtro de la consulta SELECT de la tabla de Producto
            pUtilQuery.AgregarWhereAnd(" p.CodProveedor=? "); // Agregar el campo IdProducto al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) { 
                // Agregar el parametro del campo IdProducto a la consulta SELECT de la tabla de Rol
                statement.setInt(pUtilQuery.getNumWhere(), pProveedor.getCodProveedor()); 
            }
        }
        // Verificar si se va incluir el campo Nombre en el filtro de la consulta SELECT de la tabla de Rol
        if (pProveedor.getNombre() != null && pProveedor.getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.Nombre LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Producto
                statement.setString(pUtilQuery.getNumWhere(), "%" + pProveedor.getNombre() + "%"); 
            }
        }
        if (pProveedor.getEmpresa() != null && pProveedor.getEmpresa().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.Empresa LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Producto
                statement.setString(pUtilQuery.getNumWhere(), "%" + pProveedor.getEmpresa() + "%"); 
            }
        }
        if (pProveedor.getTelefono() != null && pProveedor.getTelefono().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.Telefono LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Producto
                statement.setString(pUtilQuery.getNumWhere(), "%" + pProveedor.getTelefono() + "%"); 
            }
        }
        
         if (pProveedor.getDireccion() != null && pProveedor.getDireccion().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.Direccion LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Producto
                statement.setString(pUtilQuery.getNumWhere(), "%" + pProveedor.getDireccion() + "%"); 
            }
        }
        
         if (pProveedor.getDescripcion() != null && pProveedor.getDescripcion().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.Direccion LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Producto
                statement.setString(pUtilQuery.getNumWhere(), "%" + pProveedor.getDescripcion() + "%"); 
            }
        }
    }

    // Metodo para obtener todos los registro de la tabla de Producto que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Producto
    public static ArrayList<Proveedor> buscar(Proveedor pProveedor) throws Exception {
        ArrayList<Proveedor> Proveedor = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pProveedor); // Obtener la consulta SELECT de la tabla Producto
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0); 
            querySelect(pProveedor, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Producto 
            sql = utilQuery.getSQL(); 
            sql += agregarOrderBy(pProveedor); // Concatenar a la consulta SELECT de la tabla Producto el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pProveedor, utilQuery);  // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Producto
                obtenerDatos(ps, Proveedor); // Llenar el ArrayList de Producto con las fila que devolvera la consulta SELECT a la tabla de Producto
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return Proveedor; // Devolver el ArrayList de Producto
    }
}
