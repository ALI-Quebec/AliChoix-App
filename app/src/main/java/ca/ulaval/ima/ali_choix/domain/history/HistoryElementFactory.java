package ca.ulaval.ima.ali_choix.domain.history;

import ca.ulaval.ima.ali_choix.domain.product.ProductId;

public class HistoryElementFactory {
    public HistoryElement create(String productId, String image_front_url, String productName) {
        ProductId addedProductId = new ProductId(productId);

        return new HistoryElement(addedProductId, image_front_url, productName);
    }
}
