package com.example.ccc.login;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.ccc.MainActivity;
import com.example.ccc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity {

    private EditText userEmail, userPassword;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progress_bar);
        TextView signButton = findViewById(R.id.register);
        TextView userLogInButton = findViewById(R.id.login_button);
        TextView forgotPassword = findViewById(R.id.forgot_password);

        if(mAuth.getCurrentUser() != null){
            if(!mAuth.getCurrentUser().isEmailVerified()){
            Toast.makeText(Login.this, "Verify Your email",Toast.LENGTH_SHORT)
                    .show();
        }else {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
            }
        }



        forgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, ForgotPassword.class);
            startActivity(intent);
            finish();
        });

        userLogInButton.setOnClickListener(view -> {
            if (!emailValidation() || !passwordValidation()) {
                return;
            }
            view.setVisibility(View.GONE);
            String email = Objects.requireNonNull(userEmail).getText().toString().trim();
            String password = Objects.requireNonNull(userPassword).getText().toString().trim();
            progressBar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                    if( task.isSuccessful() && mAuth.getCurrentUser().isEmailVerified()){

                        progressBar.setVisibility(View.GONE);
                        view.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(Login.this, "logged in successful!", Toast.LENGTH_SHORT).show();


                    } else if(!task.isSuccessful()){

                        Toast.makeText(Login.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        view.setVisibility(View.VISIBLE);

                    } else{
                        mAuth.getCurrentUser().sendEmailVerification();
                        Toast.makeText(Login.this, "verify your email!", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        view.setVisibility(View.VISIBLE);

                    }

                }
            });

        });

        signButton.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, Registering.class);
            startActivity(intent);
            finish();
        });
    }

    public boolean emailValidation() {

        String email = userEmail.getText().toString();
        String[] emailParts = email.split("@");
        String domain = "keyaka.ul.ac.za";
        String domainPart = "";
        try {
            domainPart = emailParts[1];
        }
        catch (ArrayIndexOutOfBoundsException ignored){
        }

        if (email.isEmpty()) {

            userEmail.setError("Email Required*");
            return false;
        } else if (!domainPart.equals(domain)) {
            userEmail.setError("Please provide school email*");
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            userEmail.setError("Please provide valid email*");
            return false;
        } else {
            userEmail.setError(null);
            return true;
        }
    }

    public boolean passwordValidation() {

        String password = Objects.requireNonNull(userPassword).getText().toString().trim();

        if (password.isEmpty()) {

            userPassword.setError("Password Required*");
            return false;
        } else if (password.length() < 6) {

            userPassword.setError("Password should be 6 characters or more*");
            return false;
        } else {
            userPassword.setError(null);
            return true;
        }
    }
}