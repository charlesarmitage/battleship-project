package com.cjra.battleship_project;

import android.widget.TextView;

import com.cjra.battleships.ShipDeployment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class FleetDeploymentActivityTests {

    @Test
    public void RefreshesGameWhenActivityResumed(){
        ShipDeployment view = mock(ShipDeployment.class);
        FleetDeploymentActivity activity = new FleetDeploymentActivity(view);

        activity.onCreate(null);
        activity.onResume();

        verify(view).refresh();
    }
}
