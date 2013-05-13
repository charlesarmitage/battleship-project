package com.cjra.battleship_project;

public class OwnSeaGridController implements RendersView {
    private OwnSeaGridModel model;
    private FleetDeploymentView view;

    public OwnSeaGridController(OwnSeaGridModel model, FleetDeploymentView view) {

        this.model = model;
        this.view = view;
    }

    public void render() {
        int availableShips = model.numberOfAvailableShips();
        view.setNumberOfAvailableShips(availableShips);
    }
}
