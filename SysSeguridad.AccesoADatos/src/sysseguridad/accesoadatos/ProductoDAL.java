package sysseguridad.accesoadatos;

import java.util.*; // Utilizar la utileria de java https://docs.oracle.com/javase/8/docs/api/java/util/package-summary.html
import java.sql.*;
import sysseguridad.entidadesdenegocio.*; // Agregar la referencia al proyecto de entidades de negocio y poder utilizar las entidades de Rol y Usuario

public class ProductoDAL { // Clase para poder realizar consulta de Insertar, modificar, eliminar, obtener datos de la tabla Rol

    // Metodo para obtener los campos a utilizar en la consulta SELECT de la tabla de Rol
    static String obtenerCampos() {
        return "pr.IdProducto, pr.IdCategoria, pr.IdProveedor, pr.Nombre, pr.Descripcion, pr.Precio, pr.Existencia";
    }

    // Metodo para obtener el SELECT a la tabla Rol y el TOP en el caso que se utilice una base de datos SQL SERVER
    private static String obtenerSelect(Producto pProducto) {
        String sql;
        sql = "SELECT ";
        if (pProducto.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            // Agregar el TOP a la consulta SELECT si el gestor de base de datos es SQL SERVER y "getTop_aux" es mayor a cero
            sql += "TOP " + pProducto.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Producto pr"); // Agregar los campos de la tabla de Rol mas el FROM a la tabla Rol con su alias "r"
        return sql;
    }

    // Metodo para obtener Order by a la consulta SELECT de la tabla Rol y ordene los registros de mayor a menor 
    private static String agregarOrderBy(Producto pProducto) {
        String sql = " ORDER BY pr.IdProducto DESC";
        if (pProducto.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            // Agregar el LIMIT a la consulta SELECT de la tabla de Rol en el caso que getTop_aux() sea mayor a cero y el gestor de base de datos
            // sea MYSQL
            sql += " LIMIT " + pProducto.getTop_aux() + " ";
        }
        return sql;
    }
    // Metodo para poder insertar un nuevo registro en la tabla de Rol
    public static int crear(Producto pProducto) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "INSERT INTO Producto(IdCategoria,IdProveedor,Nombre,Descripcion,Precio,Existencia) VALUES(?,?,?,?,?,?)"; // Definir la consulta INSERT a la tabla de Rol utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pProducto.getIdCategoria()); // Agregar el parametro a la consulta donde estan el simbolo "?" #1  
                ps.setInt(2, pProducto.getIdProveedor()); // Agregar el parametro a la consulta donde estan el simbolo "?" #1  
                ps.setString(3, pProducto.getNombre()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
                ps.setString(4, pProducto.getDescripcion()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
                ps.setInt(5, pProducto.getPrecio()); // Agregar el parametro a la consulta donde estan el simbolo "?" #1  
                ps.setInt(6, pProducto.getExistencia()); // Agregar el parametro a la consulta donde estan el simbolo "?" #1  
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

    // Metodo para poder actualizar un registro en la tabla de Rol
    public static int modificar(Producto pProducto) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            sql = "UPDATE Producto SET IdCategoria=?,IdProveedor=?,Nombre=?,Descripcion=?,Precio=?,Existencia=? WHERE IdProducto=?"; // Definir la consulta UPDATE a la tabla de Rol utilizando el simbolo ? para enviar parametros
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pProducto.getIdCategoria()); // Agregar el parametro a la consulta donde estan el simbolo ? #2  
                ps.setInt(2, pProducto.getIdProveedor()); // Agregar el parametro a la consulta donde estan el simbolo ? #2  
                ps.setString(3, pProducto.getNombre()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
                ps.setString(4, pProducto.getDescripcion()); // Agregar el parametro a la consulta donde estan el simbolo ? #1  
                ps.setInt(5, pProducto.getPrecio()); // Agregar el parametro a la consulta donde estan el simbolo ? #2  
                ps.setInt(6, pProducto.getExistencia()); // Agregar el parametro a la consulta donde estan el simbolo ? #2  
                 ps.setInt(7, pProducto.getIdProducto());
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
            sql = "DELETE FROM Producto WHERE IdProducto=?";  // Definir la consulta DELETE a la tabla de Rol utilizando el simbolo ? para enviar parametros
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
    
    // Metodo para llenar las propiedades de la entidad de Rol con los datos que viene en el ResultSet,
    // el metodo nos ayudara a no preocuparlos por los indices al momento de obtener los valores del ResultSet
    static int asignarDatosResultSet(Producto pProducto, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT r.Id(indice 1),r.Nombre(indice 2) * FROM Rol
        pIndex++;
        pProducto.setIdProducto(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pProducto.setIdCategoria(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pProducto.setIdProveedor(pResultSet.getInt(pIndex)); // index 3
        pIndex++;
        pProducto.setNombre(pResultSet.getString(pIndex)); // index 4
        pIndex++;
        pProducto.setDescripcion(pResultSet.getString(pIndex)); // index 5
        pIndex++;
        pProducto.setPrecio(pResultSet.getInt(pIndex)); // index 6
        pIndex++;
        pProducto.setExistencia(pResultSet.getInt(pIndex)); // index 7
        return pIndex;
    }
    
    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Rol 
      private static void obtenerDatos(PreparedStatement pPS, ArrayList<Producto> pProducto) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario
                Producto producto = new Producto();
                // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                asignarDatosResultSet(producto, resultSet, 0);
                pProducto.add(producto); // agregar la entidad Usuario al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex;// enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }
    
    private static void  obtenerDatosIncluirCategoriaYProveedor(PreparedStatement pPS, ArrayList<Producto> pProducto) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) { // obtener el ResultSet desde la clase ComunDB
            //crear un HashMap para automatizar la creacion de instancias de la clase Rol
            HashMap<Integer, Proveedor> proveedorMap = new HashMap();
            HashMap<Integer, Categoria> categoriaMap = new HashMap();
            while (resultSet.next()) { // Recorrer cada una de la fila que regresa la consulta  SELECT de la tabla Usuario JOIN a la tabla de Rol
                Producto producto = new Producto();
                 // Llenar las propiedaddes de la Entidad Usuario con los datos obtenidos de la fila en el ResultSet
                int index = asignarDatosResultSet(producto, resultSet, 0);
                  if (proveedorMap.containsKey(producto.getIdProveedor()) == false) { // verificar que el HashMap aun no contenga el Rol actual
                    Proveedor proveedor = new Proveedor();
                    // en el caso que el Rol no este en el HashMap se asignara
                    ProveedorDAL.asignarDatosResultSet(proveedor, resultSet, index);
                    proveedorMap.put(proveedor.getIdProveedor(), proveedor); // agregar el Rol al  HashMap
                    producto.setProveedor(proveedor); // agregar el Rol al Usuario
                } else {
                    // En el caso que el Rol existe en el HashMap se agregara automaticamente al Usuario
                    producto.setProveedor(proveedorMap.get(producto.getIdProveedor())); 
                }
                if (categoriaMap.containsKey(producto.getIdCategoria()) == false) { // verificar que el HashMap aun no contenga el Rol actual
                    Categoria categoria = new Categoria();
                    // en el caso que el Rol no este en el HashMap se asignara
                    CategoriaDAL.asignarDatosResultSet(categoria, resultSet, index);
                    categoriaMap.put(categoria.getIdCategoria(), categoria); // agregar el Rol al  HashMap
                    producto.setCategoria(categoria); // agregar el Rol al Usuario
                } else {
                    // En el caso que el Rol existe en el HashMap se agregara automaticamente al Usuario
                    producto.setCategoria(categoriaMap.get(producto.getIdCategoria())); 
                }
                               
                pProducto.add(producto); // Agregar el Usuario de la fila actual al ArrayList de Usuario
            }
            resultSet.close(); // cerrar el ResultSet
        } catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener ResultSet de la clase ComunDB   en el caso que suceda 
        }
    }

      
    // Metodo para obtener por Id un registro de la tabla de Rol 
     public static Producto obtenerPorId(Producto pProducto) throws Exception {
        Producto producto = new Producto();
        ArrayList<Producto> productos = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pProducto); // obtener la consulta SELECT de la tabla Usuario
             // Concatenar a la consulta SELECT de la tabla Usuario el WHERE  para comparar el campo Id
            sql += " WHERE pr.IdProducto=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pProducto.getIdProducto()); // agregar el parametro a la consulta donde estan el simbolo ? #1 
                obtenerDatos(ps, productos); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (productos.size() > 0) { // verificar si el ArrayList de Usuario trae mas de un registro en tal caso solo debe de traer uno
            producto = productos.get(0); // Si el ArrayList de Usuario trae un registro o mas obtenemos solo el primero
        }
        return producto; // devolver el Usuario encontrado por Id 
    }

    // Metodo para obtener todos los registro de la tabla de Rol
     public static ArrayList<Producto> obtenerTodos() throws Exception {
        ArrayList<Producto> productos;
        productos = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(new Producto()); // obtener la consulta SELECT de la tabla Usuario
            sql += agregarOrderBy(new Producto()); // concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id 
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // obtener el PreparedStatement desde la clase ComunDB
                obtenerDatos(ps, productos); // Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return productos; // devolver el ArrayList de Usuario
    }
    
    // Metodo para asignar los filtros de la consulta SELECT de la tabla de Rol de forma dinamica
    static void querySelect(Producto pProducto, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // obtener el PreparedStatement al cual aplicar los parametros
        if (pProducto.getIdProducto() > 0) { // verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Usuario
            pUtilQuery.AgregarWhereAnd(" pr.IdProducto=? "); // agregar el campo Id al filtro de la consulta SELECT y agregar el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Id a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pProducto.getIdProducto());
            }
        }
        // verificar si se va incluir el campo IdRol en el filtro de la consulta SELECT de la tabla de Usuario
        if (pProducto.getIdCategoria() > 0) {
            pUtilQuery.AgregarWhereAnd(" pr.IdCategoria=? "); // agregar el campo IdRol al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdRol a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pProducto.getIdCategoria());
            }
        }
        if (pProducto.getIdProveedor() > 0) {
            pUtilQuery.AgregarWhereAnd(" pr.IdProveedor=? "); // agregar el campo IdRol al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdRol a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pProducto.getIdProveedor());
            }
        }
        // verificar si se va incluir el campo Nombre en el filtro de la consulta SELECT de la tabla de Usuario
        if (pProducto.getNombre() != null && pProducto.getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" pr.Nombre LIKE ? "); // agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Usuario
                statement.setString(pUtilQuery.getNumWhere(), "%" + pProducto.getNombre() + "%");
            }
        }
        // Verificar si se va incluir el campo Apellido en el filtro de la consulta SELECT de la tabla de Usuario
        if (pProducto.getDescripcion() != null && pProducto.getDescripcion().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" pr.Descripcion LIKE ? "); // agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Usuario
                statement.setString(pUtilQuery.getNumWhere(), "%" + pProducto.getNombre() + "%");
            }
        }
        // Verificar si se va incluir el campo Login en el filtro de la consulta SELECT de la tabla de Usuario
       if (pProducto.getPrecio() > 0) {
            pUtilQuery.AgregarWhereAnd(" pr.Precio=? "); // agregar el campo IdRol al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdRol a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pProducto.getPrecio());
            }
        }
       if (pProducto.getExistencia() > 0) {
            pUtilQuery.AgregarWhereAnd(" pr.Existencia=? "); // agregar el campo IdRol al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                 // agregar el parametro del campo IdRol a la consulta SELECT de la tabla de Usuario
                statement.setInt(pUtilQuery.getNumWhere(), pProducto.getExistencia());
            }
        }
    }


     // Metodo para obtener todos los registro de la tabla de Rol que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Rol 
    public static ArrayList<Producto> buscarIncluirCategoriaYProveedor(Producto pProducto) throws Exception {
    ArrayList<Producto> productos = new ArrayList();
    try (Connection conn = ComunDB.obtenerConexion();) {
        String sql = "SELECT ";
        if (pProducto.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "TOP " + pProducto.getTop_aux() + " ";
        }
        sql += obtenerCampos();
        sql += ",";
        sql += CategoriaDAL.obtenerCampos(); // Obtener los campos de la tabla de Categoria que irán en el SELECT
        sql += ",";
        sql += ProveedorDAL.obtenerCampos(); // Obtener los campos de la tabla de Proveedor que irán en el SELECT
        sql += " FROM Producto pr";
        sql += " JOIN Proveedor p on (pr.IdProveedor=p.IdProveedor)";
        sql += " JOIN Categoria c on (pr.IdCategoria=c.IdCategoria)"; // Agregar el join para unir la tabla de Usuario con Categoria
         // Agregar el join para unir la tabla de Usuario con Proveedor
        ComunDB comundb = new ComunDB();
        ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
        querySelect(pProducto, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Usuario 
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pProducto); // Concatenar a la consulta SELECT de la tabla Usuario el ORDER BY por Id
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pProducto, utilQuery); // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Usuario
                obtenerDatosIncluirCategoriaYProveedor(ps, productos);// Llenar el ArrayList de Usuario con las fila que devolvera la consulta SELECT a la tabla de Usuario
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;// Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } catch (SQLException ex) {
            throw ex;// Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return productos; // Devolver el ArrayList de Usuario
    }
}


