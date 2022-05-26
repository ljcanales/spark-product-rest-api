package ejercicio3.db;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static ejercicio3.utils.Constants.DB_NAME;
import static ejercicio3.utils.Constants.DB_URI;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DBService {
    private static DBService instance = null;
    private final MongoDatabase database;

    private DBService() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient client = MongoClients.create(DB_URI);
        database = client.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
    }

    public static DBService getInstance() {
        if (instance == null) {

            instance = new DBService();
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
