package ca.ulaval.ima.ali_choix.domain.exceptions;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class InvalidNutrientLevelsQuantityException extends ApplicationException {
    private static final String ERROR = "Invalid nutrient levels quantity";
    private static final String DESCRIPTION = "Nutrient levels quantity is not valid";
    private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

    public InvalidNutrientLevelsQuantityException() {
        super(ERROR, DESCRIPTION, CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String getDescription() {
        return String.format(DESCRIPTION);

    }
}
