package com.myinnovation.customer.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myinnovation.customer.Models.MessInfo;
import com.myinnovation.customer.Models.Notification;
import com.myinnovation.customer.R;
import com.myinnovation.customer.databinding.ActivityPaymentBinding;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    ActivityPaymentBinding binding;
    final int UPI_PAYMENT = 0;
    private int amount = 1;
    private String upiId = "";
    private String name = "";
    private String Mess_Owner_Mobile_number = "";
    private String Mess_Owner_Email = "";
    private String Mess_Owner_Name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.razorpaybtn.setOnClickListener(v -> {
            if(isConnectionAvailable(this)){
                makepayment();
            } else{
                Toast.makeText(getApplicationContext(), "Check your connection...", Toast.LENGTH_SHORT).show();
            }
        });

        binding.send.setOnClickListener(view -> {
            String amount = binding.amountEt.getText().toString();
            String note = binding.note.getText().toString();
            if(amount.equals("") || amount.isEmpty() || note.equals("") || note.isEmpty() || binding.name.equals("") || binding.upiId.equals("")){
                Toast.makeText(PaymentActivity.this, "Fields cannot be empty.", Toast.LENGTH_LONG).show();
            } else{
//                payUsingUpi(amount, binding.upiId.getText().toString(), binding.name.getText().toString(), note);
                FirebaseDatabase.getInstance().getReference().child("EndUser").child("Details").child(FirebaseAuth.getInstance().getUid()).child("mess_id").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            String id = snapshot.getValue(String.class);
                            FirebaseDatabase.getInstance().getReference().child("Customer").child("Mess-Info").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists()){
                                        for(DataSnapshot snapshot2:snapshot.getChildren()){
                                            if(snapshot2.getKey().equals(id)){
                                                MessInfo messInfo = snapshot2.getValue(MessInfo.class);
                                                binding.paymentId.setText(messInfo.getMess_upi_id());
                                                binding.name.setText(messInfo.getMess_name());
                                                payUsingUpi(amount, binding.paymentId.getText().toString(), binding.name.getText().toString(), note);

                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    void payUsingUpi(String amount, String upiId, String name, String note) {

        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();


        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);

        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");

        if(null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(PaymentActivity.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UPI_PAYMENT) {
            if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                if (data != null) {
                    String trxt = data.getStringExtra("response");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add(trxt);
                    upiPaymentDataOperation(dataList);
                } else {
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
            } else {
                ArrayList<String> dataList = new ArrayList<>();
                dataList.add("nothing");
                upiPaymentDataOperation(dataList);
            }
        }
    }

    private void upiPaymentDataOperation(ArrayList<String> data) {
        if (isConnectionAvailable(PaymentActivity.this)) {
            String str = data.get(0);
            Log.d("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String[] response = str.split("&");
            for (String s : response) {
                String[] equalStr = s.split("=");
                if (equalStr.length >= 2) {
                    if (equalStr[0].equalsIgnoreCase("Status")) {
                        status = equalStr[1].toLowerCase();
                    }
                } else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }

            if (status.equals("success")) {
                Toast.makeText(PaymentActivity.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                Notification notification = new Notification();
                notification.setNotificationType("Payment");
                notification.setNotificationBy(FirebaseAuth.getInstance().getUid());

                binding.amountEt.setText("");
                binding.note.setText("");
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(PaymentActivity.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
                binding.amountEt.setText("");
                binding.note.setText("");
            }
            else {
                Toast.makeText(PaymentActivity.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
                binding.amountEt.setText("");
                binding.note.setText("");
            }
        } else {
            Toast.makeText(PaymentActivity.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
            binding.amountEt.setText("");
            binding.note.setText("");
        }
    }

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable();
        }
        return false;
    }


    private void makepayment()
    {

        FirebaseDatabase.getInstance().getReference().child("EndUser").child("Details").child(FirebaseAuth.getInstance().getUid()).child("mess_id").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String id = snapshot.getValue(String.class);
                    FirebaseDatabase.getInstance().getReference().child("Customer").child("Mess-Info").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                for(DataSnapshot snapshot2:snapshot.getChildren()){
                                    if(snapshot2.getKey().equals(id)){
                                        MessInfo messInfo = snapshot2.getValue(MessInfo.class);
                                        amount = Integer.valueOf(messInfo.getMonthlyPrice());
                                        Mess_Owner_Mobile_number = messInfo.getPhone_no();
                                        Mess_Owner_Email = messInfo.getMess_email();
                                        Mess_Owner_Name = messInfo.getOwner_name();

                                        Checkout checkout = new Checkout();
                                        checkout.setKeyID("rzp_test_YogxmByRqeM0xU");

                                        checkout.setImage(R.drawable.aahar_logo);

                                        try {
                                            JSONObject options = new JSONObject();

                                            options.put("name", messInfo.getMess_name());
                                            options.put("description", "Mess Paymenr");
                                            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
                                            options.put("theme.color", "#3399cc");
                                            options.put("currency", "INR");
                                            options.put("amount", amount*100);//300 X 100

                                            options.put("prefill.email", Mess_Owner_Email);
                                            options.put("prefill.contact","+91" + Mess_Owner_Mobile_number);
                                            checkout.open(PaymentActivity.this, options);
                                        } catch(Exception e) {
                                            Log.e("TAG", "Error in starting Razorpay Checkout", e);
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment is successful", Toast.LENGTH_LONG).show();
        binding.paymentSuccessfulLinearLayout.setVisibility(View.VISIBLE);
        binding.paymentId.setText(s);
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        binding.upiPaymentLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment unsuccessful", Toast.LENGTH_SHORT).show();
        binding.upiPaymentLinearLayout.setVisibility(View.VISIBLE);
        binding.paymentSuccessfulLinearLayout.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}