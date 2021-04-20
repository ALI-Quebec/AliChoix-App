package ca.ulaval.ima.ali_choix.domain.exceptions;

import android.os.Build;

import androidx.annotation.RequiresApi;

import ca.ulaval.ima.ali_choix.domain.exceptions.ApplicationException;
import ca.ulaval.ima.ali_choix.domain.exceptions.ErrorCode;

public class HistoryLoadingException extends ApplicationException {
    private static final String ERROR = "Error while loading history";
    private static final String DESCRIPTION = "There was an error when trying to load history";
    private static final ErrorCode CODE = ErrorCode.APPLICATION_FAILURE;

    public HistoryLoadingException() {
        super(ERROR, DESCRIPTION, CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String getDescription() {
        return String.format(DESCRIPTION);
    }
}
