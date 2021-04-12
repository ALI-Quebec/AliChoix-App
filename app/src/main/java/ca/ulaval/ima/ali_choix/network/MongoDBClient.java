package ca.ulaval.ima.ali_choix.network;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import ca.ulaval.ima.ali_choix.BuildConfig;

import static com.mongodb.client.model.Filters.eq;

public class MongoDBClient {
    private final ConnectionString MONGODB_CONNECTION = new ConnectionString(BuildConfig.MONGODB_CONNECTION);
    private final String DATABASE_NAME = "aliChoix";
    private final String PRODUCTS_COLLECTION_NAME = "products";
    private final String HISTORY_COLLECTION_NAME = "history";
    private final String MISSING_PRODUCTS_COLLECTION_NAME = "missingproducts";

    public String findProduct(String productID) {
        MongoClient mongoClient = MongoClients.create(MONGODB_CONNECTION);
        MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE_NAME).getCollection(PRODUCTS_COLLECTION_NAME);
        //TODO what if not found?
        String productJson = collection.find(eq("_id", productID)).first().toJson();
        mongoClient.close();
        return productJson;
    }

    public String findHistoryOfProduct(String productID) {
        MongoClient mongoClient = MongoClients.create(MONGODB_CONNECTION);
        MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE_NAME).getCollection(HISTORY_COLLECTION_NAME);
        //TODO what if not found?
        String productJson = collection.find(eq("_id", productID)).first().toJson();
        mongoClient.close();
        return productJson;
    }
}
