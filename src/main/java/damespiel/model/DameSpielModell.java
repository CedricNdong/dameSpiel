package damespiel.model;

import lombok.Data;

import java.util.Scanner;

@Data
public class DameSpielModell {
    Scanner scanner = new Scanner(System.in);


    private DameBoard dameBoard;
    public DameSpielModell() {
        dameBoard = new DameBoard();{

        }
    }





    public void startGame() {
        dameBoard.gameInitBoard();

        System.out.println("Please enter the x position of the piece you want to move");
        int valid = scanner.nextInt();

        if ( valid == 1){

            dameBoard.movePiece(5, 0, 4, 1, dameBoard.getBoard());


        }

        System.out.println("move work");


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

