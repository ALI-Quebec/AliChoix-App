package ca.ulaval.ima.ali_choix.domain.exceptions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HistoryLoadingExceptionTest {
    private ApplicationException exception;

    @Before
    public void setUp() {
        exception = new HistoryLoadingException();
    }

    @Test
    public void whenGettingDescription_thenReturnHistoryLoadingExceptionDescription() {
        String expectedDescription = "There was an error when trying to load history";
        String description = exception.getDescription();

        assertEquals(description, expectedDescription);
    }

    @Test
    public void whenGettingCode_thenReturnHistoryLoadingExceptionCode() {
        ErrorCode expectedCode = ErrorCode.APPLICATION_FAILURE;
        ErrorCode code = exception.getCode();

        assertEquals(code, expectedCode);
    }

    @Test
    public void whenGettingError_thenReturnHistoryLoadingExceptionError() {
        String expectedError = "Error while loading history";
        String error = exception.getError();

        assertEquals(error, expectedError);
    }
}