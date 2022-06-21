package com.myinnovation.customer.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.myinnovation.customer.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private String username = "", email = "", password = "", mobile = "";

    private FirebaseAuth mAuth;
    private FirebaseDatabase mbase;
    private ActivitySignUpBinding binding;

    private Uri userImageUri;
    private String userImage = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        mbase = FirebaseDatabase.getInstance();

        binding.regSubmit.setOnClickListener(view -> validateFieldsForSignUp());

        binding.toLogin.setOnClickListener(v -> {
            NavigationToNextActivity();
        });
        binding.userImage.setOnClickListener(v -> {
            Intent imageIntent = new Intent();
            imageIntent.setAction(Intent.ACTION_GET_CONTENT);
            imageIntent.setType("image/*");
            someActivityResultLauncher.launch(imageIntent);
        });

    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {

                ProgressDialog pd = new ProgressDialog(this);
                pd.setTitle("File Uploading");
                pd.setProgressStyle(pd.STYLE_SPINNER);
                pd.setMessage("Uploaded : ");
                pd.setCancelable(false);
                pd.setCanceledOnTouchOutside(false);

                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    assert data != null;
                    if(data.getData() != null){
                        userImageUri = data.getData();
                    } else{
                        Toast.makeText(this, "Unable to bring image...", Toast.LENGTH_LONG).show();
                        return;
                    }
                    userImage = String.valueOf(userImageUri);
                    binding.userImage.setImageURI(userImageUri);
                }
            });

    private void validateFieldsForSignUp() {
        email = binding.regEmail.getText().toString();
        password = binding.regPassword.getText().toString();
        username = binding.regUsername.getText().toString();
        mobile = binding.regMobile.getText().toString();

        if(userImage.equals("") || userImage.isEmpty()){
            Toast.makeText(this, "User Image can't be empty.", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            binding.regEmail.setError("Required");
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Check your email again...", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(mobile)) {
            binding.regMobile.setError("Required");
            return;
        } else if (!Patterns.PHONE.matcher(mobile).matches()) {
            Toast.makeText(this, "Wrong Mobile Number", Toast.LENGTH_LONG).show();
            return;
        } else if (mobile.length() != 10) {
            Toast.makeText(this, "Mobile Length should be 10 only.", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            binding.regPassword.setError("Required");
            return;
        } else if (password.length() < 8) {
            Toast.makeText(this, "Password Length should be 8 or greater than 8.", Toast.LENGTH_LONG).show();
            return;
        }

        registerUser();
    }

    private void registerUser() {
        Intent intent = new Intent(SignUpActivity.this, OtpVerificationActivity.class);
        intent.putExtra("USERNAME", username);
        intent.putExtra("EMAIL", email);
        intent.putExtra("PASSWORD", password);
        intent.putExtra("MOBILE", mobile);
        intent.putExtra("USERIMAGE", userImage);
        startActivity(intent);
    }

    public void NavigationToNextActivity() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}