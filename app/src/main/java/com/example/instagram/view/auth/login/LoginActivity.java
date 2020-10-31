package com.example.instagram.view.auth.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagram.R;
import com.example.instagram.models.User;
import com.example.instagram.view.auth.register.RegisterActivity;
import com.example.instagram.view.main.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView txtSignUp;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupView();
    }

     void setupView()
     {
         edtEmail=findViewById(R.id.loginEmail);
         edtPassword=findViewById(R.id.loginPassword);
         btnLogin=findViewById(R.id.loginBtn);
         txtSignUp=findViewById(R.id.loginCreateAccount);

         btnLogin.setOnClickListener(this);
         txtSignUp.setOnClickListener(this);

         mAuth=FirebaseAuth.getInstance();
     }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                String email=edtEmail.getText().toString();
                String password=edtPassword.getText().toString();

                //ToDo: Validation
                signIn(email,password);
                break;
            case R.id.loginCreateAccount:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
    private void signIn(String email,String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Wrong Email or Password! ",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
