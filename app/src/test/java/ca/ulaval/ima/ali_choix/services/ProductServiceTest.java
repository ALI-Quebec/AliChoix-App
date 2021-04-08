package ca.ulaval.ima.ali_choix.services;

import static org.junit.Assert.*;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ima.ali_choix.domain.NutriScoreGrade;
import ca.ulaval.ima.ali_choix.domain.exceptions.InvalidNutriScoreGradeException;

public class ProductServiceTest {
    private ProductService productService;

    @Before
    public void setUp() {
        productService = new ProductService() {
            @Nullable
            @Override
            public IBinder onBind(Intent intent) {
                return null;
            }
        };
    }

    @Test
    public void givenNutriScoreGradeA_whenGettingDescription_thenReturnCorrespondingDescription() {
        assertEquals("Très bonne qualité nutritionnelle", productService.getNutriScoreDescription("a"));
    }

    @Test
    public void givenNutriScoreGradeB_whenGettingDescription_thenReturnCorrespondingDescription() {
        assertEquals("Bonne qualité nutritionnelle", productService.getNutriScoreDescription("b"));
    }

    @Test
    public void givenNutriScoreGradeC_whenGettingDescription_thenReturnCorrespondingDescription() {
        assertEquals("Qualité nutritionnelle moyenne", productService.getNutriScoreDescription("c"));
    }

    @Test
    public void givenNutriScoreGradeD_whenGettingDescription_thenReturnCorrespondingDescription() {
        assertEquals("Mauvaise qualité nutritionnelle", productService.getNutriScoreDescription("d"));
    }

    @Test
    public void givenNutriScoreGradeE_whenGettingDescription_thenReturnCorrespondingDescription() {
        assertEquals("Très mauvaise qualité nutritionnelle", productService.getNutriScoreDescription("e"));
    }

    @Test(expected = InvalidNutriScoreGradeException.class)
    public void givenWrongNutriScoreGrade_whenGettingDescription_thenThrow() {
        productService.getNutriScoreDescription("x");
    }
}