package ca.ulaval.ima.ali_choix.services;

import android.content.Context;

import java.util.List;

import ca.ulaval.ima.ali_choix.Infrastructure.LocalHistoryFile;
import ca.ulaval.ima.ali_choix.domain.history.HistoryDataManager;
import ca.ulaval.ima.ali_choix.domain.history.HistoryElement;
import ca.ulaval.ima.ali_choix.domain.history.HistoryElementFactory;
import ca.ulaval.ima.ali_choix.domain.history.HistoryRepository;
import ca.ulaval.ima.ali_choix.domain.product.ProductId;

public class HistoryService {
    private HistoryRepository historyRepository;
    private HistoryElementFactory historyElementFactory;
    private HistoryDataManager historyDataManager;
    private Context context;

    public HistoryService(Context context, HistoryDataManager historyDataManager,HistoryElementFactory historyElementFactory ){
        this.context = context;
        this.historyDataManager = historyDataManager;
        loadHistory();
        this.historyElementFactory = historyElementFactory;
    }

    public HistoryService(Context context){
        this.context = context;
        historyDataManager = new LocalHistoryFile();
        loadHistory();
        historyElementFactory = new HistoryElementFactory();
    }

    public void addHistoryElement(String productId, String image_front_url, String productName){
        HistoryElement addedHistoryElement = historyElementFactory.create(productId,image_front_url,productName);
        historyRepository.addElement(addedHistoryElement);
        saveHistory();
    }

    public void removeHistoryElement(String productId){
        ProductId removedProductId = new ProductId(productId);
        historyRepository.removeElement(removedProductId);
        saveHistory();
    }

    public void removeAllHistory(){
        historyRepository.removeAllElements();
        saveHistory();
    }

    public List<HistoryElement> getHistory(){
        return historyRepository.getHistory();
    }

    public String getLastSearchedProductId(){
        return historyRepository.getLastSearchedProductId().toString();
    }

    private void saveHistory(){
       historyDataManager.saveHistory(historyRepository,context);
    }

    private void loadHistory(){
        historyRepository = historyDataManager.loadHistory(context);
    }
}
