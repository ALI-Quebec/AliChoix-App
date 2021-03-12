package ca.ulaval.ima.ali_choix.ui.domain;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenFoodFactScannedProduct {
    private String code;

    public String getCode() {
        return code;
    }

    public static OpenFoodFactScannedProduct fromJson(JSONObject jsonObject) {
        OpenFoodFactScannedProduct b = new OpenFoodFactScannedProduct();
        // Deserialize json into object fields
        try {
            b.code = jsonObject.getString("code");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return b;
    }

}
