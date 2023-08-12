/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sysseguridad.accesoadatos;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sysseguridad.entidadesdenegocio.Categoria;


public class CategoriaDAL {
    // Método para obtener los campos a utilizar en la consulta SELECT de la tabla de Categoría
    public static String obtenerCampos() {
        return "r.IdCategoria, r.Nombre,Descripcion,Imagen"; // Se sugiere agregar un espacio después de la coma para mayor legibilidad.
    }
       // Método para obtener el SELECT a la tabla de Categoría
    public static String obtenerSelect(Categoria pCategoria) {
        String sql;
        sql="SELECT ";
  if (pCategoria.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
    // Agregar el TOP a la consulta SELECT si el gestor de base de datos es SQL SERVER y "getTop_aux" es mayor a cero
    sql += "TOP " + pCategoria.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Categoria r"); // Agregar los campos de la tabla de Categoria mas el FROM a la tabla Categoria con su alias "r"
        return sql;
    }
     // Metodo para obtener Order by a la consulta SELECT de la tabla Categoria y ordene los registros de mayor a menor 
    public static String agregarOrderBy(Categoria pCategoria) {
        String sql = " ORDER BY r.Id DESC";
        if (pCategoria.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            // Agregar el LIMIT a la consulta SELECT de la tabla de Categoria en el caso que getTop_aux() sea mayor a cero y el gestor de base de datos
            // sea MYSQL
            sql += " LIMIT " + pCategoria.getTop_aux() + " ";
        }
        return sql;
    }
     // Metodo para poder insertar un nuevo registro en la tabla de Categoria
    public static int crear(Categoria pCategoria) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "INSERT INTO Categoria(Nombre) VALUES(?)"; // Definir la consulta INSERT a la tabla de Categoria utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setString(1, pCategoria.getNombre()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
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
 // ...
public static int modificar(Categoria categoria) throws SQLException {
    int result = 0;
    String sql = "UPDATE Categoria SET nombre=?, descripcion=?, imagen=?, top_aux=? WHERE Id=?";
    try (Connection conn = ComunDB.obtenerConexion();
         PreparedStatement ps = conn.prepareStatement(sql);) {
        ps.setString(1, categoria.getNombre());
        ps.setString(2, categoria.getDescripcion());
        ps.setString(3, categoria.getImagen());
        ps.setInt(4, categoria.getTop_aux());
        ps.setInt(5, categoria.getIdCategoria());
        result = ps.executeUpdate();
    } catch (SQLException ex) {
        throw ex;
    }
    return result;
}
// ...
// Método para poder eliminar un registro en la tabla de Categoría
public static int eliminar(Categoria pCategoria) throws Exception {
    int result;
    String sql;
    try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexión desde la clase ComunDB y encerrarla en try para cierre automático
        sql = "DELETE FROM Categoria WHERE Id=?"; // Definir la consulta DELETE a la tabla de Categoría utilizando el símbolo ? para enviar parámetros
        try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
            ps.setInt(1, pCategoria.getIdCategoria()); // Agregar el parámetro a la consulta donde está el símbolo ? #1
            result = ps.executeUpdate(); // Ejecutar la consulta DELETE en la base de datos
            ps.close(); // Cerrar el PreparedStatement
        } catch (SQLException ex) {
            throw ex; // Enviar al siguiente método el error al ejecutar PreparedStatement en caso que suceda
        }
        conn.close(); // Cerrar la conexión a la base de datos
    } catch (SQLException ex) {
        throw ex; // Enviar al siguiente método el error al obtener la conexión de la clase ComunDB en caso que suceda
    }
    return result; // Retornar el número de filas afectadas en el DELETE en la base de datos
}
// Método para llenar las propiedades de la entidad de Categoría con los datos que vienen en el ResultSet,
// este método nos ayudará a no preocuparnos por los índices al momento de obtener los valores del ResultSet.
public int asignarDatosResultSet(Categoria pCategoria, ResultSet pResultSet, int pIndex) throws Exception {
    pIndex++;
    pCategoria.setIdCategoria(pResultSet.getInt(pIndex)); // índice 1
    pIndex++;
    pCategoria.setNombre(pResultSet.getString(pIndex)); // índice 2
    pIndex++;
    pCategoria.setDescripcion(pResultSet.getString(pIndex)); // índice 3
    pIndex++;
    pCategoria.setImagen(pResultSet.getString(pIndex)); // índice 4
    return pIndex;
}
}
