package damespiel.view;

import damespiel.controller.DameSpielController;
import damespiel.controller.IDameSpielController;
import damespiel.controller.IDameSpielView;
import damespiel.model.DamePiece;
import damespiel.model.PieceType;
import processing.core.PApplet;
import processing.core.PImage;

public class DameSpielView extends PApplet implements IDameSpielView {

    PImage startScreen;
    IDameSpielController dameSpielController;

    DamePiece[][] damePieces;


    public DameSpielView() {
    setSize( 1000, 1000);
    }
    public void setup() {
        this.dameSpielController = new DameSpielController(this, width, height);

        startScreen = loadImage("src/main/java/damespiel/view/images/StartScreen.png");

        //damespiel/src/main/java/damespiel/view/images
    }
    public void draw() {
        dameSpielController.nextFrame();
    }


    public void keyPressed() {
        if (key == 's') {
            dameSpielController.userInput();
        }
        if (key == 'r') {
            dameSpielController.restartGame();
        }
        if (key == 'q') {
            dameSpielController.exitGame();
        }
    }







    /**
     * @param damePieces
     */
    @Override
    public void onStartPosition(DamePiece[][] damePieces) {

    }

    /**
     *
     */
    @Override
    public void drawGame() {

    }

    /**
     *
     */
    @Override
    public void drawTitleScreen() {
        background(255);
        image(startScreen, 0, 0, 1000, 1000);


    }

    /**
     *
     */
    @Override
    public void drawGameOver() {
        background(0);
        textSize(70);
        text("Game Over !", 350, 300);
        text("Player 1 won", 350, 450);

        textSize(45);
        text(" Press r to restart or q to exit", 280, 650);


    }

    public static void main(String[] args) {
        PApplet.main(DameSpielView.class);
    }
}
