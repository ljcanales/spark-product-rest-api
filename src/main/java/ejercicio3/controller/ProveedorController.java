package ejercicio3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ejercicio3.dao.ProveedorDAO;
import ejercicio3.model.Proveedor;
import ejercicio3.model.ResponseMessage;
import spark.Request;
import spark.Response;
import spark.Route;

public class ProveedorController {
    public static Route getProveedor = (Request request, Response response) -> {
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        ObjectMapper mapper = new ObjectMapper();
        response.type("application/json");
        response.status(200);
        String id = request.params(":id");
        if (id == null || id.isEmpty()) {
            response.status(400);
            return mapper.writeValueAsString(new ResponseMessage("Falta id"));
        }
        Proveedor proveedor = proveedorDAO.getProveedor(id);
        if (proveedor == null) {
            response.status(404);
            return mapper.writeValueAsString(new ResponseMessage("Proveedor no encontrado"));
        }
        return mapper.writeValueAsString(proveedor);
    };
}
