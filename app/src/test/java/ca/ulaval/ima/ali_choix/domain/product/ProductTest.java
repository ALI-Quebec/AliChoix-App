package ca.ulaval.ima.ali_choix.domain.product;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ima.ali_choix.domain.DomainConstant;

import static org.junit.Assert.assertEquals;

public class ProductTest{
    private Product product;

    @Before
    public void setUp() {
        product = new Product();

    }

    @Test
    public void givenNullOrigins_whenGettingOrigins_thenReturnUnknownConstant() {
        assertEquals(product.getOrigins(), DomainConstant.UNKNOWN);
    }

    @Test
    public void givenOrigins_whenGettingOrigins_thenReturnOrigins() {
        product.setOrigins("Canada");

        assertEquals(product.getOrigins(), "Canada");
    }

    @Test
    public void givenNullCountriesImported_whenGettingCountriesImported_thenReturnUnknownConstant() {
        assertEquals(product.getCountriesImported(), DomainConstant.UNKNOWN);
    }

    @Test
    public void givenCountriesImported_whenGettingCountriesImported_thenReturnCountriesImported() {
        product.setCountriesImported("United States");

        assertEquals(product.getCountriesImported(), "United States");
    }

    @Test
    public void givenNullQuantity_whenGettingQuantity_thenReturnUnknownConstant() {
        assertEquals(product.getQuantity(), DomainConstant.UNKNOWN);
    }

    @Test
    public void givenQuantity_whenGettingQuantity_thenReturnQuantity() {
        product.setQuantity("400");

        assertEquals(product.getQuantity(), "400");
    }

    @Test
    public void givenNullEnglishAndFrenchName_whenGettingName_thenReturnUnknownConstant() {
        assertEquals(product.getName(), DomainConstant.UNKNOWN);
    }

    @Test
    public void givenNullEnglishNameAndFrenchName_whenGettingName_thenReturnFrenchName() {
        product.setFrenchName("French name");

        assertEquals(product.getName(), "French name");
    }

    @Test
    public void givenNullFrenchNameAndEnglishName_whenGettingName_thenReturnEnglishName() {
        product.setEnglishName("English name");

        assertEquals(product.getName(), "English name");
    }

    @Test
    public void givenNullNutriScoreGrade_whenGettingNutriScoreGrade_thenReturnUnknownNutriScoreGrade() {
        assertEquals(product.getNutriScoreGrade(), NutriScoreGrade.UNKNOWN.toString());
    }

    @Test
    public void givenNutriScoreGrade_whenGettingNutriScoreGrade_thenReturnNutriScoreGrade() {
        product.setNutriScoreGrade("a");

        assertEquals(product.getNutriScoreGrade(), "a");
    }
}