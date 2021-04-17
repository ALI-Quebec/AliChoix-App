package ca.ulaval.ima.ali_choix.domain.exceptions;

import android.os.Build;

import androidx.annotation.RequiresApi;

import ca.ulaval.ima.ali_choix.domain.product.EcoScoreGrade;

public class InvalidEcoScoreGradeException extends ApplicationException {
    private static final String ERROR = "Invalid eco score grade";
    private static final String DESCRIPTION = "Ecore score grade is not valid";
    private static final ErrorCode CODE = ErrorCode.INVALID_REQUEST;

    public InvalidEcoScoreGradeException() {
        super(ERROR, DESCRIPTION, CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String getDescription() {
        return String.format(DESCRIPTION, enumerateValues(EcoScoreGrade.class));
    }
}
