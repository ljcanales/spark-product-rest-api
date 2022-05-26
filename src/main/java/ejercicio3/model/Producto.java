package ejercicio3.model;

import com.fasterxml.jackson.annotation.JsonSetter;

import static ejercicio3.utils.Constants.HOST;

public class Producto {
    private String id;
    private String nombre;
    private String descripcion;
    private float precio;
    private int stock;
    private String proveedor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @JsonSetter("proveedor")
    public String getProveedorUrl() {
        return proveedor != null ? HOST + "/proveedor/" + proveedor : null;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public boolean validate() {
        if (id == null || nombre == null || descripcion == null || precio == 0) {
            return false;
        }
        return true;
    }

}
