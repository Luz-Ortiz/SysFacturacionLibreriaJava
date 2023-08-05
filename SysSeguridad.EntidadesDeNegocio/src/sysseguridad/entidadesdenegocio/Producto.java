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
public class Producto {
    
    private int IdProducto;
    private String Nombre;
    private String Descripcion;
    private float precio;
    private float Existencia;
    private int top_aux;
    private ArrayList<Categoria> Categoria;
    private ArrayList<Proveedor> proveedor;

    public Producto() {
    }

    public Producto(int IdProducto, String Nombre, String Descripcion, float precio, float Existencia, int top_aux, ArrayList<Categoria> Categoria, ArrayList<Proveedor> proveedor) {
        this.IdProducto = IdProducto;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.precio = precio;
        this.Existencia = Existencia;
        this.top_aux = top_aux;
        this.Categoria = Categoria;
        this.proveedor = proveedor;
    }

    
    

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getExistencia() {
        return Existencia;
    }

    public void setExistencia(float Existencia) {
        this.Existencia = Existencia;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
    

    public ArrayList<Categoria> getCategoria() {
        return Categoria;
    }

    public void setCategoria(ArrayList<Categoria> Categoria) {
        this.Categoria = Categoria;
    }

    public ArrayList<Proveedor> getProveedor() {
        return proveedor;
    }

    public void setProveedor(ArrayList<Proveedor> proveedor) {
        this.proveedor = proveedor;
    }
    
    
    
    
    
    
}
