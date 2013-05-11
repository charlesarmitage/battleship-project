package com.cjra.battleship_project;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Default FleetDeploymentActivity activity
 */
public class FleetDeploymentActivity extends Activity implements FleetDeploymentView {

    private OwnSeaGridController battleshipGame;
    private OwnSeaGridModel seaGrids;

    public FleetDeploymentActivity(){
        seaGrids = new NormalSeaGrids();
        battleshipGame = new OwnSeaGridController(seaGrids, this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public void onResume(){
        super.onResume();
        battleshipGame.render();
    }

    @Override
    public void setNumberOfAvailableShips(int numberOfAvailableShips) {
        String shipsNoun = numberOfAvailableShips != 1 ? " ships " : " ship ";
        String shipsText = "You have " + numberOfAvailableShips + shipsNoun +  "to place.\n";
        shipsText += "Touch grid below to place ships.";

        TextView shipCountText = (TextView)findViewById( R.id.ship_count );
        shipCountText.setText(shipsText);
    }

    @Override
    public void displayFleetGrid() {
    }
}
