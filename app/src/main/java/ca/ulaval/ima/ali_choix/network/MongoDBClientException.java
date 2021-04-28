package ca.ulaval.ima.ali_choix.network;

public class MongoDBClientException extends RuntimeException {
    private final String error;
    private final String description;

    public MongoDBClientException(Exception e) {
        super(e);
        this.error = e.toString();
        this.description = e.getMessage();
    }
}
