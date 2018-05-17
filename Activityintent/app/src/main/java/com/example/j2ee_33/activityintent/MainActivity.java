package com.example.j2ee_33.activityintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText n;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n = findViewById(R.id.name);

    }
    public void display(View view){

         Intent intent = new Intent(this, DisplayMain2Activity.class);
        intent.putExtra("urmi", n.getText().toString());
        startActivity(intent);

    }
}
