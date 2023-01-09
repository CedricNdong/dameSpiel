package damespiel.controller;

import damespiel.model.Dame;
import damespiel.view.DameSpielView;
import lombok.Data;


@Data
public class DameSpielController implements IDameSpielController {

    private Dame dame;
    private IDameSpielView dameView;
    private GameState gameState;
    public DameSpielController(DameSpielView dameSpielView, int width, int height) {
       // this.dame = new Dame(width, height);
        this.dameView = dameSpielView;
        this.gameState = GameState.START_SCREEN;
    }

    /**
     *
     */
    @Override
    public void nextFrame() {
        if (gameState == GameState.START_SCREEN) {
            dameView.drawTitleScreen();
        }else if (gameState == GameState.DAME_SCREEN) {
            // draw all dame pieces
            dameView.drawGame();

        }
        else if (gameState == GameState.GAME_OVER) {
            dameView.drawGameOver();
        }

    }


    @Override
    public void userInput() {
        if (gameState == GameState.START_SCREEN) {
            //gameState = GameState.DAME_SCREEN;
            gameState = GameState.GAME_OVER;
        }
        else if (gameState == GameState.DAME_SCREEN) {
            // move dame piece

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
