package ca.ulaval.ima.ali_choix.domain.product;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.ima.ali_choix.domain.exceptions.InvalidNutriScoreGradeException;

public enum NutriScoreGrade {
    A("a", "Très bonne qualité nutritionnelle"),
    B("b", "Bonne qualité nutritionnelle"),
    C("c", "Qualité nutritionnelle moyenne"),
    D("d", "Mauvaise qualité nutritionnelle"),
    E("e", "Très mauvaise qualité nutritionnelle"),
    UNKNOWN("unknown", "Nutri-Score non calculé\nQualité nutritionnelle inconnue");

    private String grade;
    private String description;
    private static final Map<String, NutriScoreGrade> lookup = new HashMap<>();

    static {
        for (NutriScoreGrade nutriScoreGrade : NutriScoreGrade.values()) {
            lookup.put(nutriScoreGrade.toString(), nutriScoreGrade);
        }
    }

    NutriScoreGrade(String grade, String description) {
        this.grade = grade;
        this.description = description;
    }

    ;

    @Override
    public String toString() {
        return grade;
    }

    public static NutriScoreGrade get(String grade) {
        if (grade == null) throw new InvalidNutriScoreGradeException();

        NutriScoreGrade nutriScoreGradeFound = lookup.get(grade.toLowerCase());

        if (nutriScoreGradeFound == null) throw new InvalidNutriScoreGradeException();

        return nutriScoreGradeFound;
    }

    public String getDescription() {
        return description;
    }
}