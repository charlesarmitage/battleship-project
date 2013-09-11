package com.cjra.battleship_project;

import android.graphics.Canvas;

/**
 * Created by cjra on 07/09/13.
 */
public class GridShapeRotator {

    public void rotateCanvas(Canvas canvas, float degrees, float height, float width){
        canvas.save();
        canvas.rotate(degrees, (width/2)+1, (height/2)+1);
    }

    public void restoreCanvas(Canvas canvas){
        canvas.restore();
    }
}
