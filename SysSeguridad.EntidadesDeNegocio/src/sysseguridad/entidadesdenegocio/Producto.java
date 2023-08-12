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
    private int IdCategoria;
    private int IdProveedor;
    private String Nombre;
    private String Descripcion;
    private float Precio;
    private float Existencia;
    private int top_aux;
    private Categoria categoria;
    private Proveedor proveedor;
    
    public Producto() {
    }

    public Producto(int IdProducto, int IdCategoria, int IdProveedor, String Nombre, String Descripcion, float Precio, float Existencia, Categoria categoria, Proveedor proveedor) {
        this.IdProducto = IdProducto;
        this.IdCategoria = IdCategoria;
        this.IdProveedor = IdProveedor;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.Existencia = Existencia;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
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
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    

    
}