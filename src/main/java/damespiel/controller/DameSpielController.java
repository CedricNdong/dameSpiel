package damespiel.controller;

import damespiel.model.Dame;
import damespiel.model.PieceType;
import damespiel.view.DameSpielView;
import lombok.Data;


@Data
public class DameSpielController implements IDameSpielController {

    private Dame dame;
    private IDameSpielView dameView;
    private GameState gameState;
    private boolean isEvent = true;
    public DameSpielController(DameSpielView dameSpielView) {
        this.dame = new Dame();
        this.dameView = dameSpielView;
        this.gameState = GameState.START_SCREEN;
    }

    /**
     *
     */
    @Override
    public void nextFrame() {
        if (isEvent) {

            switch (gameState) {
                case START_SCREEN -> {
                    dameView.drawTitleScreen();
                    isEvent= false;
                }
                case DAME_SCREEN -> {
                    dameView.drawGame();
                    isEvent = false;
                }
                case GAME_OVER -> {
                    dameView.drawGameOver();
                }
            }
        }
    }


    @Override
    public void userInput() {
        if (gameState == GameState.START_SCREEN) {
            isEvent = true;
            gameState = GameState.DAME_SCREEN;

        }
        else if (gameState == GameState.DAME_SCREEN) {

            // mouss X Y  und



        }

    }
    public void mousePos(int xPos, int yPos) {
        // check if the board  at the position not null


            if (dame.getBoard()[xPos][yPos] != null) {
              dame.possibleMove(xPos, yPos);

                // dameView.drawCanMoveTo(dame.getTo1().get(0), dame.getTo1().get(1));


                 dameView.drawCanMoveTo(dame.getTo2().get(0), dame.getTo2().get(1));

            System.out.println("value is null");

        }
            else {
                dameView.drawGame();
            }



    }

    public void restartGame() {
         gameState = GameState.DAME_SCREEN;

    }
    public void exitGame() {
        System.out.println("exit game");
        System.exit(0);
    }
}
