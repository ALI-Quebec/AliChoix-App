package ca.ulaval.ima.ali_choix.domain;

import java.time.LocalDateTime;

public class HistoricElementFactory {
    public HistoricElement create(String productId, String image_front_url, String productName) {
        ProductId addedProductId = new ProductId(productId);
        LocalDateTime timeAddedToHistoric = LocalDateTime.now();
        return new HistoricElement(addedProductId,image_front_url,productName,timeAddedToHistoric);
    }
}
