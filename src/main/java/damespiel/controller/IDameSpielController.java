package damespiel.controller;


public interface IDameSpielController {
    public void nextFrame();
    public void userInput();



    public void restartGame();

    public void exitGame();


   public  void mousePos(int mouseX, int mouseY);
}
