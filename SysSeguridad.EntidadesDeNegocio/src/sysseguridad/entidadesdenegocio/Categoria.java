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
public class Categoria 
   {
      public int IdCategoria;
      public String Nombre;
      public String Descripcion;
    
      public int top_aux;
      private ArrayList<Producto> producto;

    public Categoria() {
    }

    public Categoria(int IdCategoria, String Nombre, String Descripcion, int top_aux, ArrayList<Producto> producto) {
        this.IdCategoria = IdCategoria;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.top_aux = top_aux;
        this.producto = producto;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public ArrayList<Producto> getProducto() {
        return producto;
    }

    public void setProducto(ArrayList<Producto> producto) {
        this.producto = producto;
    }
    
  
}