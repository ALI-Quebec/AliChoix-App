package ca.ulaval.ima.ali_choix.services;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import ca.ulaval.ima.ali_choix.domain.DomainConstant;
import ca.ulaval.ima.ali_choix.domain.exceptions.InvalidEcoScoreGradeException;
import ca.ulaval.ima.ali_choix.domain.exceptions.InvalidNutriScoreGradeException;
import ca.ulaval.ima.ali_choix.domain.exceptions.InvalidNutrientLevelsQuantityException;
import ca.ulaval.ima.ali_choix.domain.product.NutrientLevelsQuantity;
import ca.ulaval.ima.ali_choix.domain.product.Nutriments;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest {
    private static final String EXCEPTION_THROWING_GRADE = "this will throws an invalid grade exception";
    private static final String EXCEPTION_THROWING_NUTRIMENT = "this will throws an invalid nutriment exception";
    private ProductService productService;
    private Nutriments nutriments;


    @Before
    public void setUp() {
        productService = new ProductService();
        nutriments = new Nutriments();
    }

    @Test
    public void givenNutriScoreGradeA_whenGettingNutriScoreDescription_thenReturnNutriScoreGradeADescription() {
        assertEquals("Très bonne qualité nutritionnelle", productService.getNutriScoreDescription("a"));
    }

    @Test
    public void givenNutriScoreGradeB_whenGettingNutriScoreDescription_thenReturnNutriScoreGradeBDescription() {
        assertEquals("Bonne qualité nutritionnelle", productService.getNutriScoreDescription("b"));
    }

    @Test
    public void givenNutriScoreGradeC_whenGettingNutriScoreDescription_thenReturnNutriScoreGradeCDescription() {
        assertEquals("Qualité nutritionnelle moyenne", productService.getNutriScoreDescription("c"));
    }

    @Test
    public void givenNutriScoreGradeD_whenGettingNutriScoreDescription_thenReturnNutriScoreGradeDDescription() {
        assertEquals("Mauvaise qualité nutritionnelle", productService.getNutriScoreDescription("d"));
    }

    @Test
    public void givenNutriScoreGradeE_whenGettingNutriScoreDescription_thenReturnNutriScoreGradeEDescription() {
        assertEquals("Très mauvaise qualité nutritionnelle", productService.getNutriScoreDescription("e"));
    }

    @Test
    public void givenNutriScoreGradeUnknown_whenGettingNutriScoreDescription_thenReturnNutriScoreGradeUnknownDescription() {
        assertEquals("Nutri-Score non calculé\nQualité nutritionnelle inconnue", productService.getNutriScoreDescription("unknown"));
    }

    @Test
    public void givenInvalidNutriScoreGradeException_whenGettingNutriScoreDescription_thenReturnNutriScoreGradeUnknownDescription() {
        assertEquals("Nutri-Score non calculé\nQualité nutritionnelle inconnue", productService.getNutriScoreDescription(EXCEPTION_THROWING_GRADE));
    }

    @Test
    public void givenNutrimentsWithLowNutrientLevelsQuantity_whenGettingNutrientLevelsQuantity_thenReturnHashMapWithLowNutrientLevelsQuantity() {
        nutriments.setFat100g(2f);
        nutriments.setSaturatedFat100g(1f);
        nutriments.setSugars100g(4f);
        nutriments.setSalt100g(0.1f);

        HashMap<String, NutrientLevelsQuantity> nutrientLevelsQuantity = new HashMap<>();
        nutrientLevelsQuantity.put(DomainConstant.FAT_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsQuantity.LOW);
        nutrientLevelsQuantity.put(DomainConstant.SATURATED_FAT_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsQuantity.LOW);
        nutrientLevelsQuantity.put(DomainConstant.SUGARS_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsQuantity.LOW);
        nutrientLevelsQuantity.put(DomainConstant.SALT_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsQuantity.LOW);

        assertEquals(nutrientLevelsQuantity, productService.getNutrientLevelsQuantity(nutriments));
    }

    @Test
    public void givenNutrimentsWithModerateNutrientLevelsQuantity_whenGettingNutrientLevelsQuantity_thenReturnHashMapWithModerateNutrientLevelsQuantity() {
        nutriments.setFat100g(19f);
        nutriments.setSaturatedFat100g(4f);
        nutriments.setSugars100g(12.5f);
        nutriments.setSalt100g(1.5f);

        HashMap<String, NutrientLevelsQuantity> nutrientLevelsQuantity = new HashMap<>();
        nutrientLevelsQuantity.put(DomainConstant.FAT_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsQuantity.MODERATE);
        nutrientLevelsQuantity.put(DomainConstant.SATURATED_FAT_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsQuantity.MODERATE);
        nutrientLevelsQuantity.put(DomainConstant.SUGARS_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsQuantity.MODERATE);
        nutrientLevelsQuantity.put(DomainConstant.SALT_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsQuantity.MODERATE);

        assertEquals(nutrientLevelsQuantity, productService.getNutrientLevelsQuantity(nutriments));
    }

    @Test
    public void givenNutrimentsWithHighNutrientLevelsQuantity_whenGettingNutrientLevelsQuantity_thenReturnHashMapWithHighNutrientLevelsQuantity() {
        nutriments.setFat100g(21f);
        nutriments.setSaturatedFat100g(7f);
        nutriments.setSugars100g(22f);
        nutriments.setSalt100g(4f);

        HashMap<String, NutrientLevelsQuantity> nutrientLevelsQuantity = new HashMap<>();
        nutrientLevelsQuantity.put(DomainConstant.FAT_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsQuantity.HIGH);
        nutrientLevelsQuantity.put(DomainConstant.SATURATED_FAT_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsQuantity.HIGH);
        nutrientLevelsQuantity.put(DomainConstant.SUGARS_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsQuantity.HIGH);
        nutrientLevelsQuantity.put(DomainConstant.SALT_NUTRIENT_LEVELS_QUANTITY_KEY, NutrientLevelsQuantity.HIGH);

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
    public void givenHighNutrientLevelsQuantity_whenGettingNutrientLevelsDescription_thenReturnHighNutrientLevelsDescription() {
        assertEquals("Quantité élevée", productService.getNutrientLevelsDescription("high"));
    }

    @Test
    public void givenInvalidNutrientLevelsQuantityException_whenGettingNutrientLevelsDescription_thenReturnUnknownNutrientLevelsDescription() {
        assertEquals("Quantité inconnue", productService.getNutrientLevelsDescription(EXCEPTION_THROWING_NUTRIMENT));
    }

    @Test
    public void givenEcoScoreGradeA_whenGettingEcoScoreDescription_thenReturnEcoScoreGradeADescription() {
        assertEquals("Très faible impact environnemental", productService.getEcoScoreDescription("a"));
    }

    @Test
    public void givenEcoScoreGradeB_whenGettingEcoScoreDescription_thenReturnEcoScoreGradeBDescription() {
        assertEquals("Faible impact environnemental", productService.getEcoScoreDescription("b"));
    }

    @Test
    public void givenEcoScoreGradeC_whenGettingEcoScoreDescription_thenReturnEcoScoreGradeCDescription() {
        assertEquals("Impact modéré sur l'environnement", productService.getEcoScoreDescription("c"));
    }

    @Test
    public void givenEcoScoreGradeD_whenGettingEcoScoreDescription_thenReturnEcoScoreGradeDDescription() {
        assertEquals("Impact environnemental élevé", productService.getEcoScoreDescription("d"));
    }

    @Test
    public void givenEcoScoreGradeE_whenGettingEcoScoreDescription_thenReturnEcoScoreGradeEDescription() {
        assertEquals("Impact environnemental très élevé", productService.getEcoScoreDescription("e"));
    }

    @Test
    public void givenEcoScoreGradeUnknown_whenGettingEcoScoreDescription_thenReturnEcoScoreGradeUnknownDescription() {
        assertEquals("Impact environnemental inconnu", productService.getEcoScoreDescription("unknown"));
    }

    @Test
    public void givenInvalidEcoScoreGradeException_whenGettingEcoScoreDescription_thenReturnEcoScoreGradeUnknownDescription() {
        assertEquals("Impact environnemental inconnu", productService.getEcoScoreDescription(EXCEPTION_THROWING_GRADE));
    }

}