package ca.ulaval.ima.ali_choix.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;

import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import ca.ulaval.ima.ali_choix.R;

public class FireDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.missing_product_pop_up, null))
                // Add action buttons
                .setPositiveButton("Retour au scan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                        Bundle bundle = new Bundle();
                        navController.navigate(R.id.action_navigation_scanned_product_to_navigation_scan, bundle);
                    }
                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        //what to do
//                    }
//                })
        ;
        return builder.create();
    }
}