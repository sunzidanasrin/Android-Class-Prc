package com.example.j2ee_33.activityintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMain2Activity extends AppCompatActivity {

    TextView dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_main2);
        dis = findViewById(R.id.displayName);


        Intent intent = getIntent();

        dis.setText(intent.getStringExtra("urmi"));

    }
}
