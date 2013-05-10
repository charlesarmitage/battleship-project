package com.cjra.battleship_project;

import com.cjra.battleship_project.FleetDeploymentView;
import com.cjra.battleship_project.OwnSeaGridController;
import com.cjra.battleship_project.OwnSeaGridModel;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class RenderInitialOwnSeaGridTests {
    private Mockery mockery = new Mockery();

    @Test
    public void playerHasOneShipToPlace(){
        final OwnSeaGridModel model = mockery.mock(OwnSeaGridModel.class);
        final FleetDeploymentView view = mockery.mock(FleetDeploymentView.class);
        final OwnSeaGridController controller = new OwnSeaGridController(model, view);

        mockery.checking(new Expectations(){
            {
                allowing(model).numberOfAvailableShips();
                will(returnValue(1));

                oneOf(view).setNumberOfAvailableShips(1);
            }
        });

       controller.render();

       mockery.assertIsSatisfied();
       //assertEquals(1, 2);
    }
}
