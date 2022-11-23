package com.example.recyclick;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.recyclick.Fragment.TambahBarangFragment;
public class ContainerFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_fragment);
        Util.gantiFragment(getSupportFragmentManager(), new TambahBarangFragment());
    }
}