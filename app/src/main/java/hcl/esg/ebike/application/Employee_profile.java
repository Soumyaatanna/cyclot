package hcl.esg.ebike.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Employee_profile extends AppCompatActivity {
    private TextView textDisplayName;
    private TextView textEmail;
    private TextView textUid;
    Button LogOut;

    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);



            // Initialize TextViews and Button
            textDisplayName = findViewById(R.id.text_display_name);
            textEmail = findViewById(R.id.text_email);
            textUid = findViewById(R.id.text_uid);
            LogOut = findViewById(R.id.logout);

            // Retrieve user data from Firebase Realtime Database
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if (user != null) {
                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());

                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // User data exists in the database
                            String displayName = dataSnapshot.child("name").getValue(String.class);
                            String email = dataSnapshot.child("email").getValue(String.class);
                            String emp_id = dataSnapshot.child("emp_id").getValue(String.class);

                            // Update UI with the retrieved user data
                            textDisplayName.setText(displayName);
                            textEmail.setText(email);
                            textUid.setText(emp_id);
                        } else {
                            // Handle case where user data doesn't exist
                            Log.d("EmployeeProfile", "User data does not exist in the database");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle database error
                        Log.e("EmployeeProfile", "Database error: " + databaseError.getMessage());
                    }
                });
            } else {
                // No user is signed in
                Log.d("EmployeeProfile", "No user signed in");
            }

            // Set onClickListener for LogOut button
            LogOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle logout
                    Intent intent = new Intent(Employee_profile.this, Register.class);
                    startActivity(intent);
                    finish();
                }
            });
        }


    }
