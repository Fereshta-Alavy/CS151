import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.*;

public class Stopwatch extends JComponent {

    private JLabel label;
    private Timer timer;
    private int minute;
    private int second;
    private int count;

    public Stopwatch() {
        minute = 1;
        second = 30;
        count = 0;
        label = new JLabel();
        activate();
    }
    
    public int retCount()
    {
    	return count;
    }

    public void resetTime() 
    {
    	timer.stop();
    	minute = 1;
        second = 30;
        count = 0;
        activate();
	}
    
    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public JLabel getTimer() {
        return label;
    }

    public boolean isTime() {
        return count % 3 == 0 && count != 0;
    }

    public void activate() {
        int delay = 1000;
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (count == 3) {
                    count = 0;
                    minute = 1;
                    second = 30;
                }

                label.setText(minute + ":" + String.format("%02d", second));
                second--;

                if (second == 0) {
                    count++;
                    minute--;
                    second = 59;
                }

                if (second == 30) {
                    count++;
                }
            }
        };
        timer = new Timer(delay, listener);
        timer.start();
    }

	public void stopTime() {
		timer.stop();
		
	}

	

}