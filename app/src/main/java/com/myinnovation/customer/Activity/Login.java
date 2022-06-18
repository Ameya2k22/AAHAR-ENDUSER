package com.myinnovation.customer.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.myinnovation.customer.Activity.MainActivity;
import com.myinnovation.customer.Activity.SignUp;
import com.myinnovation.customer.R;

public class Login extends AppCompatActivity {
    EditText email, password;
    Button Login;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Login = findViewById(R.id.login);
        user = FirebaseAuth.getInstance().getCurrentUser();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                FirebaseAuth.getInstance().signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(com.myinnovation.customer.Activity.Login.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    public void SignUp(View view) {
        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(user != null){
//            Intent intent = new Intent(Login.this, MainActivity.class);
//            startActivity(intent);
//        }
//    }

}