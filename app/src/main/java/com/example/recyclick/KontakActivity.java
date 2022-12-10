package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class KontakActivity extends AppCompatActivity {
CardView wa,ig;
TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);
        wa = findViewById(R.id.crdWa);
        ig = findViewById(R.id.crdIg);
        back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noTelp = "+6281252277680";
                Uri uri = Uri.parse(String.format(
                        "https://api.whatsapp.com/send?phone=%s",noTelp
                ));

                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String appLink = "https://www.instagram.com/afdholanabil/";
                String pack = "com.instagram.android";

                bukaLink(appLink,pack,appLink);
            }
        });
    }

    public void bukaLink(String appLink , String pack,String webLink){
        try{
            Uri uri = Uri.parse(appLink);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(pack);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException activityNotFoundException){
            Uri uri = Uri.parse(webLink);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }


    }
}