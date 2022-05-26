package ejercicio3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ejercicio3.dao.ProductoDAO;
import ejercicio3.model.Producto;
import ejercicio3.model.ResponseMessage;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.List;

public class ProductoController {

    public static Route getProducto = (Request request, Response response) -> {
        ProductoDAO productoDAO = new ProductoDAO();
        ObjectMapper mapper = new ObjectMapper();
        response.type("application/json");
        response.status(200);
        String id = request.params(":id");
        if (id == null || id.isEmpty()) {
            response.status(400);
            return mapper.writeValueAsString(new ResponseMessage("Falta id"));
        }
        Producto producto = productoDAO.getProduct(id);
        if (producto == null) {
            response.status(404);
            return mapper.writeValueAsString(new ResponseMessage("Prducto no encontrado"));
        }
        return mapper.writeValueAsString(producto);
    };

    public static Route getProductos = (Request request, Response response) -> {
        ProductoDAO productoDAO = new ProductoDAO();
        ObjectMapper mapper = new ObjectMapper();
        response.type("application/json");
        response.status(200);
        List<Producto> productos = productoDAO.getProducts();
        return mapper.writeValueAsString(productos);
    };

    public static Route createProducto = (Request request, Response response) -> {
        ProductoDAO productoDAO = new ProductoDAO();
        ObjectMapper mapper = new ObjectMapper();
        response.type("application/json");
        response.status(201);
        Producto producto;
        try {
            producto = mapper.readValue(request.body(), Producto.class);
        } catch (Exception e) {
            response.status(400);
            return mapper.writeValueAsString(new ResponseMessage("Bad Request"));
        }
        if (producto == null || !producto.validate()) {
            response.status(400);
            return mapper.writeValueAsString(new ResponseMessage("Producto es null o invalido"));
        }
        try {
            productoDAO.saveProduct(producto);
        } catch (Exception e) {
            response.status(500);
            return mapper.writeValueAsString(new ResponseMessage("Error guardando en la base de datos"));
        }

        return mapper.writeValueAsString(new ResponseMessage("Producto guardado"));
    };

    public static Route replaceProducto = (Request request, Response response) -> {
        ProductoDAO productoDAO = new ProductoDAO();
        ObjectMapper mapper = new ObjectMapper();
        String id = request.params(":id");
        response.type("application/json");
        response.status(200);
        Producto producto;
        try {
            producto = mapper.readValue(request.body(), Producto.class);
        } catch (Exception e) {
            response.status(400);
            return mapper.writeValueAsString(new ResponseMessage("Bad Request"));
        }
        if (producto == null || !producto.validate() || !producto.getId().equals(id)) {
            response.status(400);
            return mapper.writeValueAsString(new ResponseMessage("Producto es null o invalido"));
        }
        try {
            productoDAO.replaceProduct(producto);
        } catch (Exception e) {
            response.status(500);
            return mapper.writeValueAsString(new ResponseMessage("Error guardando en la base de datos"));
        }

        return mapper.writeValueAsString(new ResponseMessage("Producto reemplazado"));
    };

    public static Route modifyProducto = (Request request, Response response) -> {
        ProductoDAO productoDAO = new ProductoDAO();
        ObjectMapper mapper = new ObjectMapper();
        String id = request.params(":id");
        response.type("application/json");
        response.status(200);
        Producto producto;
        try {
            producto = mapper.readValue(request.body(), Producto.class);
        } catch (Exception e) {
            response.status(400);
            return mapper.writeValueAsString(new ResponseMessage("Bad Request"));
        }
        if (producto == null || producto.getId() != null) {
            response.status(400);
            return mapper.writeValueAsString(new ResponseMessage("Producto es null o invalido"));
        }
        try {
            productoDAO.modifyProduct(mapper.readValue(request.body(), HashMap.class), id);
        } catch (Exception e) {
            response.status(500);
            return mapper.writeValueAsString(new ResponseMessage("Error actualizando en la base de datos"));
        }

        return mapper.writeValueAsString(new ResponseMessage("Producto actualizado"));
    };

    public static Route deleteProducto = (Request request, Response response) -> {
        ProductoDAO productoDAO = new ProductoDAO();
        ObjectMapper mapper = new ObjectMapper();
        String id = request.params(":id");
        response.type("application/json");
        response.status(200);
        if (id == null || id.isEmpty()) {
            response.status(400);
            return mapper.writeValueAsString(new ResponseMessage("Falta id"));
        }
        try {
            productoDAO.deleteProduct(id);
        } catch (Exception e) {
            response.status(500);
            return mapper.writeValueAsString(new ResponseMessage("Error eliminando de la base de datos"));
        }
        return mapper.writeValueAsString(new ResponseMessage("Producto eliminado"));
    };
}
