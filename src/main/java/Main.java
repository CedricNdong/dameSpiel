import damespiel.model.Dame;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int x,y, x2, y2;
        System.out.println("Hello world! and welcome to the dame game");
        System.out.println("WHITE_PIECE = O  , BLACK_PIECE = X BLACK_QUEEN = Q and WHITE_QUEEN = W");
        System.out.println("");
        Scanner scanner = new Scanner(System.in);

        Dame dame = new Dame();

        System.out.println("\n");

        while(true) {
            System.out.println("##>>>  "+dame.getCurrentPlayer().getPlayerPieceType()+ " is playing");
            System.out.println("\nx please");
            x  = scanner.nextInt();
            System.out.println("y please");
            y  = scanner.nextInt();
            System.out.println("x2 please");
            x2 = scanner.nextInt();
            System.out.println("y2 please");
            y2 = scanner.nextInt();
                dame.makeMove(x, y, x2, y2);

        }
    }
}
