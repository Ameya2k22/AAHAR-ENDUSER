package com.myinnovation.customer.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.myinnovation.customer.Models.User;
import com.myinnovation.customer.R;
import com.myinnovation.customer.databinding.ActivityOtpVerificationBinding;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OtpVerificationActivity extends AppCompatActivity {

    ActivityOtpVerificationBinding binding;
    private String verificationID = "";
    private String username = "", email = "", password = "", mobile = "";
    private FirebaseAuth mAuth;
    private String userOTP = "", userImage;
    private ProgressDialog dialog;
    private ProgressDialog pd;

    private FirebaseStorage storage;
    private FirebaseDatabase mbase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        InitializeFields();
        sendVerificationCode(mobile);


        binding.otp1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                if(binding.otp1.getText().toString().length()==1)
                {
                    binding.otp2.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        binding.otp2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                if(binding.otp2.getText().toString().length()==1)
                {
                    binding.otp3.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        binding.otp3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                if(binding.otp3.getText().toString().length()==1)
                {
                    binding.otp4.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void afterTextChanged(Editable s) {
            }

        });

        binding.otp4.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                if(binding.otp4.getText().toString().length()==1)
                {
                    binding.otp5.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        binding.otp5.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                if(binding.otp5.getText().toString().length()==1)
                {
                    binding.otp6.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        binding.otp6.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                if(binding.otp6.getText().toString().length()==1)
                {
                    binding.verifyOtp.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        binding.verifyOtp.setOnClickListener(v -> {
            userOTP = binding.otp1.getText().toString() + binding.otp2.getText().toString() + binding.otp3.getText().toString() + binding.otp4.getText().toString() + binding.otp5.getText().toString() + binding.otp6.getText().toString();
            if (TextUtils.isEmpty(userOTP) || userOTP.length() != 6) {
                Toast.makeText(OtpVerificationActivity.this, "Wrong OTP", Toast.LENGTH_SHORT).show();
            } else
                verifyCode(userOTP.trim());
        });
    }

    private void InitializeFields() {
        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        mbase = FirebaseDatabase.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setTitle("Wait Verification is important!!!");
        dialog.setIcon(R.drawable.ic_warning);
        dialog.setMessage("We are going to verify you whether you are human or not just for convenience");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        if (getIntent() != null) {
            username = getIntent().getStringExtra("USERNAME");
            email = getIntent().getStringExtra("EMAIL");
            mobile = getIntent().getStringExtra("MOBILE");
            password = getIntent().getStringExtra("PASSWORD");
            userImage = getIntent().getStringExtra("USERIMAGE");
        }
    }

    private void sendVerificationCode(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + phoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
            final String code = credential.getSmsCode();
            if (code != null) {
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(OtpVerificationActivity.this, "Verification Failed " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(@NonNull String s,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            super.onCodeSent(s, token);
            verificationID = s;
            dialog.dismiss();
            Toast.makeText(OtpVerificationActivity.this, "OTP is successfully sent check your inbox", Toast.LENGTH_LONG).show();
            binding.verifyOtp.setEnabled(true);
            binding.bar.setVisibility(View.INVISIBLE);
        }
    };

    private void verifyCode(String Code) {
        binding.bar.setVisibility(View.VISIBLE);
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, Code);
        signInByCredentials(credential);
    }

    private void signInByCredentials(PhoneAuthCredential credential) {

        pd = new ProgressDialog(this);
        pd.setTitle("Creating your account!!!");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Wait : ");
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener((OnCompleteListener) task -> {
                    if (task.isSuccessful()) {
                        saveUserImage();
                        saveUserDetail();
                        binding.bar.setVisibility(View.INVISIBLE);
                        Toast.makeText(OtpVerificationActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(OtpVerificationActivity.this, MainActivity.class));
                    }
                })
                .addOnFailureListener(e -> {
                    if(pd.isShowing()){
                        pd.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "Error occurred during creating your account...", Toast.LENGTH_LONG).show();
                });
    }

    private void saveUserDetail() {
        User user = new User(username, email, password, mobile, userImage);
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Signed in", Toast.LENGTH_SHORT).show();
                        String id = Objects.requireNonNull(task.getResult().getUser()).getUid();
                        FirebaseDatabase.getInstance().getReference().child("EndUser").child("Details").child(id).setValue(user)
                                .addOnCompleteListener(task1 -> {
                                    if(task1.isSuccessful()){
                                        Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Unable to create account", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveUserImage() {

        Uri userImageUri = Uri.parse(userImage);
        final StorageReference storageReference = storage.getReference().child("USER IMAGES").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()));

        storageReference.putFile(userImageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    if(pd.isShowing()){
                        pd.dismiss();
                    }
                    Toast.makeText(this, "Image saved successfully.", Toast.LENGTH_LONG).show();

                    storageReference.getDownloadUrl()
                            .addOnSuccessListener(uri1 -> mbase.getReference().child("Users").child(Objects.requireNonNull(mAuth.getUid())).child("coverPhoto").setValue(uri1.toString()))
                            .addOnFailureListener(e -> Toast.makeText(this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show());
                })
                .addOnProgressListener(snapshot -> {
                    int per = (int) ((100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount());
                    pd.setMessage("Uploaded : " + per + "%");
                    if(!pd.isShowing()){
                        pd.show();
                    }
                })
                .addOnFailureListener(e -> {
                    if(pd.isShowing()){
                        pd.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), "Unable to create account", Toast.LENGTH_LONG).show();
                });

    }
}