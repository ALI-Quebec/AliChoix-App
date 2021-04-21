package ca.ulaval.ima.ali_choix.domain.exceptions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HistorySavingExceptionTest {
    private ApplicationException exception;

    @Before
    public void setUp() {
        exception = new HistorySavingException();
    }

    @Test
    public void whenGettingDescription_thenReturnHistorySavingExceptionDescription() {
        String expectedDescription = "There was an error when trying to save history";
        String description = exception.getDescription();

        assertEquals(description, expectedDescription);
    }

    @Test
    public void whenGettingCode_thenReturnHistorySavingExceptionCode() {
        ErrorCode expectedCode = ErrorCode.APPLICATION_FAILURE;
        ErrorCode code = exception.getCode();

        assertEquals(code, expectedCode);
    }

    @Test
    public void whenGettingError_thenReturnHistorySavingExceptionError() {
        String expectedError = "Error while saving history";
        String error = exception.getError();

        assertEquals(error, expectedError);
    }
}