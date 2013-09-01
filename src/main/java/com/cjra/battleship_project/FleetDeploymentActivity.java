package com.cjra.battleship_project;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.cjra.battleships.DeploymentController;
import com.cjra.battleships.DeploymentModel;
import com.cjra.battleships.DeploymentView;
import com.cjra.battleships.Position;
import com.cjra.battleships.Positionable;
import com.cjra.battleships.ShipDeployment;
import com.cjra.battleships.ShipType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Default Battleship activity
 */
public class FleetDeploymentActivity extends Activity
        implements DeploymentView,
        View.OnTouchListener,
        View.OnLayoutChangeListener {

    private ShipDeployment battleshipGame;
    private DeploymentModel seaGrids;
    private GraphicalDeploymentView seaGrid;

    public FleetDeploymentActivity(){
        seaGrids = new DeploymentModel();
        battleshipGame = new DeploymentController(this, seaGrids);
    }

    public FleetDeploymentActivity(ShipDeployment view) {
        battleshipGame = view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        seaGrid = (GraphicalDeploymentView)findViewById(R.id.fleet_deployment);
        seaGrid.addOnLayoutChangeListener(this);
        seaGrid.setOnTouchListener(this);

        TextView debug = (TextView)findViewById(R.id.bottom_text);
        seaGrid.setDebugText(debug);

        battleshipGame.resetGrid();
    }

    @Override
    public void onResume(){
        super.onResume();
        battleshipGame.refresh();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() != MotionEvent.ACTION_DOWN){
            return false;
        }

        TextView debugText = (TextView)findViewById(R.id.debug_text);
        debugText.setText("Touch event: " + event.getX() + ", " + event.getY());

        Position position = seaGrid.convertToPosition(event);
        battleshipGame.selectCell(position.x, position.y);
        return true;
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        displayDebugInfo();
    }

    private void displayDebugInfo() {
        TextView debugText = (TextView)findViewById(R.id.debug_text);
        GraphicalDeploymentView seaGrid = (GraphicalDeploymentView)findViewById(R.id.fleet_deployment);

        debugText.setText("M: " + seaGrid.getMeasuredWidth() + ", W: " + seaGrid.getSpecMode());
    }

    @Override
    public void displaySelection(Positionable selection) {
        seaGrid.displaySelection(selection.getPositions());
    }

    @Override
    public void displayShips(Collection<? extends Positionable> deployedShips) {

    }

    @Override
    public void displayAvailableShips(Collection<ShipType> availableShips) {
        String shipText = "Available ships: ";
        for(ShipType ship : availableShips){
            switch (ship){
                case PATROL_BOAT:
                    shipText += "Patrol Boat, ";
                    break;
                case DESTROYER:
                    shipText += "Destroyer, ";
                    break;
                case BATTLESHIP:
                    shipText += "Battleship, ";
                    break;
                case AIRCRAFT_CARRIER:
                    shipText += "Carrier, ";
                    break;
                default:
                    break;
            }
        }

        TextView shipTextBox = (TextView)findViewById(R.id.available_ships);
        shipTextBox.setText(shipText);
    }

    @Override
    public void offerShipPlacement(ShipType ship) {

    }

    @Override
    public void offerGameStart() {

    }

    @Override
    public void refreshView() {

    }
}
