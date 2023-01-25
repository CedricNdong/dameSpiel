import damespiel.controller.DameSpielController;
import damespiel.model.Dame;
import damespiel.view.DameSpielView;
import processing.core.PApplet;


public class Main {

    public static void main(String[] args) {
        var dame = new Dame();
        var dameController = new DameSpielController(dame);
        var dameView = new DameSpielView();

        // Connect controller and view
        dameController.setDameView(dameView);
        dameView.setDameSpielController(dameController);

        // Starts the processing application
        PApplet.runSketch(new String[]{"DameSpielView"}, dameView);

    }
}