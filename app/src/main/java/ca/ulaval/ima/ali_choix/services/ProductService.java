package ca.ulaval.ima.ali_choix.services;

import android.app.Service;

import ca.ulaval.ima.ali_choix.domain.NutriScoreGrade;

public abstract class ProductService extends Service {

    public ProductService(){}

    public String getNutriScoreDescription(String grade) {
        NutriScoreGrade nutriScoreGrade = NutriScoreGrade.get(grade);
        return nutriScoreGrade.getDescription();
    }
}
