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
public class Proveedor {
    public int IdProveedor;
    public int CodProveedor;
    public String Nombre;
    public String Empresa;
    public String Telefono; 
    public String Descripcion;
    public String Direccion;
    private int top_aux;
    private ArrayList<Producto> Producto;

    public Proveedor() {
    }

    public Proveedor(int IdProveedor, int CodProveedor, String Nombre, String Empresa, String Telefono, String Descripcion, String Direccion, int top_aux, ArrayList<Producto> Producto) {
        this.IdProveedor = IdProveedor;
        this.CodProveedor = CodProveedor;
        this.Nombre = Nombre;
        this.Empresa = Empresa;
        this.Telefono = Telefono;
        this.Descripcion = Descripcion;
        this.Direccion = Direccion;
        this.top_aux = top_aux;
        this.Producto = Producto;
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    public int getCodProveedor() {
        return CodProveedor;
    }

    public void setCodProveedor(int CodProveedor) {
        this.CodProveedor = CodProveedor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String Empresa) {
        this.Empresa = Empresa;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public ArrayList<Producto> getProducto() {
        return Producto;
    }

    public void setProducto(ArrayList<Producto> Producto) {
        this.Producto = Producto;
    }

   
   
}