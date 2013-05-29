package com.cjra.battleship_project;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Android view for fleet deployment.
 */
public class GraphicalDeploymentView extends ImageView {
    // TODO: get grid boarder width from gird drawable
    private static final int GRID_BOARDER = 10;
    private String mode = "";
    private int cellSize = 0;

    public GraphicalDeploymentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthSpec, int heightSpec){
        int sideSpec = measureSquare(widthSpec);
        setMeasuredDimension(sideSpec, sideSpec);
    }

    private int measureSquare(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        mode = MeasureSpec.toString(specMode);

        int specSize = MeasureSpec.getSize(measureSpec);
        int result = specSize;

        if (specMode == MeasureSpec.UNSPECIFIED || specMode == MeasureSpec.AT_MOST) {
            cellSize = ((specSize - 80) / 10);
            result = (cellSize * 10) + (2 * GRID_BOARDER);
        }

        return result;
    }

    protected void onDraw(Canvas canvas){
        GraphicalGridDrawable grid = new GraphicalGridDrawable();
        // TODO: Could sizing be derived from setBounds?
        grid.setSideLength(getMeasuredWidth());
        grid.setCellSize(cellSize);
        grid.draw(canvas);
    }

    public String getSpecMode() {
        return mode;
    }
}
