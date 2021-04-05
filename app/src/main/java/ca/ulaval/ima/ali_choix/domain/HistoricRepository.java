package ca.ulaval.ima.ali_choix.domain;

import java.util.List;

public interface HistoricRepository {

    void addElement(HistoricElement historicElement);

    void removeElement(ProductId productId);

    void removeAllElement();

    List<HistoricElement> getHistoric();

    ProductId getLastSearchedProductId();
}
