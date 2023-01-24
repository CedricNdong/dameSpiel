package damespiel.view;

import lombok.Data;

import java.io.*;

@Data
public class ScoreTracker {
    private int bestScore;
    private String fileName;

    public ScoreTracker(String fileName) {
        this.fileName = fileName;
        bestScore = 0;
        readScore();
    }

    private synchronized void readScore() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(fileName));
                    String line = reader.readLine();
                    reader.close();
                    if (line != null) {
                        bestScore = Integer.parseInt(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public synchronized void updateScore(int newScore) {
        if (newScore > bestScore) {
            bestScore = newScore;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                        writer.write(Integer.toString(bestScore));
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }

}
