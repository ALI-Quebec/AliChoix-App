package ca.ulaval.ima.ali_choix.services;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.ima.ali_choix.domain.exceptions.HistoryLoadingException;
import ca.ulaval.ima.ali_choix.domain.exceptions.HistorySavingException;
import ca.ulaval.ima.ali_choix.domain.history.HistoryRepositoryCollector;
import ca.ulaval.ima.ali_choix.domain.history.HistoryElement;
import ca.ulaval.ima.ali_choix.domain.history.HistoryElementFactory;
import ca.ulaval.ima.ali_choix.domain.history.HistoryRepository;
import ca.ulaval.ima.ali_choix.domain.product.ProductId;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HistoryServiceTest {
    private static final String PRODUCT_ID = "1";
    private static final ProductId LAST_SEARCH_PRODUCT_ID = new ProductId("10");
    private static final String IMAGE_URL = "image url";
    private static final String PRODUCT_NAME = "product name";
    private static final ProductId productId = new ProductId(PRODUCT_ID);
    private static final List<HistoryElement> HISTORY_ELEMENTS = new ArrayList<>();
    private HistoryElement historyElement;
    private HistoryService historyService;
    @Mock private Context context;
    @Mock private HistoryRepositoryCollector historyRepositoryCollector;
    @Mock private HistoryRepository historyRepository;
    @Mock private HistoryElementFactory historyElementFactory;

    @Before
    public void setUp() {
        historyElement = new HistoryElement(productId,IMAGE_URL,PRODUCT_NAME);

        when(historyElementFactory.create(PRODUCT_ID,IMAGE_URL, PRODUCT_NAME)).thenReturn(historyElement);
        when(historyRepositoryCollector.loadHistory(context)).thenReturn(historyRepository);

        historyService = new HistoryService(context, historyRepositoryCollector,historyElementFactory);
    }

    @Test
    public void whenAddingHistoryElement_thenElementIsAddedToRepository() {
        historyService.addHistoryElement(PRODUCT_ID,IMAGE_URL, PRODUCT_NAME);

        verify(historyRepository).addElement(historyElement);
    }

    @Test
    public void whenAddingHistoryElement_thenHistoryIsSaved() {
        historyService.addHistoryElement(PRODUCT_ID,IMAGE_URL, PRODUCT_NAME);

        verify(historyRepositoryCollector).saveHistory(historyRepository,context);
    }

    @Test
    public void whenRemovingHistoryElement_thenElementIsRemovedFromRepository() {
        historyService.removeHistoryElement(PRODUCT_ID);

        verify(historyRepository).removeElement(productId);
    }

    @Test
    public void whenRemovingHistoryElement_thenHistoryIsSaved() {
        historyService.removeHistoryElement(PRODUCT_ID);

        verify(historyRepositoryCollector).saveHistory(historyRepository,context);
    }

    @Test
    public void whenGettingHistory_thenHistoryRepositoryIsCalled() {
        when(historyService.getHistory()).thenReturn(HISTORY_ELEMENTS);
        List<HistoryElement> result = historyService.getHistory();

        assertEquals(result, HISTORY_ELEMENTS);
    }

    @Test
    public void whenGettingLastSearchedProductId_thenLastSearchedProductIdIsReturned() {
        when(historyRepository.getLastSearchedProductId()).thenReturn(LAST_SEARCH_PRODUCT_ID);
        String result = historyService.getLastSearchedProductId();

        assertEquals(result, LAST_SEARCH_PRODUCT_ID.toString());
    }

    @Test
    public void whenCatchingHistorySavingException_thenErrorSavingHistoryIsTrue() {
        doThrow(new HistorySavingException())
                .when(historyRepositoryCollector)
                .saveHistory(historyRepository, context);

        historyService.addHistoryElement(PRODUCT_ID,IMAGE_URL, PRODUCT_NAME);

        assertEquals(historyService.historySavingProblemState(), true);
    }

    @Test
    public void givenHistorySavingProblemStateIsTrue_whenResetingHistorySavingProblemState_thenErrorSavingHistoryIsFalse() {
        doThrow(new HistorySavingException())
                .when(historyRepositoryCollector)
                .saveHistory(historyRepository, context);
        historyService.addHistoryElement(PRODUCT_ID,IMAGE_URL, PRODUCT_NAME);
        assertEquals(historyService.historySavingProblemState(), true);

        historyService.resetHistorySavingProblemState();

        assertEquals(historyService.historySavingProblemState(), false);
    }

    @Test
    public void whenCatchingHistoryLoadingException_thenErrorLoadingHistoryIsTrue() {
        doThrow(new HistoryLoadingException())
                .when(historyRepositoryCollector)
                .loadHistory(context);

        historyService = new HistoryService(context, historyRepositoryCollector,historyElementFactory);

        assertEquals(historyService.historyLoadProblemState(), true);
    }

    @Test
    public void givenHistoryLoadProblemStateIsTrue_whenResetingHistoryLoadingProblemState_thenErrorLoadingHistoryIsFalse() {
        doThrow(new HistoryLoadingException())
                .when(historyRepositoryCollector)
                .loadHistory(context);
        historyService = new HistoryService(context, historyRepositoryCollector,historyElementFactory);
        assertEquals(historyService.historyLoadProblemState(), true);

        historyService.resetHistoryLoadingProblemState();

        assertEquals(historyService.historyLoadProblemState(), false);
    }
}
