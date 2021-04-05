package ca.ulaval.ima.ali_choix.domain.exceptions;

import android.os.Build;

import androidx.annotation.RequiresApi;

import ca.ulaval.ima.ali_choix.domain.ErrorCode;

public class HistoricEmptyException extends ApplicationException {
    private static final String ERROR = "Historic is empty";
    private static final String DESCRIPTION = "Cannot get searched product because historic is empty";
    private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

    public HistoricEmptyException() {
        super(ERROR, DESCRIPTION, CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String getDescription() {
        return String.format(DESCRIPTION);
    }
}
