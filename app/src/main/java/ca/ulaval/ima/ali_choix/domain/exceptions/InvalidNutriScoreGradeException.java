package ca.ulaval.ima.ali_choix.domain.exceptions;

import android.os.Build;

import androidx.annotation.RequiresApi;

import ca.ulaval.ima.ali_choix.domain.product.NutriScoreGrade;

public class InvalidNutriScoreGradeException extends ApplicationException {
    private static final String ERROR = "Invalid nutri score grade";
    private static final String DESCRIPTION = "Nutri score grade is not valid";
    private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

    public InvalidNutriScoreGradeException() {
        super(ERROR, DESCRIPTION, CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String getDescription() {
        return String.format(DESCRIPTION);
    }
}
