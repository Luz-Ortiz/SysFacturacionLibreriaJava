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
import static sysseguridad.accesoadatos.RolDAL.querySelect;
import sysseguridad.entidadesdenegocio.*;

public class ProveedorDAL {
    static String obtenerCampos() {
        return "p.IdProveedor,d.CodProveedor,p.Nombre,p.Empresa,p.Telefono,p.Descripcion,p.Direccion";
    }
    // Metodo para obtener el SELECT a la tabla Proveedor y el TOP en el caso que se utilice una base de datos SQL SERVER
    private static String obtenerSelect(Proveedor pProveedor) {
        String sql;
        sql = "SELECT ";
        if (pProveedor.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            // Agregar el TOP a la consulta SELECT si el gestor de base de datos es SQL SERVER y "getTop_aux" es mayor a cero
            sql += "TOP " + pProveedor.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Proveedor p"); // Agregar los campos de la tabla de Proveedor mas el FROM a la tabla Proveedor con su alias "p"
        return sql;
    }
     // Metodo para obtener Order by a la consulta SELECT de la tabla Proveedor y ordene los registros de mayor a menor 
    private static String agregarOrderBy(Proveedor pProveedor) {
        String sql = " ORDER BY r.Id DESC";
        if (pProveedor.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            // Agregar el LIMIT a la consulta SELECT de la tabla de Proveedor en el caso que getTop_aux() sea mayor a cero y el gestor de base de datos
            // sea MYSQL
            sql += " LIMIT " + pProveedor.getTop_aux() + " ";
        }
        return sql;
    }
// Metodo para poder insertar un nuevo registro en la tabla de Proveedor
    public static int crear(Proveedor pProveedor) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "INSERT INTO Proveedor(Nombre) VALUES(?)"; // Definir la consulta INSERT a la tabla de Proveedor utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setString(1, pProveedor.getNombre()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
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
    // Metodo para poder actualizar un registro en la tabla de Proveedor
    public static int modificar(Proveedor pProveedor) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "UPDATE Rol SET Nombre=? WHERE Id=?"; // Definir la consulta UPDATE a la tabla de Proveedor utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setString(1, pProveedor.getNombre()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
                ps.setInt(2, pProveedor.getIdProveedor()); // Agregar el parametro a la consulta donde estan el simbolo ? #2  
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
    
    // Metodo para poder eliminar un registro en la tabla de Proveedor
    public static int eliminar(Proveedor pProveedor) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "DELETE FROM Rol WHERE Id=?";  // Definir la consulta DELETE a la tabla de Proveedor utilizando el simbolo ? para enviar parametros
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
    
    // Metodo para llenar las propiedades de la entidad de Proveedor con los datos que viene en el ResultSet,
    // el metodo nos ayudara a no preocuparlos por los indices al momento de obtener los valores del ResultSet
    static int asignarDatosResultSet(Proveedor pProveedor, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT r.Id(indice 1),r.Nombre(indice 2) * FROM Rol
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
        return pIndex;
        
    }
    
    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Rol 
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Proveedor> pProveedores) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Rol
                Proveedor proveedor = new Proveedor(); 
                asignarDatosResultSet(proveedor, resultSet, 0); // Llenar las propiedaddes de la Entidad Rol con los datos obtenidos de la fila en el ResultSet
                pProveedores.add(proveedor); // Agregar la entidad Rol al ArrayList de Rol
            }
            resultSet.close(); // Cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    
    // Metodo para obtener por Id un registro de la tabla de Proveedor 
    public static Proveedor obtenerPorId(Proveedor pProveedor) throws Exception {
        Proveedor proveedor = new Proveedor();
        ArrayList<Proveedor> proveedores = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pProveedor); // Obtener la consulta SELECT de la tabla Proveedor
            sql += " WHERE p.Idproveedor=?"; // Concatenar a la consulta SELECT de la tabla Proveedor el WHERE 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pProveedor.getIdProveedor()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                obtenerDatos(ps, proveedores); // Llenar el ArrayList de Proveedor con las fila que devolvera la consulta SELECT a la tabla de Proveedor
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close();  // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (proveedores.size() > 0) { // Verificar si el ArrayList de Proveedor trae mas de un registro en tal caso solo debe de traer uno
            proveedor = proveedores.get(0); // Si el ArrayList de Proveedor trae un registro o mas obtenemos solo el primero 
        }
        return proveedor; // Devolver el proveedor encontrado por IdProveedor
    }
    
      // Metodo para obtener todos los registro de la tabla de Proveedor 
    public static ArrayList<Proveedor > obtenerTodos() throws Exception {
        ArrayList<Proveedor > proveedores ;
        proveedores = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {// Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(new Proveedor ());  // Obtener la consulta SELECT de la tabla Proveedor 
            sql += agregarOrderBy(new Proveedor ());  // Concatenar a la consulta SELECT de la tabla Proveedor  el ORDER BY por Id 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                obtenerDatos(ps, proveedores); // Llenar el ArrayList de Proveedor  con las fila que devolvera la consulta SELECT a la tabla de Proveedor 
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return proveedores; // Devolver el ArrayList de Proveedor 
    }

      static void querySelect(Proveedor  pProveedor , ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // Obtener el PreparedStatement al cual aplicar los parametros
        if (pProveedor .getIdProveedor () > 0) { // Verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Rol
            pUtilQuery.AgregarWhereAnd(" p.IdProveedor =? "); // Agregar el campo Id al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) { 
                // Agregar el parametro del campo Id a la consulta SELECT de la tabla de Proveedor 
                statement.setInt(pUtilQuery.getNumWhere(), pProveedor .getIdProveedor ()); 
            }
        }
        // Verificar si se va incluir el campo Nombre en el filtro de la consulta SELECT de la tabla de Proveedor 
        if (pProveedor .getNombre() != null && pProveedor .getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Nombre LIKE ? "); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Proveedor 
                statement.setString(pUtilQuery.getNumWhere(), "%" + pProveedor .getNombre() + "%"); 
            }
        }
    }

   // Metodo para obtener todos los registro de la tabla de Rol que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Rol 
    public static ArrayList<Proveedor> buscar(Proveedor  pProveedor ) throws Exception {
        ArrayList<Proveedor> proveedores  = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pProveedor ); // Obtener la consulta SELECT de la tabla Rol
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0); 
            querySelect(pProveedor , utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Rol 
            sql = utilQuery.getSQL(); 
            sql += agregarOrderBy(pProveedor ); // Concatenar a la consulta SELECT de la tabla Rol el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pProveedor , utilQuery);  // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Rol
                obtenerDatos(ps, proveedores ); // Llenar el ArrayList de Rol con las fila que devolvera la consulta SELECT a la tabla de Rol
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return proveedores; // Devolver el ArrayList de Rol
    } 
    
}
