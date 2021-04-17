package ca.ulaval.ima.ali_choix.domain.exceptions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvalidNutrientLevelsQuantityExceptionTest {
    private ApplicationException exception;

    @Before
    public void setUp() {
        exception = new InvalidNutrientLevelsQuantityException();
    }

    @Test
    public void whenGettingDescription_thenEnumerateNutrientLevelsQuantityValues() {
        String expectedDescription = "Nutrient levels quantity is not valid";
        String description = exception.getDescription();

        assertEquals(description, expectedDescription);
    }
}