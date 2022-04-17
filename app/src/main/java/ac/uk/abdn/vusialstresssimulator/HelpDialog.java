package ac.uk.abdn.vusialstresssimulator;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

public class HelpDialog extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Help")
                .setMessage("1. Choose text input option. " +
                        "\n\n2. To be able to run a simulation choose desired effect and then press the start button. Adding an overlay is optional." +
                        "\n\n3. If you have chosen the 'Add Text' option, to be able to add new text first make sure you disable all effects by choosing 'None' from the list with Effect options.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return dialogBuilder.create();
    }
}
