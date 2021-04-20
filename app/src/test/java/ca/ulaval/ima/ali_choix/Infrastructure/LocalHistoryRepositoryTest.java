package ca.ulaval.ima.ali_choix.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ima.ali_choix.domain.history.HistoryElement;
import ca.ulaval.ima.ali_choix.domain.history.HistoryRepository;
import ca.ulaval.ima.ali_choix.domain.product.ProductId;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LocalHistoryRepositoryTest {
    ProductId FIRST_PRODUCT_ID = new ProductId("1");
    ProductId SECOND_PRODUCT_ID = new ProductId("2");
    ProductId THIRD_PRODUCT_ID = new ProductId("3");
    ProductId FOURTH_PRODUCT_ID = new ProductId("4");

    HistoryElement FIRST_ELEMENT = new HistoryElement(FIRST_PRODUCT_ID,null,null);
    HistoryElement SECOND_ELEMENT = new HistoryElement(SECOND_PRODUCT_ID,null,null);
    HistoryElement THIRD_ELEMENT = new HistoryElement(THIRD_PRODUCT_ID,null,null);
    HistoryElement FOURTH_ELEMENT = new HistoryElement(FOURTH_PRODUCT_ID,null,null);

    HistoryRepository historyRepository;

    @Before
    public void setUp() {
        historyRepository = new LocalHistoryRepository();
        historyRepository.addElement(FIRST_ELEMENT);
        historyRepository.addElement(SECOND_ELEMENT);
        historyRepository.addElement(THIRD_ELEMENT);
    }

    @Test
    public void givenPresentElement_whenAddingExistingProduct_thenAddedProductIsLast(){
        assertEquals(THIRD_PRODUCT_ID, historyRepository.getLastSearchedProductId());

        historyRepository.addElement(FOURTH_ELEMENT);

        assertEquals(FOURTH_PRODUCT_ID, historyRepository.getLastSearchedProductId());
    }

    @Test
    public void givenPresentElement_whenAddingExistingProduct_thenMoveProductAtTheEnd(){
        assertEquals(THIRD_PRODUCT_ID, historyRepository.getLastSearchedProductId());

        historyRepository.addElement(FIRST_ELEMENT);

        assertEquals(FIRST_PRODUCT_ID, historyRepository.getLastSearchedProductId());
    }

    @Test
    public void givenPresentElement_whenAddingExistingProduct_thenHistorySizeDoesNotChange(){
        int originalSize = historyRepository.getHistory().size();

        historyRepository.addElement(FIRST_ELEMENT);

        int newSize = historyRepository.getHistory().size();
        assertEquals(originalSize, newSize);
    }

    @Test
    public void givenPresentElement_whenRemoving_elementIsRemoved(){
        historyRepository.removeElement(FIRST_PRODUCT_ID);

        assertFalse(historyRepository.getHistory().contains(FIRST_ELEMENT));
    }
}