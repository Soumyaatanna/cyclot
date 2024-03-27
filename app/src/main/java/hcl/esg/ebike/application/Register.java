package hcl.esg.ebike.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword, editTextempid,editName;
    Button signup_btn;
    TextView signin_btn;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private DatabaseReference mDatabase;
// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = findViewById(R.id.email);
        editTextempid = findViewById(R.id.emp_id);
        editName = findViewById(R.id.name);
        editTextPassword = findViewById(R.id.password);
        signup_btn = findViewById(R.id.signup_btn);
        signin_btn = findViewById(R.id.signin_btn);


        mDatabase = FirebaseDatabase.getInstance().getReference();


        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password,name,empid;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                name = String.valueOf(editName.getText());
                empid = String.valueOf(editTextempid.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(Register.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(empid)) {
                    Toast.makeText(Register.this, "Enter Id", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Register.this, MapActivity.class);
                                    startActivity(intent);
                                    finish();
                                    sendData(v);
                                } else {

                                    Toast.makeText(Register.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }
    public void sendData(View view) {
        writeNewUser();
        Toast.makeText(this, "Data Inserted in database", Toast.LENGTH_SHORT).show();

    }

    public void writeNewUser() {
        User user = new User(
                editTextEmail.getText().toString(),
                editTextempid.getText().toString(),
                editName.getText().toString());


        mDatabase.child("users").child(user.getEmail()).setValue(user);
    }
}