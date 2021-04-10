package ca.ulaval.ima.ali_choix.domain;

import java.time.LocalDateTime;

public class HistoryElementFactory {
    public HistoryElement create(String productId, String image_front_url, String productName) {
        ProductId addedProductId = new ProductId(productId);
        LocalDateTime timeAddedToHistoric = LocalDateTime.now();
        return new HistoryElement(addedProductId,image_front_url,productName,timeAddedToHistoric);
    }
}
