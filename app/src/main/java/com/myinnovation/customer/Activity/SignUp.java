package com.myinnovation.customer.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.myinnovation.customer.R;
import com.myinnovation.customer.User;

public class SignUp extends AppCompatActivity {

    EditText Name, Email, Password, Phone_no;
    TextView Login;
    Button SignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Name = findViewById(R.id.Name);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        Phone_no = findViewById(R.id.Phone_no);
        SignUp = findViewById(R.id.SignUp);
        Login = findViewById(R.id.Login);


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(Name.getText().toString(), Email.getText().toString(),
                        Password.getText().toString(), Phone_no.getText().toString());
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(Email.getText().toString(), Password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(com.myinnovation.customer.Activity.SignUp.this, "Signed in", Toast.LENGTH_SHORT).show();
                            String id = task.getResult().getUser().getUid();
                            FirebaseDatabase.getInstance().getReference().child("EndUser").child("Details").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUp.this, "User Created", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(com.myinnovation.customer.Activity.SignUp.this, "not Signed in", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });


    }

    public void Login(View view) {
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
    }
}