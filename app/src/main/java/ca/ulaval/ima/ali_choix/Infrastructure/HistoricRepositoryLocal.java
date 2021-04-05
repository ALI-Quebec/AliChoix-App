package ca.ulaval.ima.ali_choix.Infrastructure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import ca.ulaval.ima.ali_choix.domain.HistoricElement;
import ca.ulaval.ima.ali_choix.domain.HistoricRepository;
import ca.ulaval.ima.ali_choix.domain.ProductId;
import ca.ulaval.ima.ali_choix.domain.exceptions.HistoricEmptyException;

public class HistoricRepositoryLocal implements HistoricRepository {
    private LinkedHashMap<ProductId,HistoricElement> historic;

    public HistoricRepositoryLocal(){
        historic = new LinkedHashMap<>();
    }

    @Override
    public void addElement(HistoricElement historicElement) {
        if(historic.containsKey(historicElement.getProductId())){
            historic.remove(historicElement.getProductId());
        }
        historic.put(historicElement.getProductId(),historicElement);
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
    public List<HistoricElement> getHistoric() {
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
