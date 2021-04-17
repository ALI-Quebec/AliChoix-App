package ca.ulaval.ima.ali_choix.domain.history;

import java.io.Serializable;
import java.time.LocalDateTime;

import ca.ulaval.ima.ali_choix.domain.product.ProductId;

public class HistoryElement implements Serializable {
    private ProductId productId;
    private String image_front_url;
    private String productName;
    private LocalDateTime timeAddedToHistory;

    public HistoryElement(ProductId productId, String image_front_url, String productName, LocalDateTime timeAddedToHistory) {
        this.productId = productId;
        this.image_front_url = image_front_url;
        this.productName = productName;
        this.timeAddedToHistory = timeAddedToHistory;
    }

    public ProductId getProductId() {
        return productId;
    }

    public String getImage_front_url() {
        return image_front_url;
    }

    public String getProductName() {
        return productName;
    }

    public LocalDateTime getTimeAddedToHistory() {
        return timeAddedToHistory;
    }
}
