package ca.ulaval.ima.ali_choix.domain.exceptions;

import android.os.Build;

import androidx.annotation.RequiresApi;

import ca.ulaval.ima.ali_choix.domain.ErrorCode;

public class HistoryEmptyException extends ApplicationException {
    private static final String ERROR = "History is empty";
    private static final String DESCRIPTION = "Cannot get searched product because history is empty";
    private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

    public HistoryEmptyException() {
        super(ERROR, DESCRIPTION, CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String getDescription() {
        return String.format(DESCRIPTION);
    }
}
