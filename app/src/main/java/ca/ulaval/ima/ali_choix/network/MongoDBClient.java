package ca.ulaval.ima.ali_choix.network;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.util.regex.Pattern;

import ca.ulaval.ima.ali_choix.BuildConfig;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Updates.inc;

public class MongoDBClient {
    private static final String DATABASE_NAME = "aliChoix";
    private static final String PRODUCTS_COLLECTION_NAME = "products";
    private static final String HISTORY_COLLECTION_NAME = "history";
    private static final String MISSING_PRODUCTS_COLLECTION_NAME = "missingproducts";
    private static final String SCAN_COUNT_FIELD_NAME = "scanCount";

    public static void logProductScannedInHistory(String productID) {
        try {
            MongoClientURI mongoClientURI = new MongoClientURI(BuildConfig.MONGODB_CONNECTION);
            MongoClient mongoClient = new MongoClient(mongoClientURI);
            MongoCollection<Document> productsCollection = mongoClient.getDatabase(DATABASE_NAME).getCollection(PRODUCTS_COLLECTION_NAME);
            Pattern productRegex = Pattern.compile("\\d*" + productID); // Regex to account for 12 digit codes (they have 13 in the DB)
            Document product = productsCollection.find(regex("_id", productRegex)).first();

            if (product == null) {
                addToMissingProductsCollection(mongoClient, productID);
            }

            incrementProductCounter(mongoClient, productID);
            mongoClient.close();
        } catch (Exception e) {
            throw new MongoDBClientException(e);
        }

    }

    private static void addToMissingProductsCollection(MongoClient mongoClient, String productID) {
        MongoCollection<Document> missingProductsCollection = mongoClient.getDatabase(DATABASE_NAME).getCollection(MISSING_PRODUCTS_COLLECTION_NAME);
        Document product = missingProductsCollection.find(eq("_id", productID)).first();

        if (product == null) {
            missingProductsCollection.insertOne(new Document("_id", productID));
        }
    }

    private static void incrementProductCounter(MongoClient mongoClient, String productID) {
        MongoCollection<Document> historyCollection = mongoClient.getDatabase(DATABASE_NAME).getCollection(HISTORY_COLLECTION_NAME);
        Document product = historyCollection.findOneAndUpdate(eq("_id", productID), inc(SCAN_COUNT_FIELD_NAME, 1));
        if (product == null) {
            historyCollection.insertOne(new Document("_id", productID).append(SCAN_COUNT_FIELD_NAME, 1));
        }
    }
}
