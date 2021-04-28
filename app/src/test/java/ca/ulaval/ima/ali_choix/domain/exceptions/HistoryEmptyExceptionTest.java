package ca.ulaval.ima.ali_choix.domain.exceptions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HistoryEmptyExceptionTest {
    private ApplicationException exception;

    @Before
    public void setUp() {
        exception = new HistoryEmptyException();
    }

    @Test
    public void whenGettingDescription_thenReturnHistoryEmptyExceptionDescription() {
        String expectedDescription = "Cannot get searched product because history is empty";
        String description = exception.getDescription();

        assertEquals(description, expectedDescription);
    }

    @Test
    public void whenGettingCode_thenReturnHistoryEmptyExceptionCode() {
        ErrorCode expectedCode = ErrorCode.INVALID_REQUEST;
        ErrorCode code = exception.getCode();

        assertEquals(code, expectedCode);
    }

    @Test
    public void whenGettingError_thenReturnHistoryEmptyExceptionError() {
        String expectedError = "History is empty";
        String error = exception.getError();

        assertEquals(error, expectedError);
    }
}