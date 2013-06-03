package com.cjra.battleship_project;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * com.cjra.battleship_project
 */
public class ShipDeploymentTests {

    @Test
    public void shouldBe_10_By_10_Grid(){
        NormalSeaGrids grid = new NormalSeaGrids();
        assertEquals(10, grid.width());
        assertEquals(10, grid.height());
    }

}
