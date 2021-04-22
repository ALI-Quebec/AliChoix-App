package ca.ulaval.ima.ali_choix.Infrastructure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import ca.ulaval.ima.ali_choix.domain.exceptions.HistoryEmptyException;
import ca.ulaval.ima.ali_choix.domain.history.HistoryElement;
import ca.ulaval.ima.ali_choix.domain.history.HistoryRepository;
import ca.ulaval.ima.ali_choix.domain.product.ProductId;

public class LocalHistoryRepository implements HistoryRepository {
    private LinkedHashMap<ProductId, HistoryElement> history;

    public LocalHistoryRepository() {
        history = new LinkedHashMap<>();
    }

    public LocalHistoryRepository(List<HistoryElement> historyElements) {
        history = new LinkedHashMap<>();

        for (HistoryElement historyElement : historyElements) {
            history.put(historyElement.getProductId(), historyElement);
        }
    }

    @Override
    public void addElement(HistoryElement historyElement) {
        if (history.containsKey(historyElement.getProductId())) {
            history.remove(historyElement.getProductId());
        }

        history.put(historyElement.getProductId(), historyElement);
    }

    @Override
    public void removeElement(ProductId productId) {
        history.remove(productId);
    }

    @Override
    public List<HistoryElement> getHistory() {
        return new ArrayList<>(history.values());
    }

    @Override
    public ProductId getLastSearchedProductId() {
        if (history.size() == 0) {
            throw new HistoryEmptyException();
        }

        return new ArrayList<>(history.keySet()).get(history.size() - 1);
    }
}
