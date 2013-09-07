package com.cjra.battleship_project;

import android.graphics.Canvas;

/**
 * Created by cjra on 07/09/13.
 */
public class GridShapeRotator {

    public void rotateCanvas(Canvas canvas, float degrees, float width){
        canvas.save();

        if(degrees == (float)0.0){
            return;
        }

        canvas.translate(width+1, (float)0.0);
        canvas.rotate(degrees);
    }

    public void restoreCanvas(Canvas canvas){
        canvas.restore();
    }
}
