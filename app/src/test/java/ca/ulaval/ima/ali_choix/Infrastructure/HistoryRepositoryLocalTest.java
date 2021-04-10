package ca.ulaval.ima.ali_choix.Infrastructure;

import org.junit.Test;

import ca.ulaval.ima.ali_choix.domain.HistoryElement;
import ca.ulaval.ima.ali_choix.domain.HistoryRepository;
import ca.ulaval.ima.ali_choix.domain.ProductId;
import ca.ulaval.ima.ali_choix.domain.exceptions.HistoryEmptyException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class HistoryRepositoryLocalTest {
    ProductId FIRST_PRODUCT_ID = new ProductId("1");
    ProductId SECOND_PRODUCT_ID = new ProductId("2");
    ProductId THIRD_PRODUCT_ID = new ProductId("3");
    ProductId FOURTH_PRODUCT_ID = new ProductId("4");

    HistoryElement FIRST_ELEMENT = new HistoryElement(FIRST_PRODUCT_ID,null,null,null);
    HistoryElement SECOND_ELEMENT = new HistoryElement(SECOND_PRODUCT_ID,null,null,null);
    HistoryElement THIRD_ELEMENT = new HistoryElement(THIRD_PRODUCT_ID,null,null,null);
    HistoryElement FOURTH_ELEMENT = new HistoryElement(FOURTH_PRODUCT_ID,null,null,null);

    HistoryRepository historyRepository;

    //TODO : can't use beforeEach?
    public void setUp() {
        historyRepository = new HistoryRepositoryLocal();
        historyRepository.addElement(FIRST_ELEMENT);
        historyRepository.addElement(SECOND_ELEMENT);
        historyRepository.addElement(THIRD_ELEMENT);
    }

    @Test
    public void givenPresentElement_whenAddingExistingProduct_thenAddedProductIsLast(){
        setUp();
        assertEquals(THIRD_PRODUCT_ID, historyRepository.getLastSearchedProductId());

        historyRepository.addElement(FOURTH_ELEMENT);

        assertEquals(FOURTH_PRODUCT_ID, historyRepository.getLastSearchedProductId());
    }

    @Test
    public void givenPresentElement_whenAddingExistingProduct_thenMoveProductAtTheEnd(){
        setUp();
        assertEquals(THIRD_PRODUCT_ID, historyRepository.getLastSearchedProductId());

        historyRepository.addElement(FIRST_ELEMENT);

        assertEquals(FIRST_PRODUCT_ID, historyRepository.getLastSearchedProductId());
    }

    @Test
    public void givenPresentElement_whenAddingExistingProduct_thenHistorySizeDoesNotChange(){
        setUp();
        int originalSize = historyRepository.getHistory().size();

        historyRepository.addElement(FIRST_ELEMENT);

        int newSize = historyRepository.getHistory().size();
        assertEquals(originalSize, newSize);
    }

    @Test
    public void givenPresentElement_whenRemoving_elementIsRemoved(){
        setUp();

        historyRepository.removeElement(FIRST_PRODUCT_ID);

        assertFalse(historyRepository.getHistory().contains(FIRST_ELEMENT));
    }

    @Test
    public void givenMultipleElement_whenRemovingAll_historyIsEmpty(){
        setUp();

        historyRepository.removeAllElements();

        assertEquals(0, historyRepository.getHistory().size());
    }

    @Test (expected = HistoryEmptyException.class)
    public void givenHistoryIsEmpty_whenGettingLastSearched_throwHistoryEmptyException(){
        setUp();
        historyRepository.removeAllElements();

        historyRepository.getLastSearchedProductId();
    }
}