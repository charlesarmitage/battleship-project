package com.cjra.battleship_project;

import android.app.Activity;
import android.os.Bundle;
import com.example.battleship_project.R;

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
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void displayFleetGrid() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
