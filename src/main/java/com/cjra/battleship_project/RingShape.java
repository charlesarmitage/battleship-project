package com.cjra.battleship_project;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;

/**
 * com.cjra.battleship_project
 */
public class RingShape extends Shape {
    private float width;
    private float height;

    public RingShape(){
    }

    protected void onResize(float width, float height){
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        OvalShape circle = new OvalShape();
        circle.resize(width, height);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        circle.draw(canvas, paint);
    }
}
