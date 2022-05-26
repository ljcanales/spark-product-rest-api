package ejercicio3.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import ejercicio3.db.DBService;
import ejercicio3.model.Producto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ProductoDAO {
    private final MongoCollection<Producto> productoCollection;

    public ProductoDAO() {
        this.productoCollection = DBService.getInstance().getDatabase().getCollection("Producto", Producto.class);
    }

    public Producto getProduct(String id) {
        return productoCollection.find(eq("_id", id)).first();
    }

    public List<Producto> getProducts() {
        List<Producto> productos = new LinkedList<>();
        FindIterable<Producto> iterable = productoCollection.find();
        for (Producto producto : iterable) {
            productos.add(producto);
        }
        return productos;
    }

    public void saveProduct(Producto producto) {
        productoCollection.insertOne(producto);
    }

    public void replaceProduct(Producto producto) {
        productoCollection.replaceOne(eq("_id", producto.getId()),producto);
    }

    public void deleteProduct(String id) {
        productoCollection.deleteOne(eq("_id", id));
    }

    public void modifyProduct(HashMap fields, String id) {
        BasicDBObject updateBson = new BasicDBObject();
        updateBson.append("$set", fields);
        productoCollection.updateOne(eq("_id", id), updateBson);
    }
}
