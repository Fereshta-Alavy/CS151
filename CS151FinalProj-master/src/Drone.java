
import javax.swing.*;
import java.awt.*;

public class Drone extends JComponent {
    ImageIcon image;
    int x, y, frameW;
    JLabel droneLabel;

    public Drone(int x, int y, int w) {
        this.x = x;
        this.y = y;
        frameW = w;
        image = new ImageIcon("Drone.jpg");
        droneLabel = new JLabel(image);
        droneLabel.setBounds(x, y, 200, 100);
    }
    public void moveUpDrone(){
        int yC = droneLabel.getY();
        yC -= 10;
        y -= 10;
        droneLabel.setBounds(x,yC,200,100);
        
    }
    public void moveDownDrone(){
        int yC = droneLabel.getY();
        yC += 10;
        y += 10;
        droneLabel.setBounds(x,yC,200,100);

    }
    
    public boolean checkConflict(PlaneShape theCurrPlane) {
		
    	return false;
	}
    
    public void setXY(int x1, int y1)
    {
    	y = y1;
    	x = x1;
    	droneLabel.setBounds(x, y, 200, 100);
    }
    public int getX()
    {
    	return x;
    }
    
    public int getY()
    {
    	return y;
    }
    public int getXBound()
    {
    	return x+200;
    }
    public int getYBound()
    {
    	return y+100;
    }
	public void move() 
	{
		x+=1;
		droneLabel.setBounds(x, y, 200, 100);
	}
	public void moveDroneLeft() 
	{
		x-=10;
		droneLabel.setBounds(x, y, 200, 100);
	}
	public void moveDroneRight() {
		x+=10;
		droneLabel.setBounds(x, y, 200, 100);		
	}
	public void checkStatus() 
	{
		if(getXBound() > frameW)
			x = 50;
		droneLabel.setBounds(x, y, 200, 100);
	}
	
}

