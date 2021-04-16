package ca.ulaval.ima.ali_choix.services;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ca.ulaval.ima.ali_choix.domain.history.HistoryDataManager;
import ca.ulaval.ima.ali_choix.domain.history.HistoryElement;
import ca.ulaval.ima.ali_choix.domain.history.HistoryElementFactory;
import ca.ulaval.ima.ali_choix.domain.history.HistoryRepository;
import ca.ulaval.ima.ali_choix.domain.product.ProductId;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HistoryServiceTest {
    private static final String PRODUCT_ID = "1";
    private static final String IMAGE_URL = "image link";
    private static final String PRODUCT_NAME = "product name";
    private static final ProductId productId = new ProductId(PRODUCT_ID);
    private HistoryElement historyElement;

    private HistoryService historyService;
    @Mock private Context context;
    @Mock private HistoryDataManager historyDataManager;
    @Mock private HistoryRepository historyRepository;
    @Mock private HistoryElementFactory historyElementFactory;

    @Before
    public void setUp() {
        historyElement = new HistoryElement(productId,IMAGE_URL,PRODUCT_NAME,null);

        when(historyElementFactory.create(PRODUCT_ID,IMAGE_URL, PRODUCT_NAME)).thenReturn(historyElement);
        when(historyDataManager.loadHistory(context)).thenReturn(historyRepository);

        historyService = new HistoryService(context,historyDataManager,historyElementFactory);

    }

    @Test
    public void whenAddingHistoryElement_thenElementIsAddedToRepository() {
        historyService.addHistoryElement(PRODUCT_ID,IMAGE_URL, PRODUCT_NAME);

        verify(historyRepository).addElement(historyElement);
    }

    @Test
    public void whenAddingHistoryElement_thenHistoryIsSaved() {
        historyService.addHistoryElement(PRODUCT_ID,IMAGE_URL, PRODUCT_NAME);

        verify(historyDataManager).saveHistory(historyRepository,context);
    }

    @Test
    public void whenRemovingHistoryElement_thenElementIsRemovedFromRepository() {
        historyService.removeHistoryElement(PRODUCT_ID);

        verify(historyRepository).removeElement(productId);
    }

    @Test
    public void whenRemovingHistoryElement_thenHistoryIsSaved() {
        historyService.removeHistoryElement(PRODUCT_ID);

        verify(historyDataManager).saveHistory(historyRepository,context);
    }

    @Test
    public void whenRemovingAllHistory_thenAllElementAreRemovedFromRepository() {
        historyService.removeAllHistory();

        verify(historyRepository).removeAllElements();
    }

    @Test
    public void whenRemovingAllHistory_thenHistoryIsSaved() {
        historyService.removeAllHistory();

        verify(historyDataManager).saveHistory(historyRepository,context);
    }
}
