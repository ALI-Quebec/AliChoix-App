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
    public void whenGettingDescription_thenReturnInvalidNutrientLevelsQuantityExceptionDescription() {
        String expectedDescription = "Nutrient levels quantity is not valid";
        String description = exception.getDescription();

        assertEquals(description, expectedDescription);
    }

    @Test
    public void whenGettingCode_thenReturnInvalidNutrientLevelsQuantityExceptionCode() {
        ErrorCode expectedCode = ErrorCode.INVALID_REQUEST;
        ErrorCode code = exception.getCode();

        assertEquals(code, expectedCode);
    }

    @Test
    public void whenGettingError_thenReturnInvalidNutrientLevelsQuantityExceptionError() {
        String expectedError = "Invalid nutrient levels quantity";
        String error = exception.getError();

        assertEquals(error, expectedError);
    }
}