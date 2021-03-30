package ca.ulaval.ima.ali_choix.services;

import android.app.Service;
import android.content.Context;

public abstract class ProductService extends Service {
    private Context context;

    public ProductService(Context context){
        this.context = context;
    }
}
