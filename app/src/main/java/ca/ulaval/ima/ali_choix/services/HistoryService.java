package ca.ulaval.ima.ali_choix.services;

import java.util.List;

import ca.ulaval.ima.ali_choix.Infrastructure.HistoryRepositoryLocal;
import ca.ulaval.ima.ali_choix.domain.HistoryElement;
import ca.ulaval.ima.ali_choix.domain.HistoryElementFactory;
import ca.ulaval.ima.ali_choix.domain.HistoryRepository;
import ca.ulaval.ima.ali_choix.domain.ProductId;

public class HistoryService {
    private HistoryRepository historyRepository;
    private HistoryElementFactory historyElementFactory;

    public HistoryService(){
        historyRepository = new HistoryRepositoryLocal();
        getHistoricFromDevice();
        historyElementFactory = new HistoryElementFactory();
    }

    public void addHistoricElement(String productId, String image_front_url, String productName){
        HistoryElement addedHistoryElement = historyElementFactory.create(productId,image_front_url,productName);
        historyRepository.addElement(addedHistoryElement);
        saveHistoricOnDevice();
    }

    public void removeHistoricElement(String productId){
        ProductId removedProductId = new ProductId(productId);
        historyRepository.removeElement(removedProductId);
        saveHistoricOnDevice();
    }

    public void removeAllHistoric(){
        historyRepository.removeAllElement();
        saveHistoricOnDevice();
    }

    public List<HistoryElement> getHistoric(){
        return historyRepository.getHistoric();
    }

    public String getLastSearchedProductId(){
        return historyRepository.getLastSearchedProductId().toString();
    }

    private void saveHistoricOnDevice(){
      //TODO
    }

    private void getHistoricFromDevice(){
        //TODO
    }
}
