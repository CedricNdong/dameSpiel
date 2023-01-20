package damespiel.controller;

import damespiel.model.DamePiece;
import damespiel.model.PieceType;

import java.util.ArrayList;

public interface IDameSpielView {


    public void onStartBoard();

    public void drawGame();
    public void drawTitleScreen();
    public void drawGameOver();
    public void drawCanMoveTo(int xto,int yto);

}

