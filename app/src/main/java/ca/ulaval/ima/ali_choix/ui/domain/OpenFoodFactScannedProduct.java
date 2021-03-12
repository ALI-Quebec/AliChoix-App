package ca.ulaval.ima.ali_choix.ui.domain;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenFoodFactScannedProduct {
    private String code;
    private Product product;

    public String getCode() {
        return code;
    }

    public Product getProduct() { return product; }

    public static OpenFoodFactScannedProduct fromJson(JSONObject jsonObject) {
        OpenFoodFactScannedProduct b = new OpenFoodFactScannedProduct();
        // Deserialize json into object fields
        try {
            b.code = jsonObject.getString("code");
            b.product = Product.fromJson(jsonObject.getJSONObject("product"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return b;
    }

}
