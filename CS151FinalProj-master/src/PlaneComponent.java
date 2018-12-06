import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.Timer;

import java.util.*;

/**
A component that shows a car.
*/
public class PlaneComponent extends JComponent
{
	private PlaneShape[] plane;
	//private ArrayList<PlaneShape> plane2; // if I try to make the number of planes appearing dynamic, the program crashes
											// I think it is because arrayList size will be changed later, so it cant work
	Random rand = new Random();
	private int hold;
	private int frameW, frameH;
	
	public PlaneComponent()
	{
		
	}

	public PlaneComponent(int numPlane, int maxW, int maxH)
	{
		plane = new PlaneShape[numPlane];
		//plane2 = new ArrayList<>();
		frameW = maxW;
		frameH = maxH-100;
		for(int i = 0; i < numPlane; i++) // the total number of planes to be added to screen
		{
			int x = frameW + rand.nextInt(100); // the starting X-point of the plane is off the screen
			int y = rand.nextInt(frameH);		// the starting Y-point is random on the screen
			if(i == 0)							// if its the first plane being added to the screen
			{
				plane[i] = new PlaneShape(x,y,200,maxW, maxH, i);
				//plane2.add(new PlaneShape(x,y,200,maxW, maxH, i));
			}
			else								// if there are other planes on the screen, then you need to check for collisions
			{
				boolean chk = true;				
				PlaneShape addMaybe = null;
				while(chk == true)				// keep going until you find a spot where there wont be a collision for the planes
				{
					x = frameW + rand.nextInt(100); // random X and Y values each time you loop through trying to find right spot
					y = rand.nextInt(frameH);
					addMaybe = new PlaneShape(x, y, 200, frameW, frameH, i);
					int hold = 0;				// go through all the planes already on the screen to see if drawing the new plane will cause collision
					while(hold < i)
					{
						if(hold != i)			// check all the other planes, dont check collision with this
						{
							boolean chk3 = true;	
							boolean chk2 = true;
							chk3 = plane[hold].checkConflict(addMaybe);	// check other plane with the new plane that might get added
							chk2 = addMaybe.checkConflict(plane[hold]);	// check this plane with the other plane
							//chk3 = plane2.get(hold).checkConflict(addMaybe);
							//chk2 = addMaybe.checkConflict(plane2.get(hold));
							if(chk3 == false && chk2 == false)	// if there are no collisions recorded, then collisions can be false
							{
								chk = false;
							}
							else								// else there is a collision with one of the planes, so retry with new X and Y values
							{
								chk = true;
								hold = i;
							}
						}
						hold++;
					}
				}
				plane[i] = addMaybe;
				//plane2.add(addMaybe);
			}
		}
	}
	
	protected void checkPlaneStatus(PlaneShape checkMe) 
	{
		if(!checkMe.inFrame())
		{
			boolean chk = true;
			PlaneShape addMaybe = null;
			while(chk == true)
			{
				int x = frameW + rand.nextInt(100) + rand.nextInt(100);
				int y = rand.nextInt(frameH);
				addMaybe = new PlaneShape(x, y, 200, frameW, frameH, checkMe.getIden());
				int hold = 0;
				while(hold < plane.length) //while(hold < plane2.size())
				{
					if(hold != checkMe.getIden())
					{
						boolean chk3 = true;
						boolean chk2 = true;
						chk3 = plane[hold].checkConflict(addMaybe);
						chk2 = addMaybe.checkConflict(plane[hold]);
						//chk3 = plane2.get(hold).checkConflict(addMaybe);
						//chk2 = addMaybe.checkConflict(plane2.get(hold));
						if(chk3 == false && chk2 == false)
						{
							chk = false;
						}
						else
						{
							chk = true;
							hold = plane.length;
							//hold = plane2.size();
						}
					}
					hold++;
				}
			}
			plane[checkMe.getIden()] = addMaybe;
			//plane2.remove(checkMe.getIden());
			//plane2.add(checkMe.getIden(), addMaybe);
			
		}
	} 
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		for (int n = 0; n < plane.length; n++) 
			plane[n].draw(g2);
		//for (int n = 0; n < plane2.size(); n++) 
		//		plane2.get(n).draw(g2);
	}

	public void move() {
		for(PlaneShape x : plane) //for(PlaneShape x : plane2)
		{
			x.move();
			checkPlaneStatus(x);
		}
	}

	public boolean checkConflict(Drone x)
	{
		for(PlaneShape theCurrPlane : plane)
		{
			boolean planeDrone = theCurrPlane.checkConflictToDrone(x);	// check other plane with the new plane that might get added
			//boolean dronePlane = x.checkConflict(theCurrPlane);	// check this plane with the other plane
			if(planeDrone == true)// || dronePlane == true)
			{
				return true;
			}
			hold++;
		}
		return false;
	}

	public void reset() 
	{
		for(int i = 0; i < plane.length; i++) // the total number of planes to be added to screen
		{
			int x = frameW + rand.nextInt(100); // the starting X-point of the plane is off the screen
			int y = rand.nextInt(frameH);		// the starting Y-point is random on the screen
			if(i == 0)							// if its the first plane being added to the screen
			{
				plane[i] = new PlaneShape(x,y,200, frameW, frameH, i);
				//plane2.add(new PlaneShape(x,y,200,maxW, maxH, i));
			}
			else								// if there are other planes on the screen, then you need to check for collisions
			{
				boolean chk = true;				
				PlaneShape addMaybe = null;
				while(chk == true)				// keep going until you find a spot where there wont be a collision for the planes
				{
					x = frameW + rand.nextInt(100); // random X and Y values each time you loop through trying to find right spot
					y = rand.nextInt(frameH);
					addMaybe = new PlaneShape(x, y, 200, frameW, frameH, i);
					int hold = 0;				// go through all the planes already on the screen to see if drawing the new plane will cause collision
					while(hold < i)
					{
						if(hold != i)			// check all the other planes, dont check collision with this
						{
							boolean chk3 = true;	
							boolean chk2 = true;
							chk3 = plane[hold].checkConflict(addMaybe);	// check other plane with the new plane that might get added
							chk2 = addMaybe.checkConflict(plane[hold]);	// check this plane with the other plane
							//chk3 = plane2.get(hold).checkConflict(addMaybe);
							//chk2 = addMaybe.checkConflict(plane2.get(hold));
							if(chk3 == false && chk2 == false)	// if there are no collisions recorded, then collisions can be false
							{
								chk = false;
							}
							else								// else there is a collision with one of the planes, so retry with new X and Y values
							{
								chk = true;
								hold = i;
							}
						}
						hold++;
					}
				}
				plane[i] = addMaybe;
				//plane2.add(addMaybe);
			}
		}
	}

	
}