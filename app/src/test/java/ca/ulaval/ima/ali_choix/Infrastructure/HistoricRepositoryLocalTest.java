package ca.ulaval.ima.ali_choix.Infrastructure;

import org.junit.Test;

import ca.ulaval.ima.ali_choix.domain.HistoricElement;
import ca.ulaval.ima.ali_choix.domain.HistoricRepository;
import ca.ulaval.ima.ali_choix.domain.ProductId;
import ca.ulaval.ima.ali_choix.domain.exceptions.HistoricEmptyException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class HistoricRepositoryLocalTest {
    ProductId FIRST_PRODUCT_ID = new ProductId("1");
    ProductId SECOND_PRODUCT_ID = new ProductId("2");
    ProductId THIRD_PRODUCT_ID = new ProductId("3");
    ProductId FOURTH_PRODUCT_ID = new ProductId("4");

    HistoricElement FIRST_ELEMENT = new HistoricElement(FIRST_PRODUCT_ID,null,null,null);
    HistoricElement SECOND_ELEMENT = new HistoricElement(SECOND_PRODUCT_ID,null,null,null);
    HistoricElement THIRD_ELEMENT = new HistoricElement(THIRD_PRODUCT_ID,null,null,null);
    HistoricElement FOURTH_ELEMENT = new HistoricElement(FOURTH_PRODUCT_ID,null,null,null);

    HistoricRepository historicRepository;

    //TODO : can't use beforeEach?
    public void setUp() {
        historicRepository = new HistoricRepositoryLocal();
        historicRepository.addElement(FIRST_ELEMENT);
        historicRepository.addElement(SECOND_ELEMENT);
        historicRepository.addElement(THIRD_ELEMENT);
    }

    @Test
    public void givenElementPresent_whenAddingExistingProduct_addedProductIsLast(){
        setUp();
        assertEquals(THIRD_PRODUCT_ID, historicRepository.getLastSearchedProductId());

        historicRepository.addElement(FOURTH_ELEMENT);

        assertEquals(FOURTH_PRODUCT_ID, historicRepository.getLastSearchedProductId());
    }

    @Test
    public void givenElementPresent_whenAddingExistingProduct_moveProductAtTheEnd(){
        setUp();
        assertEquals(THIRD_PRODUCT_ID, historicRepository.getLastSearchedProductId());

        historicRepository.addElement(FIRST_ELEMENT);

        assertEquals(FIRST_PRODUCT_ID, historicRepository.getLastSearchedProductId());
    }

    @Test
    public void givenElementPresent_whenAddingExistingProduct_historicSizeDoesNotChange(){
        setUp();
        int originalSize = historicRepository.getHistoric().size();

        historicRepository.addElement(FIRST_ELEMENT);

        int newSize = historicRepository.getHistoric().size();
        assertEquals(originalSize, newSize);
    }

    @Test
    public void givenElementPresent_whenRemoving_elementIsRemoved(){
        setUp();

        historicRepository.removeElement(FIRST_PRODUCT_ID);

        assertFalse(historicRepository.getHistoric().contains(FIRST_ELEMENT));
    }

    @Test
    public void givenMultipleElement_whenRemovingAll_historicIsEmpty(){
        setUp();

        historicRepository.removeAllElement();

        assertEquals(0,historicRepository.getHistoric().size());
    }

    @Test (expected = HistoricEmptyException.class)
    public void givenHistoricIsEmpty_whenGettingLastSearched_throwHistoricEmptyException(){
        setUp();
        historicRepository.removeAllElement();

        historicRepository.getLastSearchedProductId();
    }
}