package damespiel.controller;

import damespiel.model.DamePiece;
import damespiel.model.PieceType;

public interface IDameSpielView {


    public void onStartBoard();

    public void drawGame();
    public void drawTitleScreen();
    public void drawGameOver();



}

