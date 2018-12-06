import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;


/**
 * A program that allows users to move a car with the mouse.
 */
public class PlaneMover {
     static JLabel droneLabel;
     static Drone drone;
     static PlaneComponent planes;

    public static void main(String[] args) 
    {
    	JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel timeLabel = new JLabel("Time:");
        JLabel scoreLabel = new JLabel("| Score:");
        JLabel gameLabel = new JLabel("Games Played:");
        JLabel lifeLabel = new JLabel("Lives left:");
        Container container = new Container();
        Score score = new Score();
        Stopwatch stopwatch = new Stopwatch();
        
        panel.add(timeLabel);
        panel.add(stopwatch.getTimer());
        panel.add(scoreLabel);
        panel.add(score.getScoreLabel());
        panel.add(gameLabel);
        panel.add(score.getGameLabel());
        panel.add(lifeLabel);
        panel.add(score.getLivesLabel());
        container.setLayout(null);
        frame.add(container);
        frame.add(panel, BorderLayout.NORTH);
        container.setLayout(null);
        frame.add(container);
        
        drone = new Drone(50, FRAME_WIDTH / 2, FRAME_WIDTH);
        container.add(drone.droneLabel);
        
        JButton upButton = new JButton(" Move Up ");
        container.add(upButton);
        upButton.setBounds(200, 600, 100, 20);
        upButton.addActionListener(e -> {
        	drone.moveUpDrone();
        	drone.repaint();
        });

        JButton downButton = new JButton("Move Down");
        container.add(downButton);
        downButton.setBounds(320, 600, 100, 20);
        downButton.addActionListener(e -> {
        	drone.moveDownDrone();
        	drone.repaint();
        });
        
        JButton leftButton = new JButton("Move Left");
        container.add(leftButton);
        leftButton.setBounds(80, 600, 100, 20);
        leftButton.addActionListener(e -> {
        	drone.moveDroneLeft();
        	drone.repaint();
        });
        
        JButton rightButton = new JButton("Move Right");
        container.add(rightButton);
        rightButton.setBounds(440, 600, 100, 20);
        rightButton.addActionListener(e -> {
        	drone.moveDroneRight();
        	drone.repaint();
        });
        planes = new PlaneComponent(2, FRAME_WIDTH, FRAME_HEIGHT); // changed the code so that planes will be drawn dynamically
        container.add(planes);
        planes.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
        final int DELAY = 50;
        //Milliseconds between timer ticks
        Timer t = new Timer(DELAY, event ->
        {
        	boolean checkAllConflicts = false;
            planes.move();
            planes.repaint();
            drone.move();
            drone.checkStatus();
            checkAllConflicts = planes.checkConflict(drone);
            if(checkAllConflicts)
            {
            	resetDronePlane();
            	score.decrementLives();
            	if(score.getLivesLeft() == 0)
            	{
            		stopwatch.stopTime();
            		showQuickErrorDialog(frame, "Lose");
            		stopwatch.resetTime();
                	score.incrementGame();
                	score.resetLives();
            	}
            	panel.removeAll();
            	panel.add(timeLabel);
                panel.add(stopwatch.getTimer());
                panel.add(scoreLabel);
                panel.add(score.getScoreLabel());
                panel.add(gameLabel);
                panel.add(score.getGameLabel());
                panel.add(lifeLabel);
                panel.add(score.getLivesLabel());
            }
            if(score.getLivesLeft() != 0 && stopwatch.isTime() == true)
        	{
        		stopwatch.stopTime();
        		showQuickErrorDialog(frame, "Win");
        		resetDronePlane();
        		stopwatch.resetTime();
            	score.incrementGame();
            	score.updateScore();
            	score.resetLives();
            	panel.removeAll();
            	panel.add(timeLabel);
                panel.add(stopwatch.getTimer());
                panel.add(scoreLabel);
                panel.add(score.getScoreLabel());
                panel.add(gameLabel);
                panel.add(score.getGameLabel());
                panel.add(lifeLabel);
                panel.add(score.getLivesLabel());
        	}
        });
        t.start();
    }
   private static void resetDronePlane() 
   {
		drone.setXY(50, FRAME_WIDTH / 2);
		planes.reset();
   }
   
   public static void showQuickErrorDialog(JFrame parent, String winOrLose) {
		// create and configure a text area - fill it with exception text.
		final JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Sans-Serif", Font.PLAIN, 10));
		textArea.setEditable(false);
		textArea.setText(winOrLose);
		
		// stuff it in a scrollpane with a controlled size.
		JScrollPane scrollPane = new JScrollPane(textArea);		
		scrollPane.setPreferredSize(new Dimension(350, 150));
		
		// pass the scrollpane to the joptionpane.				
		JOptionPane.showMessageDialog(parent, scrollPane, winOrLose+"!", JOptionPane.ERROR_MESSAGE);
	}
   
    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 700;
}

