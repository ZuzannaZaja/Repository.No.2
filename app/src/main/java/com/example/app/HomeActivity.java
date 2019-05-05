package com.example.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.FileOutputStream;

public class HomeActivity extends AppCompatActivity {

    private TextView name, email, points;
    private ImageView image;
    private Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        btn_logout = findViewById(R.id.btn_logout);
        image = findViewById(R.id.image);
        points = findViewById(R.id.points);

        Intent intent = getIntent();
        String extraName = intent.getStringExtra("name");
        String extraEmail = intent.getStringExtra("email");
        int extraPoints = intent.getIntExtra("points", 0);
        

        name.setText(extraName);
        email.setText(extraEmail);
        points.setText(String.valueOf(extraPoints));

    // image display
    try {
        BitMatrix bm = new QRCodeWriter().encode(
                extraEmail, BarcodeFormat.QR_CODE, 100, 100);
        Bitmap ImageBitmap = Bitmap.createBitmap(100, 100,
                Bitmap.Config.ARGB_8888);

        for (int i = 0; i < 100; i++) {// width
            for (int j = 0; j < 100; j++) {// height
                ImageBitmap.setPixel(i, j, bm.get(i, j) ? Color.BLACK
                        : Color.WHITE);
            }
        }
        image.setImageBitmap(ImageBitmap);
    }catch(Exception e){
        e.printStackTrace();
        Toast.makeText(HomeActivity.this, "Image Error"+ e.toString(), Toast.LENGTH_SHORT).show();
    }

    //logout button
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
