package hcl.esg.ebike.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCycles extends AppCompatActivity {
    private EditText edit_cid,edit_color,edit_location,edit_availability;

    private DatabaseReference mDatabase;
// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cycles);

        edit_cid = findViewById(R.id.edit_cid);
        edit_color = findViewById(R.id.color);
        edit_availability = findViewById(R.id.edit_availability);
        edit_location = findViewById(R.id.edit_location);

        mDatabase = FirebaseDatabase.getInstance().getReference();

    }
    public void sendData(View view) {
        writeNewUser();
        Toast.makeText(this, "Data Inserted in database", Toast.LENGTH_SHORT).show();
    }

    public void writeNewUser() {
        Cycles cycles = new Cycles(edit_cid.getText().toString(),
                edit_availability.getText().toString(),
                edit_color.getText().toString(),
                edit_location.getText().toString());

        mDatabase.child("cycles").child(cycles.getCid()).setValue(cycles);
    }
}