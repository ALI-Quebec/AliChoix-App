package ca.ulaval.ima.ali_choix.ui.scannedproduct;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ca.ulaval.ima.ali_choix.R;

public class ScannedProductFragment extends Fragment {

    private ScannedProductViewModel scannedProductViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scannedProductViewModel =
                new ViewModelProvider(this).get(ScannedProductViewModel.class);
        View root = inflater.inflate(R.layout.fragment_scanned_product, container, false);
        final TextView scannedProductDescription = root.findViewById(R.id.scanned_product_description);
        scannedProductViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                scannedProductDescription.setText(s);
            }
        });
        return root;
    }
}