package ca.ulaval.ima.ali_choix.domain.history;

import java.util.List;

import ca.ulaval.ima.ali_choix.domain.product.ProductId;

public interface HistoryRepository {

    void addElement(HistoryElement historyElement);

    void removeElement(ProductId productId);

    void removeAllElements();

    List<HistoryElement> getHistory();

    ProductId getLastSearchedProductId();
}
