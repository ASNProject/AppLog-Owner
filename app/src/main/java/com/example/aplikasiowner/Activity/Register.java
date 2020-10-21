package com.example.aplikasiowner.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplikasiowner.Adapter.Request_Register;
import com.example.aplikasiowner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText username, email, password, notelp;
    private Button registers;
    private DatabaseReference mDatabase;
    ProgressDialog progressDialog;
    private String Suser, Semail, Spassword, Snotelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.usernameregister);
        email = findViewById(R.id.emailregister);
        password = findViewById(R.id.passwordregister);
        notelp = findViewById(R.id.notelfon);
        registers = findViewById(R.id.butonregister);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        registers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String getusername = username.getText().toString();
                String getemail = email.getText().toString();
                String getpassword = password.getText().toString();
                String getnotelp = notelp.getText().toString();

                if (getusername.equals("")) {
                    username.setError("Masukkan username!");
                    username.requestFocus();
                    return;
                }
                if (getemail.equals("")) {
                    email.setError("Masukkan email!");
                    email.requestFocus();
                    return;
                }
                if (getpassword.equals("")) {
                    password.setError("Masukkan username!");
                    password.requestFocus();
                    return;
                }
                if (getnotelp.equals("")) {
                    notelp.setError("Masukkan username!");
                    notelp.requestFocus();
                    return;
                } else {
                    mAuth.createUserWithEmailAndPassword(getemail, getpassword)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        if (user != null) {
                                            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                                                    .setDisplayName(getusername)
                                                    .build();

                                            user.updateProfile(profile)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                progressDialog.dismiss();
                                                                Toast.makeText(Register.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                                                                Intent i = new Intent(Register.this, MainActivity.class);
                                                                startActivity(i);
                                                                finish();
                                                            }
                                                        }
                                                    });
                                        }
                                    }
                                    else {
                                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                            Toast.makeText(getApplicationContext(), "Akun sudah terdaftar", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Register.this, "Register Gagal", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                    submitUser(new Request_Register(getusername, getemail, getpassword, getnotelp));
                    progressDialog = new ProgressDialog(Register.this);
                    progressDialog.setMessage("Menyimpan Data");
                    progressDialog.setIndeterminate(false);
                    progressDialog.setCancelable(true);
                    progressDialog.show();
                }
            }
        });
    }


    private void submitUser(Request_Register request_register) {
String usernames = username.getText().toString();
mDatabase.child("Data User Owner")
        .child(usernames)
        .setValue(request_register)
        .addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                username.setText("");
                email.setText("");
                password.setText("");
                notelp.setText("");
            }
        });

    }
}