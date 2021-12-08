package com.example.cube;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
                    for (int k=-1 ; k<=1 ; k+=2) {
                        vertices[counter++] = new float[]{i, j, k};
                        Log.d("show",i + " " + j + " " + k);
                    }
        }
        public void onDraw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(25);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setColor(Color.BLACK);
            canvas.drawPoint(canvas.getWidth()/2,canvas.getHeight()/2,paint);
        }
    }
}