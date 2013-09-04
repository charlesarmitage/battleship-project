package com.cjra.battleship_project;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.shapes.Shape;

/**
 * com.cjra.battleship_project
 */
public class ShipBodyShape extends Shape {
    private final Resources resources;
    private float width;
    private float height;
    private boolean isVertical = false;

    public ShipBodyShape(Resources res){
        this.resources = res;
    }

    public void setVertical(){
        isVertical = true;
    }

    protected void onResize(float width, float height){
        super.onResize(width, height);
        this.width = width;
        this.height = height;
    }



    @Override
    public void draw(Canvas canvas, Paint paint) {

        canvas.save();
        if(isVertical){
            canvas.translate(width+1, (float)0.0);
            canvas.rotate((float)90.0);
        }

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15);

        canvas.drawLine(-1, height/4, width+1, height/4, paint);
        canvas.drawLine(-1, (height/4)*3, width+1, (height/4)*3, paint);

        Paint fillPaint = new Paint();
        int blue = resources.getColor(R.color.seablue);
        fillPaint.setColor(blue);
        fillPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(-1, height/4, width+1, 3*(height/4), fillPaint);

        canvas.restore();
    }
}
