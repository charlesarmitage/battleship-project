package com.cjra.battleship_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Default Battleship activity
 */
public class FleetDeploymentActivity extends Activity
        implements FleetDeploymentView,
        View.OnTouchListener,
        View.OnLayoutChangeListener {

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

        ImageView seaGrid = (ImageView)findViewById(R.id.imageView);
        seaGrid.addOnLayoutChangeListener(this);
        seaGrid.setOnTouchListener(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        battleshipGame.render();
    }

    @Override
    public void setNumberOfAvailableShips(int numberOfAvailableShips) {
        String shipsNoun = numberOfAvailableShips != 1 ? " ships " : " ship ";
        String shipsText = "You have " + numberOfAvailableShips + shipsNoun +  "to place.";
        //shipsText += "Touch grid below to place ships.";

        TextView shipCountText = (TextView)findViewById( R.id.ship_count );
        shipCountText.setText(shipsText);
    }

    @Override
    public void displayFleetGrid() {
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
        ImageView seaGrid = (ImageView)findViewById(R.id.imageView);

        debugText.setText("Grid: " + seaGrid.getBottom() + ", W: " + seaGrid.getWidth());
    }
}
