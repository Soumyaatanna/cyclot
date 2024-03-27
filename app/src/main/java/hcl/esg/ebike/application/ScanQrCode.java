package hcl.esg.ebike.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ScanQrCode extends AppCompatActivity {
    Button return_btn;
    ImageView profile;
    DatabaseReference database= FirebaseDatabase.getInstance().getReference().child("cycles");

    Calendar calender;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr_code);

        return_btn = findViewById(R.id.return_btn);
        profile = findViewById(R.id.profile);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("cycleId")) {
            String cycleId = intent.getStringExtra("cycleId");

            return_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    updateCycleData(cycleId);

                }
            });
        }

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanQrCode.this, Employee_profile.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateCycleData(String cycleId) {
        DatabaseReference cycleRef = FirebaseDatabase.getInstance().getReference().child("cycles").child(cycleId);
        cycleRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                    String currentTime = dateFormat.format(calendar.getTime());
                    // Update cycle data in the database
                    cycleRef.child("availability").setValue("Available");
                    cycleRef.child("returned").setValue("yes");
                    cycleRef.child("History").setValue("Returned");
                    cycleRef.child("ReturnedTime").setValue(currentTime);
                    cycleRef.child("damaged").setValue("no");

                    Intent intent = new Intent(ScanQrCode.this, MapActivity.class);
                    intent.putExtra("cycleId", cycleId); // Pass cycle ID as an extra
                    startActivity(intent);


                } else {
                    Toast.makeText(ScanQrCode.this, "Try again", Toast.LENGTH_SHORT).show();
                    // Handle case where cycle data doesn't exist
                    // (Optional: Show a message to the user)
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    }

}