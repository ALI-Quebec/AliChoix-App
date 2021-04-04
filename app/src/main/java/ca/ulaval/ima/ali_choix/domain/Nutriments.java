package ca.ulaval.ima.ali_choix.domain;

import com.google.gson.annotations.SerializedName;

public class Nutriments {
    private Float fat_100g;
    @SerializedName(value = "saturated-fat_100g")
    private Float saturated_fat_100g;
    private Float sugars_100g;
    private Float salt_100g;

    public void setSaturatedFat100g(float saturated_fat_100g) { this.saturated_fat_100g = saturated_fat_100g; }

    public Float getSaturatedFat100g() { return saturated_fat_100g; }

    public void setFat100g(float fat_100g) { this.fat_100g = fat_100g;}

    public Float getFat100g() { return fat_100g;}

    public void setSugars100g(float sugars_100g) { this.sugars_100g = sugars_100g;}

    public Float getSugars100g() { return sugars_100g;}

    public void setSalt100g(float salt_100g) { this.salt_100g = salt_100g;}

    public Float getSalt100g() { return salt_100g; }
}
