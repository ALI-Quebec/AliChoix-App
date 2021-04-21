package ca.ulaval.ima.ali_choix.domain.exceptions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InvalidEcoScoreGradeExceptionTest {
    private ApplicationException exception;

    @Before
    public void setUp() {
        exception = new InvalidEcoScoreGradeException();
    }

    @Test
    public void whenGettingDescription_thenReturnInvalidEcoScoreGradeExceptionDescription() {
        String expectedDescription = "Eco score grade is not valid";
        String description = exception.getDescription();

        assertEquals(description, expectedDescription);
    }

    @Test
    public void whenGettingCode_thenReturnInvalidEcoScoreGradeExceptionCode() {
        ErrorCode expectedCode = ErrorCode.INVALID_REQUEST;
        ErrorCode code = exception.getCode();

        assertEquals(code, expectedCode);
    }

    @Test
    public void whenGettingError_thenReturnInvalidEcoScoreGradeExceptionError() {
        String expectedError = "Invalid eco score grade";
        String error = exception.getError();

        assertEquals(error, expectedError);
    }
}