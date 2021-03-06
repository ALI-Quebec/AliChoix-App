package ca.ulaval.ima.ali_choix.services;

import android.content.Context;

import java.util.List;

import ca.ulaval.ima.ali_choix.Infrastructure.LocalHistoryRepository;
import ca.ulaval.ima.ali_choix.Infrastructure.LocalHistoryRepositoryCollector;
import ca.ulaval.ima.ali_choix.domain.exceptions.HistoryLoadingException;
import ca.ulaval.ima.ali_choix.domain.exceptions.HistorySavingException;
import ca.ulaval.ima.ali_choix.domain.history.HistoryElement;
import ca.ulaval.ima.ali_choix.domain.history.HistoryElementFactory;
import ca.ulaval.ima.ali_choix.domain.history.HistoryRepository;
import ca.ulaval.ima.ali_choix.domain.history.HistoryRepositoryCollector;
import ca.ulaval.ima.ali_choix.domain.product.ProductId;

public class HistoryService {
    private HistoryRepository historyRepository;
    private HistoryElementFactory historyElementFactory;
    private HistoryRepositoryCollector historyRepositoryCollector;
    private Context context;
    private Boolean errorLoadingHistory = false;
    private Boolean errorSavingHistory = false;

    public HistoryService(Context context, HistoryRepositoryCollector historyRepositoryCollector, HistoryElementFactory historyElementFactory) {
        this.context = context;
        this.historyRepositoryCollector = historyRepositoryCollector;
        loadHistory();
        this.historyElementFactory = historyElementFactory;
    }

    public HistoryService(Context context) {
        this.context = context;
        historyRepositoryCollector = new LocalHistoryRepositoryCollector();
        loadHistory();
        historyElementFactory = new HistoryElementFactory();
    }

    public void addHistoryElement(String productId, String image_front_url, String productName) {
        HistoryElement historyElement = historyElementFactory.create(productId, image_front_url, productName);
        historyRepository.addElement(historyElement);
        saveHistory();
    }

    public void removeHistoryElement(String productId) {
        ProductId productIdToRemove = new ProductId(productId);
        historyRepository.removeElement(productIdToRemove);
        saveHistory();
    }

    public List<HistoryElement> getHistory() {
        return historyRepository.getHistory();
    }

    public String getLastSearchedProductId() {
        return historyRepository.getLastSearchedProductId().toString();
    }

    public boolean historyLoadProblemState() {
        return errorLoadingHistory;
    }

    public void resetHistoryLoadingProblemState() {
        errorLoadingHistory = false;
    }

    public boolean historySavingProblemState() {
        return errorSavingHistory;
    }

    public void resetHistorySavingProblemState() {
        errorSavingHistory = false;
    }

    private void saveHistory() {
        try {
            historyRepositoryCollector.saveHistory(historyRepository, context);
        } catch (HistorySavingException e) {
            errorSavingHistory = true;
        }
    }

    private void loadHistory() {
        try {
            historyRepository = historyRepositoryCollector.loadHistory(context);
        } catch (HistoryLoadingException e) {
            errorLoadingHistory = true;
            historyRepository = new LocalHistoryRepository();
        }
    }
}
