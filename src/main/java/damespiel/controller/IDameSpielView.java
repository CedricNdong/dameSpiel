package damespiel.controller;

import damespiel.model.Cell;
import damespiel.model.DamePiece;
import damespiel.model.DamePlayer;
import damespiel.model.PieceType;

import java.util.ArrayList;

public interface IDameSpielView {


    public void drawGame(int whiteScore, int blackScore,DamePlayer currentPlayer);

    public void drawTitleScreen();
    public void drawGameOver();
    public void drawCanMoveTo(int xto,int yto);
    public void drawMakeMove(Cell[][] board, int xto, int yto, int whiteScore, int blackScore, DamePlayer currentPlayer);

}

