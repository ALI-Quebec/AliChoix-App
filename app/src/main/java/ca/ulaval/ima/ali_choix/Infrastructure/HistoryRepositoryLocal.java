package ca.ulaval.ima.ali_choix.Infrastructure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import ca.ulaval.ima.ali_choix.domain.HistoryElement;
import ca.ulaval.ima.ali_choix.domain.HistoryRepository;
import ca.ulaval.ima.ali_choix.domain.ProductId;
import ca.ulaval.ima.ali_choix.domain.exceptions.HistoricEmptyException;

public class HistoryRepositoryLocal implements HistoryRepository {
    private LinkedHashMap<ProductId, HistoryElement> historic;

    public HistoryRepositoryLocal(){
        historic = new LinkedHashMap<>();
    }

    @Override
    public void addElement(HistoryElement historyElement) {
        if(historic.containsKey(historyElement.getProductId())){
            historic.remove(historyElement.getProductId());
        }
        historic.put(historyElement.getProductId(), historyElement);
    }

    @Override
    public void removeElement(ProductId productId) {
        historic.remove(productId);
    }

    @Override
    public void removeAllElement() {
        historic.clear();
    }

    @Override
    public List<HistoryElement> getHistoric() {
        return new ArrayList<>(historic.values());
    }

    @Override
    public ProductId getLastSearchedProductId() {
        if(historic.size() == 0){
            throw new HistoricEmptyException();
        }
        return new ArrayList<>(historic.keySet()).get(historic.size() - 1);
    }
}
