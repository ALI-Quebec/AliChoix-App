package ca.ulaval.ima.ali_choix.domain.exceptions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvalidNutriScoreGradeExceptionTest {

    private ApplicationException exception;

    @Before
    public void setUp() {
        exception = new InvalidNutriScoreGradeException();
    }

    @Test
    public void whenGettingDescription_thenReturnInvalidNutriScoreGradeExceptionDescription() {
        String expectedDescription = "Nutri score grade is not valid";
        String description = exception.getDescription();

        assertEquals(description, expectedDescription);
    }

    @Test
    public void whenGettingCode_thenReturnInvalidNutriScoreGradeExceptionCode() {
        ErrorCode expectedCode = ErrorCode.INVALID_REQUEST;
        ErrorCode code = exception.getCode();

        assertEquals(code, expectedCode);
    }

    @Test
    public void whenGettingError_thenReturnInvalidNutriScoreGradeExceptionError() {
        String expectedError = "Invalid nutri score grade";
        String error = exception.getError();

        assertEquals(error, expectedError);
    }
}