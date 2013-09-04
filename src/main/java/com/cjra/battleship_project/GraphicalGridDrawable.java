package com.cjra.battleship_project;

import android.graphics.*;
import android.graphics.drawable.ShapeDrawable;

/**
 * Graphical Grid Drawable
 */
public class GraphicalGridDrawable extends ShapeDrawable {
    private static final int GRID_BOARDER = 10;
    private static final int GRID_SIZE = 9;
    private int sideLength = 0;
    private int cellSize = 0;

    public GraphicalGridDrawable(){
        super();
    }

    public void setSideLength(int length){
        sideLength = length;
    }

    public void setCellSize(int length){
        // TODO: Resolve name mis-match
        cellSize = length;
    }

    @Override
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        drawBoundry(canvas, paint);

        Path gridLines = new Path();
        addVerticalLines(gridLines);
        addHorizontaLines(gridLines);
        canvas.drawPath(gridLines, paint);
    }

    private void addHorizontaLines(Path gridLines) {
        RectF horizontalLine = new RectF(0, 0, sideLength, 2);
        horizontalLine.offset(0, GRID_BOARDER);
        for(int i = 0; i < GRID_SIZE; i++){
            horizontalLine.offset(0, cellSize);
            gridLines.addRect(horizontalLine, Path.Direction.CW);
        }
    }

    private void addVerticalLines(Path gridLines) {
        RectF verticalLine = new RectF(0, 0, 2, sideLength);
        verticalLine.offset(GRID_BOARDER, 0);
        for(int i = 0; i < GRID_SIZE; i++){
            verticalLine.offset(cellSize, 0);
            gridLines.addRect(verticalLine, Path.Direction.CW);
        }
    }

    private void drawBoundry(Canvas canvas, Paint paint) {
        int sideLength = canvas.getClipBounds().width();

        Path boarderTop = new Path();
        boarderTop.addRect(0, 0, sideLength, GRID_BOARDER, Path.Direction.CW);
        canvas.drawPath(boarderTop, paint);

        Path boarderLeft = new Path();
        boarderLeft.addRect(0, 0, GRID_BOARDER, sideLength, Path.Direction.CW);
        canvas.drawPath(boarderLeft, paint);

        Path boarderBottom = new Path();
        boarderBottom.addRect(0, sideLength - GRID_BOARDER, sideLength, sideLength, Path.Direction.CW);
        canvas.drawPath(boarderBottom, paint);

        Path boarderRight = new Path();
        boarderRight.addRect(sideLength - GRID_BOARDER, 0, sideLength, sideLength, Path.Direction.CW);
        canvas.drawPath(boarderRight, paint);
    }

    public Point getCellCoordinates(int viewX, int viewY) {
        int x = (viewX - GRID_BOARDER) / cellSize;
        int y = (viewY - GRID_BOARDER) / cellSize;
        return new Point(x, y);
    }

    public Rect getCellBounds(Point cell) {
        int offsetX = (cell.x*cellSize) + GRID_BOARDER + 1;
        int offsetY = (cell.y*cellSize) + GRID_BOARDER + 1;
        int CELL_MARGIN = GRID_BOARDER / 2;
        return new Rect(offsetX + CELL_MARGIN, offsetY + CELL_MARGIN, cellSize + offsetX - CELL_MARGIN, cellSize + offsetY - CELL_MARGIN);
    }

    public Rect getCellBoundsWithoutMargin(Point cell) {
        int offsetX = (cell.x*cellSize) + GRID_BOARDER;
        int offsetY = (cell.y*cellSize) + GRID_BOARDER;
        int CELL_MARGIN = 0;
        return new Rect(offsetX + CELL_MARGIN, offsetY + CELL_MARGIN, cellSize + offsetX - CELL_MARGIN, cellSize + offsetY - CELL_MARGIN);
    }
}
