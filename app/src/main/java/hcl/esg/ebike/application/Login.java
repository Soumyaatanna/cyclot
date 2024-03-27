package hcl.esg.ebike.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    TextInputEditText editTextEmail,editTextPassword;
    Button signin_btn;
    TextView signup_btn;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        signin_btn = findViewById(R.id.signin_btn);
        signup_btn = findViewById(R.id.signup_btn);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Login.this,Register.class);
                startActivity(intent);
                finish();
            }
        });
        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this,"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this,"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                               if (task.isSuccessful()){
                                   FirebaseDatabase.getInstance().getReference("users")
                                           .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                           .addListenerForSingleValueEvent(new ValueEventListener() {
                                               @Override
                                               public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                   if(email.equals("securityhcl@gmail.com") && password.equals("security")){
                                                       Toast.makeText(Login.this, "Login as security Successful", Toast.LENGTH_SHORT).show();
                                                       Intent intent =new Intent(Login.this, SecurityApp.class);
                                                       startActivity(intent);
                                                       finish();
                                                   }
                                                   else if(email.equals("adminn@gmail.com") && password.equals("adminn")) {
                                                       Toast.makeText(Login.this, "Login as admin Successful", Toast.LENGTH_SHORT).show();
                                                       Intent intent = new Intent(Login.this, AdminApp.class);
                                                       startActivity(intent);
                                                       finish();
                                                   }
                                                   else {
                                                       GlobalVar.currentUser = snapshot.getValue(User.class);
                                                       Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                                       Intent intent = new Intent(Login.this, MapActivity.class);
                                                       startActivity(intent);
                                                       finish();
                                                   }
                                               }

                                               @Override
                                               public void onCancelled(@NonNull DatabaseError error) {

                                               }
                                           });

                                }
                               else {
                                    Toast.makeText(Login.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });



    }
}