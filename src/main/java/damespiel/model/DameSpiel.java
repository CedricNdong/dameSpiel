package damespiel.model;

public class DameSpiel {


    private DameBoard dameBoard;
    public DameSpiel() {
        dameBoard = new DameBoard();{

        }
    }





    public void startGame() {
        dameBoard.gameInitBoard();
    }

    /**
     * @param xPosFrom
     * @param yPosFrom
     * @param xPosTo
     * @param yPosTo
     */



    public boolean isValideMove(int xPosFrom, int yPosFrom, int xPosTo, int yPosTo) {

        return false;


    }





}

