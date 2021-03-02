package ca.ulaval.ima.ali_choix.ui.domain;

public class Product {
    private String image_thumb_url;

    public Product(String image_thumb_url) {
        this.image_thumb_url = image_thumb_url;
    }

    public String getImage() {
        return image_thumb_url;
    }
}
