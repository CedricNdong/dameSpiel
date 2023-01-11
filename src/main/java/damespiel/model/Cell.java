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
        return damePiece.toString();
    }


    public boolean isCellEmpty() {
        return damePiece == null;
    }




}