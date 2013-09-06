package com.cjra.battleship_project;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by cjra on 06/09/13.
 */
public class ShipEndShape extends Shape {

    private float width;
    private float height;

    protected void onResize(float width, float height){
        super.onResize(width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);

        drawTopCurve(canvas, paint);
        drawBottomCurve(canvas, paint);
    }

    private void drawTopCurve(Canvas canvas, Paint paint) {
        Point end  = new Point((int)width-10, (int)(height/2)+3);
        Point middle  = new Point((int)(width*0.95), (int)height/4);
        Path curve = new Path();
        curve.moveTo(0, height/4);
        curve.quadTo(middle.x, middle.y, end.x, end.y);
        canvas.drawPath(curve, paint);
    }

    private void drawBottomCurve(Canvas canvas, Paint paint) {
        Point end  = new Point((int)width-10, (int)(height/2)-3);
        Point middle  = new Point((int)(width*0.95), (int)(3*(height/4)));
        Path curve = new Path();
        curve.moveTo(0, (int)(3*height/4));
        curve.quadTo(middle.x, middle.y, end.x, end.y);
        canvas.drawPath(curve, paint);
    }
}
