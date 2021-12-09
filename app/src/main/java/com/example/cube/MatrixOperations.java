package com.example.cube;

import android.util.Log;

public class MatrixOperations {
    static public float[][] matmul(float[][]a, float[][]b) {
        int colsA = a[0].length;
        int rowsA = a.length;
        int colsB = b[0].length;
        int rowsB = b.length;
        if(colsA != rowsB) return null;
        float[][] result = new float[rowsA][colsB];
        for (int i=0 ; i<rowsA ; i++)
            for (int j=0 ; j<colsB ; j++) {
                float sum = 0;
                for (int k=0 ; k<colsA ; k++)
                    sum += a[i][k] * b[k][j];
                result[i][j] = sum;
            }
        return result;
    }
    static float[][] vecToMatrix(float point[]) {
        float result[][] = new float[3][1];
        result[0][0] = point[0];
        result[1][0] = point[1];
        result[2][0] = point[2];
        return result;
    }
    static float[][] mult(float num,float[][]a) {
        int colsA = a[0].length;
        int rowsA = a.length;
        float result[][] = new float[rowsA][colsA];
        for (int i=0 ; i<rowsA ; i++)
            for (int j = 0; j<colsA; j++)
                result[i][j] = a[i][j] * num;
        return result;
    }
    static float[][] translate_xy(float x,float y,float[][]point) {
        float result[][] = new float[2][1];
        result[0][0] = point[0][0]+x;
        result[1][0] = point[1][0]+y;
        return result;
    }
    static void logMatrix(float[][]a) {
        StringBuilder message= new StringBuilder();
        message.append("\n\n------------\n");
        for (int i=0 ; i<a.length ; i++) {
            for (int j = 0; j<a[0].length; j++)
                message.append(a[i][j] + " ");
            message.append("\n");
        }
        message.append("------------\n");
        Log.d("show", String.valueOf(message));
    }
}
