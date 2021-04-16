package ca.ulaval.ima.ali_choix.services;

import android.content.Context;

import java.util.List;

import ca.ulaval.ima.ali_choix.Infrastructure.HistoryRepositoryLocal;
import ca.ulaval.ima.ali_choix.Infrastructure.LocalHistoryFile;
import ca.ulaval.ima.ali_choix.domain.history.HistoryElement;
import ca.ulaval.ima.ali_choix.domain.history.HistoryElementFactory;
import ca.ulaval.ima.ali_choix.domain.history.HistoryRepository;
import ca.ulaval.ima.ali_choix.domain.product.ProductId;

public class HistoryService {
    private HistoryRepository historyRepository;
    private HistoryElementFactory historyElementFactory;
    private LocalHistoryFile localHistoryFile;
    private Context context;

    public HistoryService(Context context){
        this.context = context;
        localHistoryFile = new LocalHistoryFile();
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
       localHistoryFile.saveHistory(historyRepository,context);
    }

    private void getHistoryFromDevice(){
        historyRepository = localHistoryFile.loadHistory(context);
    }
}
