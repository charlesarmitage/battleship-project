package com.cjra.battleship_project;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Android view for fleet deployment.
 */
public class GraphicalDeploymentView extends ImageView {
    private static final int GRID_BOARDER = 5;
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
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
            mode = "EXACTLY";
        } else {
            mode = "OTHER";
            cellSize = ((specSize - 80) / 10);
            result = (cellSize * 10);

            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
                mode = "AT MOST";
            }
        }

        return result;
    }

    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        drawBoundry(canvas, paint);

        Path gridLines = new Path();
        addVerticalLines(gridLines);
        addHorizontaLines(gridLines);

        canvas.drawPath(gridLines, paint);
    }

    private void addHorizontaLines(Path gridLines) {
        RectF horizontalLine = new RectF(0, 0, getMeasuredWidth(), 2);
        for(int i = 0; i < 10; i++){
            horizontalLine.offset(0, cellSize);
            gridLines.addRect(horizontalLine, Path.Direction.CW);
        }
    }

    private void addVerticalLines(Path gridLines) {
        RectF verticalLine = new RectF(0, 0, 2, getMeasuredWidth());
        for(int i = 0; i < 10; i++){
            verticalLine.offset(cellSize, 0);
            gridLines.addRect(verticalLine, Path.Direction.CW);
        }
    }

    private void drawBoundry(Canvas canvas, Paint paint) {
        Path boarderTop = new Path();
        boarderTop.addRect(0, 0, getMeasuredWidth(), GRID_BOARDER, Path.Direction.CW);
        canvas.drawPath(boarderTop, paint);

        Path boarderLeft = new Path();
        boarderLeft.addRect(0, 0, GRID_BOARDER, getMeasuredWidth(), Path.Direction.CW);
        canvas.drawPath(boarderLeft, paint);

        Path boarderBottom = new Path();
        boarderBottom.addRect(0, getMeasuredWidth() - GRID_BOARDER, getMeasuredWidth(), getMeasuredWidth(), Path.Direction.CW);
        canvas.drawPath(boarderBottom, paint);

        Path boarderRight = new Path();
        boarderRight.addRect(getMeasuredWidth() - GRID_BOARDER, 0, getMeasuredWidth(), getMeasuredWidth(), Path.Direction.CW);
        canvas.drawPath(boarderRight, paint);
    }

    public String getSpecMode() {
        return mode;
    }
}
