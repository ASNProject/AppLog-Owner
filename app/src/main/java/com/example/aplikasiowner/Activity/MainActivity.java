package com.example.aplikasiowner.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasiowner.R;
import com.example.aplikasiowner.SharePreference.SharePreferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText email, pass;
    private Button simpan;
    private CheckBox cek;
    private FirebaseAuth mAuth;
    private TextView daf;
    ProgressDialog progressDialog;
    SharePreferences sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser users = mAuth.getCurrentUser();

        email = findViewById(R.id.emaillogin);
        pass = findViewById(R.id.paswordlogin);
        simpan = findViewById(R.id.butonmasuk);
        cek = findViewById(R.id.cekbok);
        daf = findViewById(R.id.daftar);

        sessions = new SharePreferences(MainActivity.this.getApplicationContext());
        String getemail = sessions.getEmail();
        String getpass = sessions.getPassword();
        email.setText(getemail);
        pass.setText(getpass);

        if (email.getText().toString().length() > 0 && pass.getText().toString().length() > 0){
            Toast.makeText(this, "Berhasil Masuk", Toast.LENGTH_SHORT).show();
        }
        else if ((email.getText().toString().length()==0) && (pass.getText().toString().length()==0)){
            simpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String statusemail = email.getText().toString();
                    String setPassword = pass.getText().toString();

                    if (statusemail.equals("")){
                        email.setError("Masukkan Email");
                        email.requestFocus();
                        return;
                    }
                    else if (setPassword.equals("")){
                        pass.setError("Masukkan Password");
                        pass.requestFocus();
                        return;
                    }
                    else {
                        mAuth.signInWithEmailAndPassword(statusemail, setPassword)
                                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            Toast.makeText(MainActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                                            if (cek.isChecked())
                                            {
                                                sessions.setEmail(email.getText().toString());
                                                sessions.setPassword(pass.getText().toString());
                                            }
                                            progressDialog.dismiss();
                                        }
                                        else {
                                            Toast.makeText(MainActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Memuat Data");
                    progressDialog.setIndeterminate(false);
                    progressDialog.setCancelable(true);
                    progressDialog.show();
                }
            });
        }



        daf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
                finish();
            }
        });

    }
}