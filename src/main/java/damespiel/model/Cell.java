package damespiel.model;

import lombok.Data;

@Data
public class Cell {
    private int xCellPos;
    private int yCellPos;
    private DamePiece damePiece;

    public Cell(int xCellPos, int ySpacePos) {
        this.xCellPos = xCellPos;
        this.yCellPos = ySpacePos;
    }


    public String toString() {
        if (damePiece != null) {
            return damePiece.toString();
        } else if (damePiece == null) {

            return " ";
        }
        return " ";

    }


    public boolean isCellEmpty() {
        if (damePiece.getPieceType() == PieceType.EMPTY) {
            return true;
        } else {
            return false;
        }
    }




}