package ejercicio3.dao;

import com.mongodb.client.MongoCollection;
import ejercicio3.db.DBService;
import ejercicio3.model.Proveedor;

import static com.mongodb.client.model.Filters.eq;

public class ProveedorDAO {
    private final MongoCollection<Proveedor> proveedorCollection;

    public ProveedorDAO() {
        this.proveedorCollection = DBService.getInstance().getDatabase().getCollection("Proveedor", Proveedor.class);
    }

    public Proveedor getProveedor(String id) {
        return proveedorCollection.find(eq("_id", id)).first();
    }
}
