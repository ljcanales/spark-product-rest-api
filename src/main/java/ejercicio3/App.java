package ejercicio3;

import ejercicio3.controller.ProductoController;
import ejercicio3.controller.ProveedorController;

import static spark.Spark.*;
public class App {
    public static void main(String[] args) {

        get("/producto/:id", ProductoController.getProducto);
        get("/productos", ProductoController.getProductos);
        post("/producto", ProductoController.createProducto);
        put("/producto/:id", ProductoController.replaceProducto);
        patch("/producto/:id", ProductoController.modifyProducto);
        delete("/producto/:id", ProductoController.deleteProducto);

        get("/proveedor/:id", ProveedorController.getProveedor);
    }
}
