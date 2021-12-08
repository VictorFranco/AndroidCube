package com.example.cube;

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
        public DrawingArea(Context context) {
            super(context);
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