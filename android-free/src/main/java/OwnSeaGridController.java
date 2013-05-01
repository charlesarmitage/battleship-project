
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
