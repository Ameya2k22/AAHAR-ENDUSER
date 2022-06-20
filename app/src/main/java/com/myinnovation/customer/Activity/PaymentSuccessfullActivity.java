package com.myinnovation.customer.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myinnovation.customer.R;
import com.myinnovation.customer.databinding.ActivityPaymentSuccessfullBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class PaymentSuccessfullActivity extends AppCompatActivity {

    ActivityPaymentSuccessfullBinding binding;
    private String paymentId = "ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentSuccessfullBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(!getIntent().getExtras().isEmpty()){
            paymentId = getIntent().getStringExtra("PAYMENTID");
            binding.paymentId.setText(paymentId);
        }

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_animation);
        binding.imageView.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                takeScreenshot();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }, 3000);


    }

    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            openScreenshot(imageFile);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Screenshot saved.", Toast.LENGTH_SHORT).show();
    }
}