package com.cjra.battleship_project;

public class NormalSeaGrids implements DeploymentModel {

    @Override
    public int numberOfAvailableShips() {
        return 1;
    }

    @Override
    public int width() {
        return 10;
    }

    @Override
    public int height() {
        return 10;
    }
}
