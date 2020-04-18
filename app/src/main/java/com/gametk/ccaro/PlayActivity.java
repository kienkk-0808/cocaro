package com.gametk.ccaro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {

    private int step;   // số bước đã đi
    private int[][] table;  // giá trị bảng
    private int x = 16;
    private int y = 11;// size table = x * y
    private Boolean hasAI;  // bật tắt AI
    private Intent intent;
    private LinearLayout layoutView;  // khu vực chơi
    private LinearLayout dong ;
    private ImageView[][] imageViews; // ImageView mỗi ô
    Button you1,you2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        init();

        hasAI = intent.getBooleanExtra("ai",false);

        createTable();

        if (hasAI){
            you1.setText("YOU");
            you2.setText("CPU");
            // máy chơi
        } else {
            you1.setText("YOU 1");
            you2.setText("YOU 2");
            // người chơi
        }

    }

    private void init(){
        intent = getIntent();
        layoutView = findViewById(R.id.layout_view);
        layoutView.setWeightSum(x);
        you1 = findViewById(R.id.you1);
        you2 = findViewById(R.id.you2);
        table = new int[x][y];
        imageViews = new ImageView[x][y];
    }

    private void createTable(){
        for (int i = 0; i < x; i++){
            drawRow();
            for (int j = 0; j < y; j++){
                table[i][j] = 0;
                imageViews[i][j] = new ImageView(this);
                imageViews[i][j].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1));

                if((i+j)%2==0){
                    imageViews[i][j].setBackgroundResource(R.drawable.x);
                } else {
                    imageViews[i][j].setBackgroundResource(R.drawable.o);
                }

                final int finalJ = j;
                final int finalI = i;
                imageViews[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), finalI +":"+ finalJ,Toast.LENGTH_SHORT).show();
                    }
                });


                imageViews[i][j].setScaleType(ImageView.ScaleType.FIT_XY);
                dong.addView(imageViews[i][j]);
            }
            layoutView.addView(dong);
        }
    }

    private void drawRow(){
        dong = new LinearLayout(this);
        dong.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,1));
        dong.setOrientation(LinearLayout.HORIZONTAL);
        dong.setWeightSum(y);
    }

}
