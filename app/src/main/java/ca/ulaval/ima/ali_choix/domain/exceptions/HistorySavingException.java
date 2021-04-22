package ca.ulaval.ima.ali_choix.domain.exceptions;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class HistorySavingException extends ApplicationException {
    private static final String ERROR = "Error while saving history";
    private static final String DESCRIPTION = "There was an error when trying to save history";
    private static final ErrorCode CODE = ErrorCode.APPLICATION_FAILURE;

    public HistorySavingException() {
        super(ERROR, DESCRIPTION, CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String getDescription() {
        return String.format(DESCRIPTION);
    }
}
