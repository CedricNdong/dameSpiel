package damespiel.controller;

import damespiel.model.DamePiece;
import damespiel.model.PieceType;

public interface IDameSpielView {


    public void onStartBoard();

    public void drawGame();
    public void drawTitleScreen();
    public void drawGameOver();


    void drawBlackPiece(int i, int j);

    void drawWhitePiece(int i, int j);

    void drawBlackQueen(int i, int j);

    void drawWhiteQueen(int i, int j);

    void drawEmptyCell(int i, int j);
}

