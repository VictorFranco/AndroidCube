package com.example.cube;

import static com.example.cube.MatrixOperations.*;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrawingArea drawingArea = new DrawingArea(this);
        setContentView(drawingArea);
    }

    class DrawingArea extends View {
        float[][] vertices = new float[8][3];
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
            for (int i=0 ; i<8 ; i++)
                vertices[i] = translate_xy(getWidth()/2,getHeight()/2,vertices[i]);

            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(25);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setColor(Color.BLACK);
            float[][] projection = {
                    {1,0,0},
                    {0,1,0}
            };
            for (int i=0 ; i<8 ; i++) { // draw vertices
                float projected[][] = matmul(projection,vecToMatrix(vertices[i]));
                canvas.drawPoint(projected[0][0], projected[1][0], paint);
            }
        }
    }
}