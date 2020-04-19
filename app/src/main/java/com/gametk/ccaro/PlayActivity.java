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

    private int step = 0;   // số bước đã đi
    private int[][] table;  // giá trị bảng
    private int x = 16;
    private int y = 11;// size table = x * y
    private Boolean hasAI;  // bật tắt AI;
    private Intent intent;
    private LinearLayout layoutView;  // khu vực chơi
    private LinearLayout dong ;
    private ImageView[][] imageViews; // ImageView mỗi ô
    private Button you1,you2;
    private int DX[] = {-1, 1, 0, 0, -1, -1, 1, 1};
    private int DY[] = {0, 0, -1, 1, -1, 1, -1, 1};

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
                imageViews[i][j].setBackgroundResource(R.drawable.trong);

                final int finalJ = j;
                final int finalI = i;
                imageViews[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (table[finalI][finalJ] == 0){
                            if (hasAI){
                                // cho AI chơi
                            }else {
                                step++;
                                if ((step % 2) == 0){
                                    imageViews[finalI][finalJ].setBackgroundResource(R.drawable.o);
                                    table[finalI][finalJ] = -1;
                                }else {
                                    imageViews[finalI][finalJ].setBackgroundResource(R.drawable.x);
                                    table[finalI][finalJ] = 1;
                                }
                                // check Win
                                if (checkWin() == 1){
                                    Toast.makeText(getApplicationContext(),"X win",Toast.LENGTH_SHORT).show();
                                    resetTable();
                                }
                                if (checkWin() == -1){
                                    Toast.makeText(getApplicationContext(),"O win",Toast.LENGTH_SHORT).show();
                                    resetTable();
                                }
                            }
                        }
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

    private int checkWin(){
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++)
                if (table[i][j] != 0)
                    for (int t = 0; t < 8; t++){
                        int sum = 0;
                        for (int k = 0; k < 5; k++){
                            int u = i + k * DX[t];
                            int v = j + k * DY[t];
                            if ((u >= 0) && (u < x) && (v >= 0) && (v < y)){
                                if (table[u][v] != table[i][j]) break;
                                sum += table[u][v];
                            }else break;
                        }
                        if (sum == 5) return 1;
                        if (sum == - 5) return -1;
                    }

        return 0;
    }

    private void resetTable(){
        Intent intent = new Intent(getApplicationContext(),PlayActivity.class);
        startActivity(intent);
        finish();
    }

}
