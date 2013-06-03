package com.cjra.battleship_project;

import org.junit.Test;
import static org.mockito.Mockito.*;

public class RenderInitialDeploymentGridTests {

    @Test
    public void playerHasOneShipToPlace(){
        FleetDeploymentView fleetView = mock(FleetDeploymentView.class);
        DeploymentModel deploymentModel = mock(DeploymentModel.class);
        when(deploymentModel.numberOfAvailableShips()).thenReturn(1);

        final OwnSeaGridController controller = new OwnSeaGridController(deploymentModel, fleetView);
        controller.render();

        verify(fleetView).setNumberOfAvailableShips(1);
    }
}
