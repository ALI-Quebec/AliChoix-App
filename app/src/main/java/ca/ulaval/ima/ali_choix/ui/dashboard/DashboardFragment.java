package ca.ulaval.ima.ali_choix.ui.dashboard;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import ca.ulaval.ima.ali_choix.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    private SurfaceView cameraRollPreview;
    private static final int MY_CAMERA_PERMISSION_CODE = 007;
    private Camera camera;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        this.cameraRollPreview = (SurfaceView)root.findViewById(R.id.cameraPreview);

        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                openCamera();
                setCamera();
            }
        }
    }

    private boolean openCamera() {
        try {
            camera = Camera.open();
            return camera != null;
        } catch (Exception e) {
            return false;
        }
    }

    private void setCamera() {
        if (camera != null) {
            try {
                camera.setPreviewDisplay(cameraRollPreview.getHolder());
            } catch (Exception e) {
                e.printStackTrace();
            }

            camera.startPreview();
        }
    }

}