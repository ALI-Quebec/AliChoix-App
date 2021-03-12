package ca.ulaval.ima.ali_choix.ui.domain;

import org.json.JSONException;
import org.json.JSONObject;

public class Product {
    private String imageThumbUrl;
    private String origin;
    private String countriesImported;
    private String productQuantity;

    public String getImage() {
        return imageThumbUrl;
    }

    public String getOrigin() { return origin; }

    public String getCountriesImported() { return countriesImported; }

    public String getProductQuantity() { return productQuantity; }

    public static Product fromJson(JSONObject jsonObject) {
        Product b = new Product();
        // Deserialize json into object fields
        try {
            b.imageThumbUrl = jsonObject.getString("image_thumb_url");
            b.origin = jsonObject.getString("origin");
            b.countriesImported = jsonObject.getString("countries_imported");
            b.productQuantity = jsonObject.getString("product_quantity");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return b;
    }
}
