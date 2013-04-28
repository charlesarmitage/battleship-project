/**
 * Created with IntelliJ IDEA.
 * User: carmitage
 * Date: 28/04/13
 * Time: 18:17
 * To change this template use File | Settings | File Templates.
 */
public class OwnSeaGridController {
    private OwnSeaGridModel model;
    private OwnSeaGridView view;

    public OwnSeaGridController(OwnSeaGridModel model, OwnSeaGridView view) {

        this.model = model;
        this.view = view;
    }

    public void render() {
        int availableShips = model.numberOfAvailableShips();
        view.setNumberOfAvailableShips(availableShips);
    }
}
