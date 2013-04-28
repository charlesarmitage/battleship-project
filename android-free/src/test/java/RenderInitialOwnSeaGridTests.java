/**
 * Created with IntelliJ IDEA.
 * User: carmitage
 * Date: 28/04/13
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class RenderInitialOwnSeaGridTests {
    private Mockery mockery = new Mockery();

    @Test
    public void playerHasOneShipToPlace(){
        final OwnSeaGridModel model = mockery.mock(OwnSeaGridModel.class);
        final OwnSeaGridView view = mockery.mock(OwnSeaGridView.class);
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
