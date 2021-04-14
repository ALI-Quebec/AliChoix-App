package ca.ulaval.ima.ali_choix.network;

import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.util.regex.Pattern;

import ca.ulaval.ima.ali_choix.BuildConfig;
import ca.ulaval.ima.ali_choix.network.exceptions.NotFoundException;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Updates.inc;

public class MongoDBClient {
    private static final MongoClientURI MONGODB_CONNECTION = new MongoClientURI(BuildConfig.MONGODB_CONNECTION);
    private static final String DATABASE_NAME = "aliChoix";
    private static final String PRODUCTS_COLLECTION_NAME = "products";
    private static final String HISTORY_COLLECTION_NAME = "history";
    private static final String MISSING_PRODUCTS_COLLECTION_NAME = "missingproducts";
    private static final String SCAN_COUNT_FIELD_NAME = "scanCount";

    public static String findProduct(String productID) {
        MongoClient mongoClient = new MongoClient(MONGODB_CONNECTION);
        MongoCollection<Document> productsCollection = mongoClient.getDatabase(DATABASE_NAME).getCollection(PRODUCTS_COLLECTION_NAME);
        Pattern productRegex = Pattern.compile("\\d*"+productID);
        Document productSearch = productsCollection.find(regex("_id", productRegex)).first();
        if (productSearch == null) {
            addToMissingProducts(mongoClient, productID);
            throw new NotFoundException();
        }
        incrementHistoryCounter(mongoClient, productID);
        String productJson = productSearch.toJson();
        mongoClient.close();
        return productJson;
    }

    private static void addToMissingProducts(MongoClient mongoClient, String productID) {
        MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE_NAME).getCollection(MISSING_PRODUCTS_COLLECTION_NAME);
        Document search = collection.find(eq("_id", productID)).first();
        // Only create the object if one doesn't exist yet
        if (search == null) {
            collection.insertOne(new Document("_id", productID));
        }
        mongoClient.close();
    }

    private static void incrementHistoryCounter(MongoClient mongoClient, String productID) {
        MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE_NAME).getCollection(HISTORY_COLLECTION_NAME);
        Document productHistory = collection.findOneAndUpdate(eq("_id", productID), inc(SCAN_COUNT_FIELD_NAME, 1));
        if (productHistory == null) {
            // Create one if it doesn't exist yet
            collection.insertOne(new Document("_id", productID).append(SCAN_COUNT_FIELD_NAME, 1));
        }
    }
}
