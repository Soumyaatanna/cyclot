package hcl.esg.ebike.application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MapActivity extends AppCompatActivity {

    Button button;


    DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("cycles");
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Fragment fragment = new mapfragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();

        button = findViewById(R.id.scan_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(MapActivity.this);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setPrompt("Scan Qr code");
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.initiateScan();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (intentResult != null) {
            String Contents = intentResult.getContents();
            if (Contents != null) {

                fetchCycleData(Contents); // Fetch data for the scanned cycle ID
            } else {
                Toast.makeText(MapActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void fetchCycleData(String cycleId) {
        DatabaseReference cycleRef = FirebaseDatabase.getInstance().getReference().child("cycles").child(cycleId);
        cycleRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Inside onActivityResult method

                        Intent intent = new Intent(MapActivity.this, ScanQrCode.class);
                        intent.putExtra("cycleId", cycleId); // Pass cycle ID as an extra
                        startActivity(intent);

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
                    String currentTime = dateFormat.format(calendar.getTime());

                    // Cycle exists, update its availability and request status
                    cycleRef.child("availability").setValue("Not Available");
                    cycleRef.child("RequestTime").setValue(currentTime);
                    cycleRef.child("request").setValue("Yes");
                    cycleRef.child("History").setValue("Requested");
                    cycleRef.child("ReturnedTime").removeValue();
                } else {
                    // Cycle doesn't exist, handle accordingly (e.g., show a message)
                    Toast.makeText(MapActivity.this, "Cycle not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
                Toast.makeText(MapActivity.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
