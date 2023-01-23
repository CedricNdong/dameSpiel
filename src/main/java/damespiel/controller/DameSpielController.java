package damespiel.controller;

import damespiel.model.Dame;
import damespiel.model.PieceType;
import damespiel.view.DameSpielView;
import lombok.Data;

import java.util.List;


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
                    int whiteScore = dame.getDamePlayers()[0].getScore();
                    int blackScore = dame.getDamePlayers()[1].getScore();
                    dameView.drawGame(whiteScore, blackScore);
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
    public void mousePosFrom(int xPos, int yPos) {
        // check if the board  at the position not null

            if (dame.getBoard()[xPos][yPos] != null) {

                List<int[]> moveTo = dame.possibleMoves(xPos, yPos);
                for (int[] ints : moveTo) {
                    dameView.drawCanMoveTo(ints[0], ints[1]);
                }

               // dameView.drawCanMoveTo(xPos, yPos);

            } else {
                System.out.println("value is null");
                dameView.drawGame(dame.getDamePlayers()[0].getScore(), dame.getDamePlayers()[1].getScore());
            }

    }

    public void mousePosTo(int xPos, int yPos,int xPosTo,int yPosTo) {
        // check if the board  at the position not null


        if (dame.getBoard()[xPos][yPos] != null && dame.getBoard()[xPosTo][yPosTo] != null) {


            dame.makeMove(xPos, yPos, xPosTo, yPosTo);
            dameView.drawMakeMove(dame.getBoard(),xPosTo, yPosTo,dame.getDamePlayers()[0].getScore(), dame.getDamePlayers()[1].getScore());

        } else {
            System.out.println("select a possible move position");
            dameView.drawGame(dame.getDamePlayers()[0].getScore(), dame.getDamePlayers()[1].getScore());
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
