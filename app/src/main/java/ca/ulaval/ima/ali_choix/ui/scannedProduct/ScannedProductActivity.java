package ca.ulaval.ima.ali_choix.ui.scannedproduct;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import ca.ulaval.ima.ali_choix.R;

public class ScannedProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String productId = intent.getExtras().getString("productId", "");

        if (productId == "") {

        }

        else {
            setContentView(R.layout.fragment_scanned_product);
            Bundle bundle = new Bundle();
            bundle.putString("productId", productId);
            ScannedProductFragment scannedProductFragment = new ScannedProductFragment();;
            scannedProductFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_container_linear, scannedProductFragment);
            transaction.commit();
        }
    }
}
