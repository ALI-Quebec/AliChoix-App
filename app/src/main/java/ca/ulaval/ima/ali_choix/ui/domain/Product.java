package ca.ulaval.ima.ali_choix.ui.domain;

import org.json.JSONException;
import org.json.JSONObject;

public class Product {
    private String image_thumb_url;

    public String getImage() {
        return image_thumb_url;
    }

    public static Product fromJson(JSONObject jsonObject) {
        Product b = new Product();
        // Deserialize json into object fields
        try {
            b.image_thumb_url = jsonObject.getString("image_thumb_url");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return b;
    }
}
