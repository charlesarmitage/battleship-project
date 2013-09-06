package com.cjra.battleship_project;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjra.battleships.Position;
import com.cjra.battleships.Positionable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Android view for fleet deployment.
 */
public class GraphicalDeploymentView extends ImageView {
    // TODO: get grid boarder width from gird drawable
    private static final int GRID_BOARDER = 10;
    private String mode = "";
    private int cellSize = 0;
    private List<Point> selectionPoints = new ArrayList<Point>();
    private TextView debugText = null;
    private GraphicalGridDrawable gridDrawable = new GraphicalGridDrawable();
    private Collection<Positionable> ships = new ArrayList<Positionable>();

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

        drawSelectionPoints(canvas);

        for(Positionable ship : ships){
            drawShip(ship,canvas);
        }
    }

    private void drawSelectionPoints(Canvas canvas) {
        RingShape circle = new RingShape();
        ShapeDrawable touch = new ShapeDrawable(circle);
        touch.getPaint().setColor(Color.WHITE);

        for(Point cell : selectionPoints) {
            Rect bounds = gridDrawable.getCellBounds(cell);
            touch.setBounds(bounds);
            touch.draw(canvas);
        }
    }

    private void drawShip(Positionable ship, Canvas canvas) {
        ShipBodyShape body = new ShipBodyShape(getResources());
        if(ship.isVertical()){
            body.setVertical();
        }
        ShapeDrawable shipDrawable;

        for(Position position : ship.getPositions()){
            if(position.equals(ship.start()) || position.equals(ship.end())){
                shipDrawable = new ShapeDrawable(new ShipEndShape());
                shipDrawable.getPaint().setColor(Color.WHITE);
            }
            else {
                shipDrawable = new ShapeDrawable(body);
                shipDrawable.getPaint().setColor(Color.WHITE);
            }

            Point point = new Point(position.x, position.y);
            Rect bounds = gridDrawable.getCellBoundsWithoutMargin(point);
            shipDrawable.setBounds(bounds);
            shipDrawable.draw(canvas);
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
        printSelectionDebugText(positions);

        selectionPoints.clear();
        for(Position position : positions){
            Point point = new Point(position.x, position.y);
            selectionPoints.add(point);
        }

        invalidate();
    }

    private void printSelectionDebugText(List<Position> positions) {
        String text = "Sel: ";
        for(Position position : positions){
            text += "(" + position.x + ", " + position.y + "),";
        }

        if(debugText != null){
            debugText.setText(text);
        }
    }

    public void displayShip(Collection<Positionable> shipPositions) {
        ships = shipPositions;
    }
}
