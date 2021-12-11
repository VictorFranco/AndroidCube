package com.example.cube;

import static com.example.cube.MatrixOperations.*;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrawingArea drawingArea = new DrawingArea(this);
        setContentView(drawingArea);
    }

    class DrawingArea extends View {
        float[][] vertices = new float[8][3];
        float[][] p_vertices = new float[8][2]; // projected vertices
        double angle = 0;
        public DrawingArea(Context context) {
            super(context);
            int counter = 0;
            for (int i=-1 ; i<=1 ; i+=2) // generate vertices
                for (int j=-1 ; j<=1 ; j+=2)
                    for (int k=-1 ; k<=1 ; k+=2)
                        vertices[counter++] = new float[]{i, j, k};
            vertices = mult(150,vertices);
        }
        public void onDraw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(25);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setColor(Color.BLACK);
            float[][] projection = {
                    {1,0,0},
                    {0,1,0}
            };
            float[][] rotateZ = {
                    {(float) Math.cos(angle), (float) -Math.sin(angle), 0},
                    {(float) Math.sin(angle), (float)  Math.cos(angle), 0},
                    {            0          ,              0          , 1},
            };
            float[][] rotateX = {
                    {1 ,           0            ,              0          },
                    {0 , (float) Math.cos(angle), (float) -Math.sin(angle)},
                    {0 , (float) Math.sin(angle), (float)  Math.cos(angle)},
            };
            float[][] rotateY = {
                    {(float) Math.cos(angle),  0  ,(float) -Math.sin(angle)},
                    {           0           ,  1  ,            0           },
                    {(float) Math.sin(angle),  0  ,(float)  Math.cos(angle)},
            };
            for (int i=0 ; i<8 ; i++) { // draw vertices
                float[][] rotated   = matmul(rotateZ,vecToMatrix(vertices[i]));
                rotated = matmul(rotateX,rotated);
                rotated = matmul(rotateY,rotated);
                float[][] projected = matmul(projection,rotated);
                projected = translate_xy(getWidth()/2,getHeight()/2,projected);
                p_vertices[i][0] = projected[0][0];
                p_vertices[i][1] = projected[1][0];
            }
            int[] pattern = {0, 1, 3, 2}; // relation among points
            for (int i=0 ; i<4 ; i++) {
                connect(pattern[i], pattern[(i+1)%4], canvas, paint, p_vertices);
                connect(pattern[i]+4, pattern[(i+1)%4]+4, canvas, paint, p_vertices);
                connect(pattern[i], pattern[i]+4, canvas, paint, p_vertices);
            }
            angle += 0.005;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    invalidate();
                }
            }, 10);
        }
        public void connect(int p1, int p2, Canvas canvas, Paint paint, float[][] points) {
            float[] vertex1 = points[p1];
            float[] vertex2 = points[p2];
            canvas.drawLine(vertex1[0],vertex1[1],vertex2[0],vertex2[1],paint);
        }
    }
}