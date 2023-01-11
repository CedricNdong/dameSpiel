package damespiel.model;


import lombok.Data;

@Data
public class DamePiece {

    private int xPosFrom;
    private int yPosFrom;

    private  PieceType pieceType;

    // build a constructor
    public DamePiece(int xPosFrom, int yPosFrom, PieceType pieceType) {
        this.xPosFrom = xPosFrom;
        this.yPosFrom = yPosFrom;
        this.pieceType = pieceType;
    }

    public void movePiece( int xPosTo, int yPosTo) {
        this.xPosFrom = xPosTo;
        this.yPosFrom = yPosTo;
    }

    public boolean isValideMove() {
        movePiece(this.xPosFrom, this.yPosFrom);

        return true;
    }
    public String toString() {

        if (pieceType == PieceType.WHITE_PIECE) {
            return "O";
        } else if (pieceType == PieceType.BLACK_PIECE) {
            return "X";
        } else if (pieceType == PieceType.WHITE_QUEEN) {
            return "W";
        } else if (pieceType == PieceType.BLACK_QUEEN) {
            return "Q";
        }else {
            return " ";
        }
    }





}
