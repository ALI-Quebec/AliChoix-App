package ca.ulaval.ima.ali_choix.domain.product;

import com.google.gson.annotations.SerializedName;

public class Nutriments {
    private Float fat_100g;
    @SerializedName(value = "saturated-fat_100g")
    private Float saturated_fat_100g;
    private Float sugars_100g;
    private Float salt_100g;
    @SerializedName(value = "energy-kcal_100g")
    private Float energy_kcal_100g;
    @SerializedName(value = "energy-kj_value")
    private Float energy_kj_value;
    private Float carbohydrates_100g;
    private Float fiber_100g;
    private Float proteins_100g;
    private Float sodium_100g;
    private Float alcohol_100g;
    private Float iron_100g;
    private Float magnesium_100g;

    public void setSaturatedFat100g(Float saturated_fat_100g) {
        this.saturated_fat_100g = saturated_fat_100g;
    }

    public Float getSaturatedFat100g() {
        return saturated_fat_100g;
    }

    public void setFat100g(Float fat_100g) {
        this.fat_100g = fat_100g;
    }

    public Float getFat100g() {
        return fat_100g;
    }

    public void setSugars100g(Float sugars_100g) {
        this.sugars_100g = sugars_100g;
    }

    public Float getSugars100g() {
        return sugars_100g;
    }

    public void setSalt100g(Float salt_100g) {
        this.salt_100g = salt_100g;
    }

    public Float getSalt100g() {
        return salt_100g;
    }

    public void setEnergyKcal100g(Float energy_kcal_100g) {
        this.energy_kcal_100g = energy_kcal_100g;
    }

    public Float getEnergyKcal100g() {
        return energy_kcal_100g;
    }

    public void setEnergyKj100g(Float energy_kj_value) {
        this.energy_kj_value = energy_kj_value;
    }

    public Float getEnergyKj100g() {
        return energy_kj_value;
    }

    public void setCarbohydrates100g(Float carbohydrates_100g) {
        this.carbohydrates_100g = carbohydrates_100g;
    }

    public Float getCarbohydrates100g() {
        return carbohydrates_100g;
    }

    public void setFibers100g(Float fiber_100g) {
        this.fiber_100g = fiber_100g;
    }

    public Float getFibers100g() {
        return fiber_100g;
    }

    public void setProteins100g(Float proteins_100g) {
        this.proteins_100g = proteins_100g;
    }

    public Float getProteins100g() {
        return proteins_100g;
    }

    public void setSodium100g(Float sodium_100g) {
        this.sodium_100g = sodium_100g;
    }

    public Float getSodium100g() {
        return sodium_100g;
    }

    public void setAlcohol100g(Float alcohol_100g) {
        this.alcohol_100g = alcohol_100g;
    }

    public Float getAlcohol100g() {
        return alcohol_100g;
    }

    public void setIron100g(Float iron_100g) {
        this.iron_100g = iron_100g;
    }

    public Float getIron100g() {
        return iron_100g;
    }

    public void setMagnesium100g(Float magnesium_100g) {
        this.magnesium_100g = magnesium_100g;
    }

    public Float getMagnesium100g() {
        return magnesium_100g;
    }

    public Float calculateToMilligram(Float nutriment) {
        if (nutriment == null) return null;

        return nutriment * 1000;
    }
}
