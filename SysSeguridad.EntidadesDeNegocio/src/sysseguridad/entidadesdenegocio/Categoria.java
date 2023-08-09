/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sysseguridad.entidadesdenegocio;
import java.util.ArrayList;
/**
 *
 * @author MINEDUCYT
 */
public class Categoria {
      public int IdCategoria;
      public String Nombre;
      public String Descripcion;
      public String Imagen;
      public int top_aux;
       public ArrayList<Producto>Producto;
        private ArrayList<Proveedor>Proveedor; 

    public Categoria() {
    }

    public Categoria(int IdCategoria, String Nombre, String Descripcion, String Imagen, int top_aux, ArrayList<Producto> Producto, ArrayList<Proveedor> Proveedor) {
        this.IdCategoria = IdCategoria;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Imagen = Imagen;
        this.top_aux = top_aux;
        this.Producto = Producto;
        this.Proveedor = Proveedor;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getImagen() {
        return Imagen;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public ArrayList<Producto> getProducto() {
        return Producto;
    }

    public ArrayList<Proveedor> getProveedor() {
        return Proveedor;
    }

    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public void setProducto(ArrayList<Producto> Producto) {
        this.Producto = Producto;
    }

    public void setProveedor(ArrayList<Proveedor> Proveedor) {
        this.Proveedor = Proveedor;
    }


        
}