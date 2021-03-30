package ca.ulaval.ima.ali_choix.domain;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.ima.ali_choix.domain.exceptions.InvalidNutriScoreGradeException;

public enum NutriScoreGrade {
    A("a"),
    B("b"),
    C("c"),
    D("d"),
    E("e");

    private String nutriScoreValue;
    private static final Map<String, NutriScoreGrade> lookup = new HashMap<>();

    static {
        for (NutriScoreGrade nutriScoreGrade : NutriScoreGrade.values()) {
            lookup.put(nutriScoreGrade.toString(), nutriScoreGrade);
        }
    }

    NutriScoreGrade(String nutriScoreValue) {
        this.nutriScoreValue = nutriScoreValue;
    };

    @Override
    public String toString() {
        return nutriScoreValue;
    }

    public static NutriScoreGrade get(String nutriScoreValue) {
        if (nutriScoreValue == null) throw new InvalidNutriScoreGradeException();

        NutriScoreGrade nutriScoreGradeFound = lookup.get(nutriScoreValue.toLowerCase());

        return nutriScoreGradeFound;
    }
}