package com.cjra.battleship_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cjra.battleships.DeploymentController;
import com.cjra.battleships.DeploymentModel;
import com.cjra.battleships.DeploymentView;
import com.cjra.battleships.Position;
import com.cjra.battleships.Positionable;
import com.cjra.battleships.ShipDeployment;
import com.cjra.battleships.ShipType;

import java.util.Collection;
import java.util.List;

/**
 * Default Battleship activity
 */
public class FleetDeploymentActivity extends Activity
        implements DeploymentView,
        View.OnTouchListener,
        View.OnLayoutChangeListener,
        View.OnClickListener {

    private ShipDeployment battleshipGame;
    private DeploymentModel deploymentModel;
    private GraphicalDeploymentView graphicalGrid;
    private ShipType offeredShip = ShipType.NONE;

    public FleetDeploymentActivity(){
        deploymentModel = new DeploymentModel();
        battleshipGame = new DeploymentController(this, deploymentModel);
    }

    public FleetDeploymentActivity(ShipDeployment view) {
        battleshipGame = view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        graphicalGrid = (GraphicalDeploymentView)findViewById(R.id.fleet_deployment);
        graphicalGrid.addOnLayoutChangeListener(this);
        graphicalGrid.setOnTouchListener(this);

        Button actionButton = (Button)findViewById((R.id.deployment_action_button));
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeShipClick();
            }
        });

        Button resetButton = (Button)findViewById(R.id.reset_deployment_button);
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                battleshipGame.resetGrid();
            }
        });

        TextView debug = (TextView)findViewById(R.id.bottom_text);
        graphicalGrid.setDebugText(debug);

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

        Position position = graphicalGrid.convertToPosition(event);
        battleshipGame.selectCell(position.x, position.y);
        return true;
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        displayDebugInfo();
    }

    @Override
    public void displaySelection(Positionable selection) {
        graphicalGrid.displaySelection(selection.getPositions());
    }

    @Override
    public void displayShips(Collection<? extends Positionable> deployedShips) {
            graphicalGrid.displayShip((Collection<Positionable>)deployedShips);
    }

    @Override
    public void displayAvailableShips(Collection<ShipType> availableShips) {
        String shipText = "Available ships:  ";
        for(ShipType ship : availableShips){
            shipText += shipTypeToString(ship);
            shipText += ", ";
        }

        TextView shipTextBox = (TextView)findViewById(R.id.available_ships);
        shipTextBox.setText(shipText.substring(0, shipText.length() - 2));
    }

    @Override
    public void offerShipPlacement(ShipType ship) {
        Button actionButton = (Button)findViewById(R.id.deployment_action_button);
        actionButton.setText("Place " + shipTypeToString(ship));
        if(ship == ShipType.NONE){
            actionButton.setVisibility(View.GONE);
        }
        else{
            actionButton.setVisibility(View.VISIBLE);
        }

        offeredShip = ship;
    }

    @Override
    public void offerGameStart() {
        Button actionButton = (Button)findViewById(R.id.deployment_action_button);
        actionButton.setVisibility(View.GONE);
        Button startButton = (Button)findViewById(R.id.game_start_button);
        startButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void refreshView() {

    }

    private void placeShipClick() {
        battleshipGame.placeShip(offeredShip);
        TextView debugText = (TextView)findViewById(R.id.debug_text);
        debugText.setText("Placed: " + shipTypeToString(offeredShip));
    }

    private String shipTypeToString(ShipType ship){
        String text = "";
        switch (ship){
            case PATROL_BOAT:
                text += "Patrol Boat";
                break;
            case DESTROYER:
                text += "Destroyer";
                break;
            case BATTLESHIP:
                text += "Battleship";
                break;
            case AIRCRAFT_CARRIER:
                text += "Carrier";
                break;
            default:
                break;
        }
        return text;
    }

    private void displayDebugInfo() {
        TextView debugText = (TextView)findViewById(R.id.debug_text);
        GraphicalDeploymentView seaGrid = (GraphicalDeploymentView)findViewById(R.id.fleet_deployment);

        debugText.setText("M: " + seaGrid.getMeasuredWidth() + ", W: " + seaGrid.getSpecMode());
    }
}
