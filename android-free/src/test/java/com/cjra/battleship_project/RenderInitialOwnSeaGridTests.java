package com.cjra.battleship_project;

import com.cjra.battleship_project.FleetDeploymentView;
import com.cjra.battleship_project.OwnSeaGridController;
import com.cjra.battleship_project.OwnSeaGridModel;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class RenderInitialOwnSeaGridTests {

    @Test
    public void playerHasOneShipToPlace(){
        FleetDeploymentView fleetView = mock(FleetDeploymentView.class);
        OwnSeaGridModel ownSeaModel = mock(OwnSeaGridModel.class);
        when(ownSeaModel.numberOfAvailableShips()).thenReturn(1);

        final OwnSeaGridController controller = new OwnSeaGridController(ownSeaModel, fleetView);
        controller.render();

        verify(fleetView).setNumberOfAvailableShips(1);
    }
}
