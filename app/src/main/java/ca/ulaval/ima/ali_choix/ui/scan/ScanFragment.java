package ca.ulaval.ima.ali_choix.ui.scan;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import ca.ulaval.ima.ali_choix.R;

import static ca.ulaval.ima.ali_choix.ui.UIConstant.IS_FROM_SCAN_KEY;
import static ca.ulaval.ima.ali_choix.ui.UIConstant.PRODUCT_ID_KEY;

public class ScanFragment extends Fragment {
    private static final int CAMERA_PERMISSION_CODE = 5050;
    private CodeScanner codeScanner;
    private CodeScannerView scannerView;
    private View root;
    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        activity = getActivity();
        root = inflater.inflate(R.layout.fragment_scan, container, false);

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            initiateScanner();
        }

        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initiateScanner();
            }
        }
    }

    private void initiateScanner() {
        activity = getActivity();
        scannerView = root.findViewById(R.id.scanner_view);
        codeScanner = new CodeScanner(activity, scannerView);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                        Bundle bundle = new Bundle();
                        bundle.putString(PRODUCT_ID_KEY, result.getText());
                        bundle.putBoolean(IS_FROM_SCAN_KEY, true);
                        navController.navigate(R.id.action_navigation_scan_to_navigation_scanned_product, bundle);
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (codeScanner != null) {
            codeScanner.startPreview();
        }
    }

    @Override
    public void onPause() {
        if (codeScanner != null) {
            codeScanner.releaseResources();
        }
        super.onPause();
    }
}