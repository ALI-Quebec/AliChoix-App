package ca.ulaval.ima.ali_choix.services;

import static org.junit.Assert.*;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import ca.ulaval.ima.ali_choix.domain.DomainConstant;
import ca.ulaval.ima.ali_choix.domain.product.NutrientLevelsQuantity;
import ca.ulaval.ima.ali_choix.domain.product.Nutriments;
import ca.ulaval.ima.ali_choix.domain.exceptions.InvalidNutriScoreGradeException;
import ca.ulaval.ima.ali_choix.domain.exceptions.InvalidNutrientLevelsQuantityException;

public class ProductServiceTest {
    private ProductService productService;
    private Nutriments nutriments;


    @Before
    public void setUp() {
        productService = new ProductService() {
            @Nullable
            @Override
            public IBinder onBind(Intent intent) {
                return null;
            }
        };
        nutriments = new Nutriments();
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
    public void givenWrongNutriScoreGrade_whenGettingNutriScoreDescription_thenThrowInvalidNutriScoreGradeException() {
        productService.getNutriScoreDescription("x");
    }

    @Test
    public void givenNutrimentsWithLowNutrientLevelsQuantity_whenGettingNutrientLevelsQuantity_thenReturnHashMapWithLowNutrientLevelsQuantity() {
        nutriments.setFat100g(2f);
        nutriments.setSaturatedFat100g(1f);
        nutriments.setSugars100g(4f);
        nutriments.setSalt100g(0.1f);

        HashMap<String, NutrientLevelsQuantity> nutrientLevelsQuantity = new HashMap<>();
        nutrientLevelsQuantity.put(DomainConstant.FAT_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsQuantity.LOW);
        nutrientLevelsQuantity.put(DomainConstant.SATURATED_FAT_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsQuantity.LOW);
        nutrientLevelsQuantity.put(DomainConstant.SUGARS_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsQuantity.LOW);
        nutrientLevelsQuantity.put(DomainConstant.SALT_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsQuantity.LOW);

        assertEquals(nutrientLevelsQuantity, productService.getNutrientLevelsQuantity(nutriments));
    }

    @Test
    public void givenNutrimentsWithModerateNutrientLevelsQuantity_whenGettingNutrientLevelsQuantity_thenReturnHashMapWithModerateNutrientLevelsQuantity() {
        nutriments.setFat100g(19f);
        nutriments.setSaturatedFat100g(4f);
        nutriments.setSugars100g(12.5f);
        nutriments.setSalt100g(1.5f);

        HashMap<String, NutrientLevelsQuantity> nutrientLevelsQuantity = new HashMap<>();
        nutrientLevelsQuantity.put(DomainConstant.FAT_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsQuantity.MODERATE);
        nutrientLevelsQuantity.put(DomainConstant.SATURATED_FAT_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsQuantity.MODERATE);
        nutrientLevelsQuantity.put(DomainConstant.SUGARS_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsQuantity.MODERATE);
        nutrientLevelsQuantity.put(DomainConstant.SALT_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsQuantity.MODERATE);

        assertEquals(nutrientLevelsQuantity, productService.getNutrientLevelsQuantity(nutriments));
    }

    @Test
    public void givenNutrimentsWithHighNutrientLevelsQuantity_whenGettingNutrientLevelsQuantity_thenReturnHashMapWithModerateNutrientLevelsQuantity() {
        nutriments.setFat100g(21f);
        nutriments.setSaturatedFat100g(7f);
        nutriments.setSugars100g(22f);
        nutriments.setSalt100g(4f);

        HashMap<String, NutrientLevelsQuantity> nutrientLevelsQuantity = new HashMap<>();
        nutrientLevelsQuantity.put(DomainConstant.FAT_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsQuantity.HIGH);
        nutrientLevelsQuantity.put(DomainConstant.SATURATED_FAT_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsQuantity.HIGH);
        nutrientLevelsQuantity.put(DomainConstant.SUGARS_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsQuantity.HIGH);
        nutrientLevelsQuantity.put(DomainConstant.SALT_NUTRIENT_LEVELS_QUANTITY, NutrientLevelsQuantity.HIGH);

        assertEquals(nutrientLevelsQuantity, productService.getNutrientLevelsQuantity(nutriments));
    }

    @Test
    public void givenLowNutrientLevelsQuantity_whenGettingNutrientLevelsDescription_thenReturnLowNutrientLevelsDescription() {
        assertEquals("Quantité faible", productService.getNutrientLevelsDescription("low"));
    }

    @Test
    public void givenModerateNutrientLevelsQuantity_whenGettingNutrientLevelsDescription_thenReturnModerateNutrientLevelsDescription() {
        assertEquals("Quantité modérée", productService.getNutrientLevelsDescription("moderate"));
    }

    @Test
    public void givenHigheNutrientLevelsQuantity_whenGettingNutrientLevelsDescription_thenReturnHighNutrientLevelsDescription() {
        assertEquals("Quantité élevée", productService.getNutrientLevelsDescription("high"));
    }

    @Test(expected = InvalidNutrientLevelsQuantityException.class)
    public void givenWrongNutrientLevelsQuantity_whenGettingNutrientLevelsDescription_thenThrowInvalidNutrientLevelsQuantityException() {
        productService.getNutrientLevelsDescription("x");
    }

}