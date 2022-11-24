package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.recyclick.Model.brData;

public class editBarangActivity extends AppCompatActivity {
    private static final String TAG = "editBarangActivity";
    TextView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);
        btnBack = (TextView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(editBarangActivity.this, new DataBarangActivity().getClass()));
            }
        });
        if(getIntent().hasExtra("Selected")){
            brData brData = getIntent().getParcelableExtra("Selected");
            Log.d(TAG, "onCreate: " + brData.toString());
        }


    }
}