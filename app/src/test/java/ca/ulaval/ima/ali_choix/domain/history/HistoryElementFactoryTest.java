package ca.ulaval.ima.ali_choix.domain.history;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HistoryElementFactoryTest {
    private static final String PRODUCT_ID = "1";
    private static final String IMAGE_URL = "image url";
    private static final String PRODUCT_NAME = "product name";

    HistoryElementFactory historyElementFactory;

    @Before
    public void setUp() {
        historyElementFactory = new HistoryElementFactory();
    }

    @Test
    public void whenCreating_thenReturnNewHistoryElement() {
        HistoryElement result = historyElementFactory.create(PRODUCT_ID, IMAGE_URL, PRODUCT_NAME);

        assertEquals(result.getProductId().toString(), PRODUCT_ID);
        assertEquals(result.getImage_front_url(), IMAGE_URL);
        assertEquals(result.getProductName(), PRODUCT_NAME);
    }
}