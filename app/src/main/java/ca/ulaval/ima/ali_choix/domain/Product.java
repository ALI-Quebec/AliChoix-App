package ca.ulaval.ima.ali_choix.domain;

public class Product {
    private String image_front_url;
    private String origins;
    private String countries_imported;
    private String product_quantity;
    private String product_name_en;

    public String getImage() {
        return image_front_url;
    }

    public void setImage(String image_front_url) {
        this.image_front_url = image_front_url;
    }

    public String getOrigin() {
        return origins;
    }

    public void setOrigins(String origins) {
        this.origins = origins;
    }

    public String getCountryImported() {
        return countries_imported;
    }

    public void setCountriesImported() {
        this.countries_imported = countries_imported;
    }

    public String getQuantity() {
        return product_quantity;
    }

    public void setProductQuantity() {
        this.product_quantity = product_quantity;
    }

    public String getName() {
        return product_name_en;
    }

    public void setName() {
        this.product_name_en = product_name_en;
    }
}