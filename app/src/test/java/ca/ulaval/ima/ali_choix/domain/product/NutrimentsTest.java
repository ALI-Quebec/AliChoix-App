package ca.ulaval.ima.ali_choix.domain.product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NutrimentsTest {

    private static final Float NUTRIMENTS_AMOUNT = 0.5f;
    private static final Float NUTRIMENTS_AMOUNT_MILLIGRAM = 500f;
    private Nutriments nutriment;

    @Before
    public void setup() {
        nutriment = new Nutriments();
    }

    @Test
    public void givenNutrimentIsNull_whenCalculatingToMilligram_thenReturnNull() {
        Float result = nutriment.calculateToMilligram(null);

        assertEquals(result, null);
    }

    @Test
    public void givenNutriment_whenCalculatingToMilligram_thenReturnMilligramAmount() {
        Float result = nutriment.calculateToMilligram(NUTRIMENTS_AMOUNT);

        assertEquals(result, NUTRIMENTS_AMOUNT_MILLIGRAM);
    }
}