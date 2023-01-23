package damespiel.controller;


public interface IDameSpielController {
    public void nextFrame();
    public void userInput();



    public void restartGame();

    public void exitGame();


   public  void mousePosFrom(int mouseX, int mouseY);
   public  void mousePosTo(int mouseX,int mouseY,int mouseX1, int mouseY1);
}
