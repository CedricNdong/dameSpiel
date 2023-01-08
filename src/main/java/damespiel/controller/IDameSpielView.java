package damespiel.controller;

import damespiel.model.DamePiece;
import damespiel.model.PieceType;

public interface IDameSpielView {

    public void onStartPosition(DamePiece[][] damePieces);
    public void drawGame();
    public void drawTitleScreen();
    public void drawGameOver();



}

