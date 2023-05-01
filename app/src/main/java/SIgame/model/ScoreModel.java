package SIgame.model;

import java.io.*;

public class ScoreModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int currentScore;
    private int highScore;

    public ScoreModel() {
        this.currentScore = 0;
        this.highScore = readHighScoreFromFile();
    }

    public void gainPoint() {
        currentScore += 10;
        if (currentScore > highScore) {
            highScore = currentScore;
            saveHighScoreToFile();
        }
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getHighScore() {
        return highScore;
    }

    private void saveHighScoreToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("high_score.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(highScore);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int readHighScoreFromFile() {
        int highScore = 0;
        try {
            FileInputStream fileIn = new FileInputStream("high_score.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            highScore = (int) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return highScore;
    }
}

