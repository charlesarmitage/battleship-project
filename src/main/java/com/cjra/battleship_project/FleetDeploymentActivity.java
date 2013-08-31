package com.cjra.battleship_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.cjra.battleships.DeploymentController;
import com.cjra.battleships.DeploymentModel;
import com.cjra.battleships.DeploymentView;
import com.cjra.battleships.Positionable;
import com.cjra.battleships.ShipDeployment;
import com.cjra.battleships.ShipType;

import java.util.Collection;

/**
 * Default Battleship activity
 */
public class FleetDeploymentActivity extends Activity
        implements DeploymentView,
        View.OnTouchListener,
        View.OnLayoutChangeListener {

    private ShipDeployment battleshipGame;
    private DeploymentModel seaGrids;

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

        GraphicalDeploymentView seaGrid = (GraphicalDeploymentView)findViewById(R.id.fleet_deployment);
        seaGrid.addOnLayoutChangeListener(this);
        seaGrid.setOnTouchListener(this);

        TextView debug = (TextView)findViewById(R.id.bottom_text);
        seaGrid.setDebugText(debug);
    }

    @Override
    public void onResume(){
        super.onResume();
        battleshipGame.refresh();
    }

    public void setNumberOfAvailableShips(int numberOfAvailableShips) {
        if(numberOfAvailableShips < 0)
            throw new ImplementationError(
                    new IllegalArgumentException("Negative number of ships is invalid.")
            );

        String shipsNoun = numberOfAvailableShips != 1 ? "ships" : "ship";
        String shipsText = String.format("You have %d %s to place.\n", numberOfAvailableShips, shipsNoun);
        shipsText += "Touch grid to place ships.";

        TextView shipCountText = (TextView)findViewById( R.id.ship_count );
        shipCountText.setText(shipsText);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        TextView debugText = (TextView)findViewById(R.id.debug_text);
        debugText.setText("Touch event: " + event.getX() + ", " + event.getY());
        return false;
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

    }

    @Override
    public void displayShips(Collection<? extends Positionable> deployedShips) {

    }

    @Override
    public void displayAvailableShips(Collection<ShipType> availableShips) {

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
