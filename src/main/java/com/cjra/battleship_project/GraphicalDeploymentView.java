package com.cjra.battleship_project;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjra.battleships.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Android view for fleet deployment.
 */
public class GraphicalDeploymentView extends ImageView {
    // TODO: get grid boarder width from gird drawable
    private static final int GRID_BOARDER = 10;
    private String mode = "";
    private int cellSize = 0;
    private List<Point> touchPoints = new ArrayList<Point>();
    private TextView debugText = null;
    private GraphicalGridDrawable gridDrawable = new GraphicalGridDrawable();

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
        // TODO: Could sizing be derived from setBounds?
        gridDrawable.setSideLength(getMeasuredWidth());
        gridDrawable.setCellSize(cellSize);
        gridDrawable.draw(canvas);

        drawTouchPoints(canvas);
    }

    private void drawTouchPoints(Canvas canvas) {
        RingShape circle = new RingShape();
        ShapeDrawable touch = new ShapeDrawable(circle);
        touch.getPaint().setColor(Color.WHITE);

        for(Point cell : touchPoints) {
            Rect bounds = gridDrawable.getCellBounds(cell);
            touch.setBounds(bounds);
            touch.draw(canvas);
        }
    }

    public String getSpecMode() {
        return mode;
    }

    public void setDebugText(TextView text){
        debugText = text;
    }

    public Position convertToPosition(MotionEvent event) {
        Point cell = gridDrawable.getCellCoordinates((int)event.getX(), (int)event.getY());
        Position position = new Position(cell.x, cell.y);
        return position;
    }

    public void displaySelection(List<Position> positions) {
        String text = "Sel: ";

        touchPoints.clear();
        for(Position position : positions){
            Point point = new Point(position.x, position.y);
            touchPoints.add(point);
            text += "(" + position.x + ", " + position.y + "),";
        }

        if(debugText != null){
            debugText.setText(text);
        }

        invalidate();
    }
}
