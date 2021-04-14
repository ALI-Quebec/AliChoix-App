package ca.ulaval.ima.ali_choix.network;

import com.mongodb.ConnectionString;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import ca.ulaval.ima.ali_choix.BuildConfig;
import ca.ulaval.ima.ali_choix.network.exceptions.NotFoundException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;

public class MongoDBClient {
    private final ConnectionString MONGODB_CONNECTION = new ConnectionString(BuildConfig.MONGODB_CONNECTION);
    private final String DATABASE_NAME = "aliChoix";
    private final String PRODUCTS_COLLECTION_NAME = "products";
    private final String HISTORY_COLLECTION_NAME = "history";
    private final String MISSING_PRODUCTS_COLLECTION_NAME = "missingproducts";
    private final String SCAN_COUNT_FIELD_NAME = "scanCount";

    public String findProduct(String productID) {
        MongoClient mongoClient = MongoClients.create(MONGODB_CONNECTION);
        MongoCollection<Document> productsCollection = mongoClient.getDatabase(DATABASE_NAME).getCollection(PRODUCTS_COLLECTION_NAME);
        FindIterable<Document> productSearch = productsCollection.find(eq("_id", productID));
        if (productSearch == null) {
            addToMissingProducts(mongoClient, productID);
            // This throws an exception so it never executes the rest
        }
        incrementHistoryCounter(mongoClient, productID);
        String productJson = productSearch.first().toJson();
        mongoClient.close();
        return productJson;
    }

    private void addToMissingProducts(MongoClient mongoClient, String productID) {
        MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE_NAME).getCollection(MISSING_PRODUCTS_COLLECTION_NAME);
        FindIterable<Document> search = collection.find(eq("_id", productID));
        // Only create the object if one doesn't exist yet
        if (search == null) {
            collection.insertOne(new Document("_id", productID));
        }
        mongoClient.close();
        throw new NotFoundException();
    }

    private void incrementHistoryCounter(MongoClient mongoClient, String productID) {
        MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE_NAME).getCollection(HISTORY_COLLECTION_NAME);
        Document productHistory = collection.findOneAndUpdate(eq("_id", productID), inc(SCAN_COUNT_FIELD_NAME, 1));
        if (productHistory == null) {
            // Create one if it doesn't exist yet
            collection.insertOne(new Document("_id", productID).append(SCAN_COUNT_FIELD_NAME, 1));
        }
    }
}
