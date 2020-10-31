package com.example.instagram.view.auth.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.instagram.R;
import com.example.instagram.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUserName;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnCreateAccount;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupView();
    }

    void setupView() {
        edtUserName = findViewById(R.id.registerUserName);
        edtEmail = findViewById(R.id.registerEmail);
        edtPassword = findViewById(R.id.registerPassword);
        btnCreateAccount = findViewById(R.id.registerBtn);

        btnCreateAccount.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        String userName = edtUserName.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        // ToDo Validation

        //next step possible best change to constructor
        User user=new User();
        user.setName(userName);
        user.setEmail(email);
        user.setPassword(password);

        createUser(user);
    }

    private void createUser(final User user) {
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            saveUserToDB(firebaseUser.getUid(),user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void saveUserToDB(String id, User user) {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users").child(id);
        user.setPassword(null);
        myRef.setValue(user);

        Toast.makeText(RegisterActivity.this, "success!!",
                Toast.LENGTH_SHORT).show();
        finish();
    }
}
