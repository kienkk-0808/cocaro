package com.gametk.ccaro;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {

    private int step = 0;   // số bước đã đi
    private int[] BX;
    private int[] BY;
    private int[][] table;  // giá trị bảng
    private int x = 16;
    private int y = 11;// size table = x * y
    private Boolean hasAI;  // bật tắt AI;
    private Intent intent;
    private LinearLayout layoutView;  // khu vực chơi
    private LinearLayout dong ;
    private ImageView[][] imageViews; // ImageView mỗi ô
    private Button you1,you2,resetView,datLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        init();

        hasAI = intent.getBooleanExtra("ai",false);

        createTable();

        resetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTable();
            }
        });

        datLai.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                back();
                if((step % 2) == 1){
                    you2.setBackgroundColor(getColor(R.color.colorGreen));
                    you1.setBackground(null);
                }else {
                    you2.setBackground(null);
                    you1.setBackgroundColor(getColor(R.color.colorGreen));
                }
            }
        });

        if (hasAI){
            you1.setText("YOU");
            you2.setText("CPU");
            // máy chơi
        } else {
            you1.setText("YOU 1\n(X)");
            you2.setText("YOU 2\n(O)");

            // người chơi
        }

    }

    private void init(){
        intent = getIntent();
        layoutView = findViewById(R.id.layout_view);
        layoutView.setWeightSum(x);
        you1 = findViewById(R.id.you1);
        you2 = findViewById(R.id.you2);
        resetView = findViewById(R.id.resetView);
        datLai = findViewById(R.id.datLai);
        table = new int[x][y];
        BX = new int[x*y];
        BY = new int[x*y];
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
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View v) {

                        if (table[finalI][finalJ] == 0){

                            if((step % 2) == 0){
                                you2.setBackgroundColor(getColor(R.color.colorGreen));
                                you1.setBackground(null);
                            }else {
                                you2.setBackground(null);
                                you1.setBackgroundColor(getColor(R.color.colorGreen));
                            }

                            if (hasAI){
                                step++;
                                // cho AI chơi
                            }else {
                                step++;
                                BX[step - 1] = finalI;
                                BY[step - 1] = finalJ;
                                if ((step % 2) == 0){
                                    imageViews[finalI][finalJ].setBackgroundResource(R.drawable.o);
                                    table[finalI][finalJ] = -1;
                                }else {
                                    imageViews[finalI][finalJ].setBackgroundResource(R.drawable.x);
                                    table[finalI][finalJ] = 1;
                                }
                                // check Win
                                if (ArtificialIntelligence.checkWin(table, x, y) == 1){
                                    Toast.makeText(getApplicationContext(),"X win",Toast.LENGTH_SHORT).show();
                                    resetTable();
                                }
                                if (ArtificialIntelligence.checkWin(table, x, y) == -1){
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

    private void resetTable(){
        Intent intent = new Intent(getApplicationContext(),PlayActivity.class);
        startActivity(intent);
        finish();
    }

    private void back(){
        if(step > 0) {
            imageViews[BX[step-1]][BY[step-1]].setBackgroundResource(R.drawable.trong);
            table[BX[step-1]][BY[step-1]] = 0;
            step = step - 1;
        }
    }

}
