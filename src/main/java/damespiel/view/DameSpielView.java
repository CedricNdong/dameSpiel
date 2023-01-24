package damespiel.view;
import damespiel.model.Cell;
import damespiel.model.DamePlayer;
import damespiel.model.PieceType;
import damespiel.controller.DameSpielController;
import damespiel.controller.IDameSpielController;
import damespiel.controller.IDameSpielView;
import processing.core.PApplet;
import processing.core.PImage;


public class DameSpielView extends PApplet implements IDameSpielView {

    PImage startScreen;
    IDameSpielController dameSpielController;
    PImage WHITE_PIECE,BLACK_PIECE,WHITE_QUEEN,BLACK_QUEEN;
    PImage[][]  gameBoard;

    private boolean onClick = true;
    private boolean move = true;
    private int xPos,yPos, xPosTo, yPosTo; // mouse position
    public DameSpielView() {
        setSize( 1000, 1000);
    }
    public void setup() {
        this.dameSpielController = new DameSpielController(this);
        startScreen = loadImage("src/main/java/damespiel/view/images/StartScreen.png");
        BLACK_PIECE = loadImage("src/main/java/damespiel/view/images/BlackPiece.png");
        WHITE_PIECE = loadImage("src/main/java/damespiel/view/images/WhitePiece.png");
        BLACK_QUEEN = loadImage("src/main/java/damespiel/view/images/BlackQueen.png");
        WHITE_QUEEN = loadImage("src/main/java/damespiel/view/images/WhiteQueen.png");
    }
    public void draw() {
        dameSpielController.nextFrame();
    }

    private void onStartBoard(int whiteScore, int blackScore) {
        background(0);
        //intitial position of the pieces
        gameBoard = new PImage[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i < 3 && (i + j) % 2 == 1) {
                    gameBoard[i][j] = BLACK_PIECE;
                } else if (i > 4 && (i + j) % 2 == 1) {
                    gameBoard[i][j] = WHITE_PIECE;

                }
                else {
                    gameBoard[i][j] = null;
                }
            }
        }


        // Draw the cell
        for (int i = 0; i < 8;i++) {
            for (int j = 0; j < 8; j++) {
                int color = ((i + j) % 2 == 0) ? 255 : 209;
                fill(color, (i + j) % 2 == 0 ? 206 : 139, (i + j) % 2 == 0 ? 158 : 71);
                rect(i * 100 + 100, j * 100 + 40, 100, 100);
            }
        }
        // Draw the pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard[j][i] != null) {
                    image(gameBoard[j][i], i * 100 + 105, j * 100 +45);
                }
            }
        }
        // Display Features wie Score
        fill(255,206,158);
        rect(65,860,875,120);

        fill(0,0,0);
        textSize(50);
        text("SCORE:", 100, 935);
        text(" Player 1:   "+whiteScore, 280, 910);
        text(" Player 2:   "+blackScore, 280, 960);
        text("TURN:", 665, 935);

        // start player ist white
            fill(255,255,255);
            ellipse(840, 920, 75, 75);
    }
    public void keyPressed() {
        if (key == 's') {
            dameSpielController.userInput();
        }
        if (key == 'r') {
            dameSpielController.restartGame();
        }
        if (key == 'q') {
            dameSpielController.exitGame();
        }

    }
    public void mousePressed() {
        move = true;
       if (mouseX > 100 && mouseX < 900 && mouseY > 40 && mouseY < 840) {
           if (onClick) {

               this.xPos = round( mouseY / 100  ) -1;

               this.yPos = round( mouseX / 100 )  -1;

               dameSpielController.mousePosFrom(this.xPos,this.yPos);
               fill(255, 0, 0, 100);
               rect(this.yPos*100+100,this.xPos*100 +40 , 100, 100);
               System.out.println("xPos: " + this.xPos + " yPos: " + this.yPos);
               onClick = false;


           }
           else {
               System.out.println(" the second click should be here and the move should be done");
                this.xPosTo = round( mouseY / 100  ) -1;
                this.yPosTo = round( mouseX / 100 )  -1;
                dameSpielController.mousePosTo(this.xPos,this.yPos,this.xPosTo,this.yPosTo);
               onClick = true;

           }
       }
    }



    @Override
    public void drawGame(int whiteScore, int blackScore, DamePlayer currentPlayer) {
         onStartBoard(whiteScore, blackScore);
         // turn the current player
        if ( currentPlayer.getPlayerPieceType() == PieceType.WHITE_PIECE || currentPlayer.getPlayerPieceType() == PieceType.WHITE_QUEEN){
            fill(255,255,255);
            ellipse(840, 920, 75, 75);
        }else {
            fill(0,0,0);
            ellipse(840, 920, 75, 75);
        }


    }




    @Override
    public void drawTitleScreen() {
        background(255);
        image(startScreen, 0, 0, 1000, 1000);


    }


    @Override
    public void drawGameOver() {
        background(0);
        textSize(70);
        text("Game Over !", 350, 300);
        text("Player 1 won", 350, 450);

        textSize(45);
        text(" Press r to restart or q to exit", 280, 650);


    }


    @Override
    public void drawCanMoveTo(int xto, int yto) {
        if (move){
            move = false;
        }
        fill(0, 255,0, 100);
        rect(yto*100+100,xto*100 +40 , 100, 100);
    }


    @Override
    public void drawMakeMove(Cell [][] board, int xto, int yto, int whiteScore, int blackScore, DamePlayer currentPlayer) {

        if (move){
            drawGame(whiteScore, blackScore, currentPlayer);
            move = false;



        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[j][i] != null) {
                    if (board[j][i].getDamePiece().getPieceType() == PieceType.EMPTY){
                        int color = ((i + j) % 2 == 0) ? 255 : 209;
                        fill(color, (i + j) % 2 == 0 ? 206 : 139, ( i + j) % 2 == 0 ? 158 : 71);
                        rect(i * 100 + 100, j * 100 + 40, 100, 100);
                    }else if (board[j][i].getDamePiece().getPieceType() == PieceType.WHITE_PIECE){
                        image(WHITE_PIECE, i * 100 + 105, j * 100 +45);
                    } else if (board[j][i].getDamePiece().getPieceType() == PieceType.BLACK_PIECE){
                        image(BLACK_PIECE, i * 100 + 105, j * 100 +45);

                    } else if (board[j][i].getDamePiece().getPieceType() == PieceType.WHITE_QUEEN){
                        image(WHITE_QUEEN, i * 100 + 105, j * 100 +45);
                    } else if (board[j][i].getDamePiece().getPieceType() == PieceType.BLACK_QUEEN){
                        image(BLACK_QUEEN, i * 100 + 105, j * 100 +45);
                    }
                }
            }
        }


    }


    public static void main(String[] args){
            PApplet.main(DameSpielView.class);
        }
}
