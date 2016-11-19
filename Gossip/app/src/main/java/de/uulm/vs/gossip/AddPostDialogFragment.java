package de.uulm.vs.gossip;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Robin Kraft on 16.11.16.
 */

public class AddPostDialogFragment extends android.app.DialogFragment {

    /**
     * The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it.
     */
    public interface AddPostDialogListener {
        public void addPost(String newPost);
    }

    // Use this instance of the interface to deliver action events
    AddPostDialogListener listener;

    public void setListener(AddPostDialogListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog
        // layout
        final View view = inflater.inflate(R.layout.dialog_add_post,
                null);
        builder.setView(view);

        final EditText addPostEditText = (EditText) view.findViewById(R.id.addPostEditText);

        builder.setMessage(R.string.add_post_dialog_title)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {

                                String newPost = addPostEditText.getText().toString();

                                if (!newPost.isEmpty()) {
                                    listener.addPost(newPost);
                                }
                            }
                        }).setNegativeButton(R.string.cancel, null);

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
