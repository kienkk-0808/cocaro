package com.gametk.ccaro;

public class ArtificialIntelligence {

    private static int DX[] = {-1, 1, 0, 0, -1, -1, 1, 1};
    private static int DY[] = {0, 0, -1, 1, -1, 1, -1, 1};

    public static int checkWin(int[][] table, int x, int y){
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

}
