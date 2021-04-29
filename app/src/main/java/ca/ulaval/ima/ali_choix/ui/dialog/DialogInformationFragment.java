package ca.ulaval.ima.ali_choix.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import ca.ulaval.ima.ali_choix.R;

import static ca.ulaval.ima.ali_choix.ui.UIConstant.DIALOG_MESSAGE_KEY;

public class DialogInformationFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        String message = getArguments().getString(DIALOG_MESSAGE_KEY, "");
        View dialogView = inflater.inflate(R.layout.information_pop_up, null);
        builder.setView(dialogView).setPositiveButton("ok", (dialog, id) -> dialog.dismiss());

        TextView textView = (TextView) dialogView.findViewById(R.id.information_pop_up_text_view);
        textView.setText(message);

        return builder.create();
    }

}