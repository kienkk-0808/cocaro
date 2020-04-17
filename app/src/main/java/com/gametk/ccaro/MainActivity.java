package com.gametk.ccaro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button may, nguoi;
    private Intent intent;
    private Boolean ai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        may.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ai = true;
                intent.putExtra("ai",ai);
                startActivity(intent);
            }
        });

        nguoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ai = false;
                intent.putExtra("ai",ai);
                startActivity(intent);
            }
        });


    }

    private void init(){
        may = findViewById(R.id.may);
        nguoi = findViewById(R.id.nguoi);
        intent = new Intent(MainActivity.this,PlayActivity.class);
    }

}
