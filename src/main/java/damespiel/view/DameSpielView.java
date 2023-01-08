package damespiel.view;

import damespiel.controller.DameSpielController;
import damespiel.controller.IDameSpielController;
import damespiel.controller.IDameSpielView;
import damespiel.model.DamePiece;
import processing.core.PApplet;
import processing.core.PImage;

public class DameSpielView extends PApplet implements IDameSpielView {

    PImage startScreen;
    IDameSpielController dameSpielController;
    boolean onPiece = false;

    PImage[][]  gameBoard;


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







    @Override
    public void onStartBoard() {
        background(0);

        for (int i = 0; i < 8;i++) {
            for (int j = 0; j < 8; j++) {
                if((i+j)%2 == 0) {
                    fill(255,206,158);
                } else {
                    fill(209,139,71);
                }
                rect(i*100 + 100,j*100 +40,100,100);





/*

                if (gameBoard[i][j] != null) {
                    image(gameBoard[j][i], i*100, j*100, 100, 100);
                }
                if (onPiece){
                    if validMove(a,b,c,d,damePieces){
                        fill(255,0,0,100);
                        rect(i*100,j*100,100,100);
                    }
                    if (j==a && j==b && damePieces[i][j] != null){
                        fill(0,0,255,100);
                        rect(i*100,j*100,100,100);
                    }
                    }



 */


                }
        }

        fill(255,206,158);
        rect(65,850,875,120);

        fill(0,0,0);
        textSize(50);
        text("Score !", 100, 920);



    }


    /**
     *
     */
    @Override
    public void drawGame() {
        onStartBoard();

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
