package ca.ulaval.ima.ali_choix.network.exceptions;

import android.os.Build;

import androidx.annotation.RequiresApi;

import ca.ulaval.ima.ali_choix.domain.exceptions.ErrorCode;
import ca.ulaval.ima.ali_choix.domain.exceptions.ApplicationException;

public class ProductNotFoundException extends ApplicationException {
    private static final String ERROR = "Cannot get searched product";
    private static final String DESCRIPTION = "Cannot get searched product because it isn't in the database";
    private static final ErrorCode CODE = ErrorCode.NOT_FOUND;

    public ProductNotFoundException() {
        super(ERROR, DESCRIPTION, CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String getDescription() {
        return String.format(DESCRIPTION);
    }
}
