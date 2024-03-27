package hcl.esg.ebike.application;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SecurityProfile extends AppCompatActivity {

    private TextView textDisplayName;
    private TextView textEmail;
    private TextView textUid;
    Button LogOut;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_profile);

        // Initialize TextViews
        textDisplayName = findViewById(R.id.text_display_name);
        textEmail = findViewById(R.id.text_email);
        textUid = findViewById(R.id.text_uid);
        LogOut = findViewById(R.id.logout);

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecurityProfile.this, Register.class);
                startActivity(intent);
                finish();
            }
        });


        // Assume you have retrieved the user data from Firebase Authentication
        String displayName = "Name : Soumya"; // Replace with user's display name
        String email = "Email : somyatanna103@gmail.com"; // Replace with user's email
        String uid = "Emp ID : CM-103"; // Replace with user's UID

        // Set the retrieved data to the respective TextViews
        textDisplayName.setText(displayName);
        textEmail.setText(email);
        textUid.setText(uid);
    }
}



