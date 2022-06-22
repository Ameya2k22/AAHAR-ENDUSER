package com.myinnovation.customer.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.myinnovation.customer.databinding.ActivityLoginBinding;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase mBase;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

    String email;
    String password;

    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toSignUpPage.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            view.getContext().startActivity(intent);
        });

        mAuth = FirebaseAuth.getInstance();
        mBase = FirebaseDatabase.getInstance();
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        binding.loginBtn.setOnClickListener(view -> validateFieldsForLogin());

        binding.signInGoogleBtn.setOnClickListener(view -> SignInUsingGoogle());


        binding.loginBtn.setOnClickListener(view -> {
            binding.progressBar3.setVisibility(View.VISIBLE);
            String Email = binding.loginEmail.getText().toString();
            String Password = binding.loginPassword.getText().toString();

            FirebaseAuth.getInstance().signInWithEmailAndPassword(Email, Password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "LoginActivity Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    binding.progressBar3.setVisibility(View.INVISIBLE);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    binding.progressBar3.setVisibility(View.INVISIBLE);
                }
            });
        });
    }

    private void SignInUsingGoogle() {
        binding.progressBar3.setVisibility(View.VISIBLE);
        Intent signInIntent = googleSignInClient.getSignInIntent();
        someActivityResultLauncher.launch(signInIntent);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // There are no request codes
                    Intent data = result.getData();
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

                    try {
                        task.getResult(ApiException.class);
                        NavigationToNextActivity();
                    } catch (ApiException e){
                        binding.progressBar3.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this, "Something went wrong" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

    private void validateFieldsForLogin() {
        email = binding.loginEmail.getText().toString();
        password = binding.loginPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            binding.loginEmail.setError("Required");
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Check your email again...", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            binding.loginPassword.setError("Required");
            return;
        } else if (password.length() < 8) {
            Toast.makeText(this, "Password Length should be 8 or greater than 8.", Toast.LENGTH_LONG).show();
            return;
        }

        LoginUsingEmailPassword();
    }

    private void LoginUsingEmailPassword() {
        binding.progressBar3.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    if (Objects.requireNonNull(mAuth.getCurrentUser()).isEmailVerified()) {
                        binding.loginEmail.setText("");
                        binding.loginPassword.setText("");
                        binding.progressBar3.setVisibility(View.VISIBLE);
                        NavigationToNextActivity();
                    } else {
                        binding.loginEmail.setText("");
                        binding.loginPassword.setText("");
                        binding.progressBar3.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this, "Email is not verified yet please verify...", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(e -> {
                    binding.loginEmail.setText("");
                    binding.loginPassword.setText("");
                    binding.progressBar3.setVisibility(View.VISIBLE);
                    Toast.makeText(LoginActivity.this, "Login Failed try again...", Toast.LENGTH_LONG).show();
                });
    }

    private void    NavigationToNextActivity(){
        binding.progressBar3.setVisibility(View.VISIBLE);
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(mAuth.getCurrentUser() != null){
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//        }
//    }
}