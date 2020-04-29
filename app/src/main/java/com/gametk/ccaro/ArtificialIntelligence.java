package com.gametk.ccaro;

public class ArtificialIntelligence {

    private static int nextMoveX;
    private static int nextMoveY;
    private static int DX[] = {-1, 1, 0, 0, -1, -1, 1, 1};
    private static int DY[] = {0, 0, -1, 1, -1, 1, -1, 1};

    public static int getNextMoveX() {
        return nextMoveX;
    }

    public static int getNextMoveY() {
        return nextMoveY;
    }

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

    public static void findNextMove(int[][] table, int x, int y) {


        if (SearchSum(table, x, y, 5, -4)) return;
        if (SearchSum(table, x, y, 5, 4)) return;

        if (SearchSum(table, x, y, 5, -3)) return;
        if (SearchSum(table, x, y, 5, 3)) return;

        if (SearchSum(table, x, y, 4, 3)) return;
        if (SearchSum(table, x, y, 4, -3)) return;

        if (SearchSum(table, x, y, 3, -2)) return;
        if (SearchSum(table, x, y, 3, 2)) return;
        if (SearchSum(table, x, y, 2, -1)) return;
        if (SearchSum(table, x, y, 2, 1)) return;


        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (table[i][j] == 0) {
                    nextMoveX = i;
                    nextMoveY = j;
                }
            }
        }

    }

    public static boolean SearchSum ( int[][] table, int x, int y, int length, int sum){
        int FoundX = 0;
        int FoundY = 0;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (table[i][j] * sum >= 0) {
                    for (int t = 0; t < 8; t++) {
                        boolean check = true;
                        int s = 0;

                        for (int k = 0; k < length; k++) {
                            int u = i + k * DX[t];
                            int v = j + k * DY[t];

                            if ((u >= 0) && (u < x) && (v >= 0) && (v < y)) {
                                if (table[u][v] * sum >= 0) {
                                    s += table[u][v];

                                    if (table[u][v] == 0) {
                                        FoundX = u;
                                        FoundY = v;
                                    }
                                } else {
                                    check = false;
                                    break;
                                }
                            } else {
                                check = false;
                                break;
                            }
                        }

                        if (check) {
                            if (s == sum) {
                                nextMoveX = FoundX;
                                nextMoveY = FoundY;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
