package ca.ulaval.ima.ali_choix.domain.product;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.ima.ali_choix.domain.exceptions.InvalidEcoScoreGradeException;

public enum EcoScoreGrade {
    A("a", "Très faible impact environnemental"),
    B("b", "Faible impact environnemental"),
    C("c", "Impact modéré sur l'environnement"),
    D("d", "Impact environnemental élevé"),
    E("e", "Impact environnemental très élevé"),
    UNKNOWN("unknown", "Impact environnemental inconnu");

    private String grade;
    private String description;
    private static final Map<String, EcoScoreGrade> lookup = new HashMap<>();

    static {
        for (EcoScoreGrade ecoScoreGrade : EcoScoreGrade.values()) {
            lookup.put(ecoScoreGrade.toString(), ecoScoreGrade);
        }
    }

    EcoScoreGrade(String grade, String description) {
        this.grade = grade;
        this.description = description;
    };

    @Override
    public String toString() {
        return grade;
    }

    public static EcoScoreGrade get(String grade) {
        if (grade == null) throw new InvalidEcoScoreGradeException();

        EcoScoreGrade ecoScoreGrade = lookup.get(grade.toLowerCase());

        if (ecoScoreGrade == null) throw new InvalidEcoScoreGradeException();

        return ecoScoreGrade;
    }

    public String getDescription() { return description; }
}
