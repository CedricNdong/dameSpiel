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
    public void moveTo(int xPosTo, int yPosTo) {

        System.out.println(" i am movinng ");

    };








}
