package hcl.esg.ebike.application;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCyclesFragment extends Fragment {

    private EditText edit_cid, edit_color, edit_location, edit_availability;

    private DatabaseReference mDatabase;

    public AddCyclesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_cycles, container, false);

        edit_cid = view.findViewById(R.id.edit_cid);
        edit_color = view.findViewById(R.id.color);
        edit_availability = view.findViewById(R.id.edit_availability);
        edit_location = view.findViewById(R.id.edit_location);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        return view;
    }

    public void sendData() {
        writeNewUser();
        Toast.makeText(getActivity(), "Data Inserted in database", Toast.LENGTH_SHORT).show();
    }

    public void writeNewUser() {
        Cycles cycles = new Cycles(edit_cid.getText().toString(),
                edit_availability.getText().toString(),
                edit_color.getText().toString(),
                edit_location.getText().toString());

        mDatabase.child("cycles").child(cycles.getCid()).setValue(cycles);
    }
}
