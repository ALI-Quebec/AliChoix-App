package ca.ulaval.ima.ali_choix.domain;

import java.util.List;

public interface HistoryRepository {

    void addElement(HistoryElement historyElement);

    void removeElement(ProductId productId);

    void removeAllElements();

    List<HistoryElement> getHistory();

    ProductId getLastSearchedProductId();
}
