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
        if (gameState == GameState.START_SCREEN) {
            dameView.drawTitleScreen();
        }else if (gameState == GameState.DAME_SCREEN) {

            dameView.drawGame();

            // draw all dame pieces
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (dame.getBoard()[j][i] != null && dame.getBoard()[i][j].getDamePiece().getPieceType() == PieceType.WHITE_PIECE) {

                       dameView.drawWhitePiece(dame.getBoard()[j][i].getDamePiece().getXPosFrom()*100 +100 , dame.getBoard()[j][i].getDamePiece().getYPosFrom()*100 +40);
                    }else if (dame.getBoard()[j][i] != null && dame.getBoard()[i][j].getDamePiece().getPieceType() == PieceType.BLACK_PIECE) {
                        dameView.drawBlackPiece(dame.getBoard()[j][i].getDamePiece().getXPosFrom()*100 +100, dame.getBoard()[j][i].getDamePiece().getYPosFrom()*100 +40);
                    }
                    else if (dame.getBoard()[j][i] != null && dame.getBoard()[i][j].getDamePiece().getPieceType() == PieceType.BLACK_QUEEN) {
                       dameView.drawBlackQueen(dame.getBoard()[j][i].getDamePiece().getXPosFrom()*100 +100, dame.getBoard()[j][i].getDamePiece().getYPosFrom()*100 +40);
                    }
                    else if (dame.getBoard()[j][i] != null && dame.getBoard()[i][j].getDamePiece().getPieceType() == PieceType.WHITE_QUEEN) {
                        dameView.drawWhiteQueen(dame.getBoard()[j][i].getDamePiece().getXPosFrom()*100 +100, dame.getBoard()[j][i].getDamePiece().getYPosFrom()*100 +40);
                    }else {
                        dameView.drawEmptyCell(dame.getBoard()[i][j].getDamePiece().getXPosFrom()*i*100, dame.getBoard()[i][j].getDamePiece().getYPosFrom()*j*100);
                    }
                }
            }

        }
        else if (gameState == GameState.GAME_OVER) {
            dameView.drawGameOver();
        }

    }


    @Override
    public void userInput(int xPosFrom, int yPosFrom) {
        if (gameState == GameState.START_SCREEN) {
            gameState = GameState.DAME_SCREEN;
        }
        else if (gameState == GameState.DAME_SCREEN) {

            // move dame piece
            dame.makeMove( xPosFrom,yPosFrom, int xPosTo, int yPosTo);

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
