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
        getHistoryFromDevice();
        historyElementFactory = new HistoryElementFactory();
    }

    public void addHistoryElement(String productId, String image_front_url, String productName){
        HistoryElement addedHistoryElement = historyElementFactory.create(productId,image_front_url,productName);
        historyRepository.addElement(addedHistoryElement);
        saveHistoryOnDevice();
    }

    public void removeHistoryElement(String productId){
        ProductId removedProductId = new ProductId(productId);
        historyRepository.removeElement(removedProductId);
        saveHistoryOnDevice();
    }

    public void removeAllHistory(){
        historyRepository.removeAllElements();
        saveHistoryOnDevice();
    }

    public List<HistoryElement> getHistory(){
        return historyRepository.getHistory();
    }

    public String getLastSearchedProductId(){
        return historyRepository.getLastSearchedProductId().toString();
    }

    private void saveHistoryOnDevice(){
      //TODO
    }

    private void getHistoryFromDevice(){
        //TODO
    }
}
