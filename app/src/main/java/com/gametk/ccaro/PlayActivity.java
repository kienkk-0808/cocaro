package com.gametk.ccaro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class PlayActivity extends AppCompatActivity {

    private int step;   // số bước đã đi
    private int[][] table;  // giá trị bảng
    private int x,y;    // size table = x * y
    private Boolean hasAI;  // bật tắt AI
    private Intent intent;
    private ImageView[][] imageViews; // ImageView mỗi ô

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        init();

        hasAI = intent.getBooleanExtra("ai",false);

        createTable();

        if (hasAI){
            // máy chơi
        } else {
            // người chơi
        }

    }

    private void init(){
        intent = getIntent();
    }

    private void createTable(){
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                table[i][j] = 0;
            }
        }
    }

    private void drawTable(){

    }


}
