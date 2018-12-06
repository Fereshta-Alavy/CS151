import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Score extends JComponent {
    
    private JLabel labelScore, labelGame, labelLives;
    private int score;
    private int gameCount;
    private int lives;

    public Score() {
        score = 0;
        gameCount = 0;
        lives = 4;
        labelScore = new JLabel(""+score + " |");
        labelGame = new JLabel("" + gameCount + " |");
        labelLives = new JLabel("" + lives);
    }

    public int getScore() {
        return score;
    }

    public int getGameCount() {
        return gameCount;
    }

    public JLabel getScoreLabel() {
        return labelScore;
    }
    public JLabel getLivesLabel() {
        return labelLives;
    }
    
    public JLabel getGameLabel() {
        return labelGame;
    }

    public void updateScore() {
        score++;
        labelScore = new JLabel("" + score + " |");
    }
    
    public int getLivesLeft()
    {
    	return lives;
    }
    
    public void resetLives()
    {
    	lives = 4; 
        labelLives = new JLabel("" + lives);
    }

    public void decrementLives()
    {
    	lives--;
        labelLives = new JLabel("" + lives);
    }
    
    public void incrementGame() {
        gameCount++;
        labelGame = new JLabel("" + gameCount + " |");
    }


}