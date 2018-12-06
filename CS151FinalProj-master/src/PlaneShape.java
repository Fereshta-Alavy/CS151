import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
   A car that can be moved around.
*/
public class PlaneShape extends PlaneComponent
{
	
	
	public int noseTipX;
	public int tailSideX;
	public int topTailY;
	public int bottomTailY;
	Point2D.Double BodyTopY; 
	Point2D.Double BodyBotY;
	Point2D.Double nosePoint;
	Point2D.Double bottomRightMost;
	Point2D.Double startOfTail;
	
   /**
      Constructs a plane item.
      @param the starting x point
      @param the starting y point
      @param width the width of the entire plane
      @param iW the width of the frame to allow for re enter into frame after exit
   */
   public PlaneShape(int x, int y, int width, int iW, int iH, int iden)
   {
      this.x = x;
      this.y = y;
      this.planeWidth = width;
      this.iconWidth = iW;
      this.iden = iden;
      
      noseTipX = (int) (x+(planeWidth*2/3.0)-52.5);
      tailSideX = (int) (x+planeWidth*2/3+planeWidth*1.125);
      topTailY = (int) (y - planeWidth/10);
      bottomTailY = (int) (y + planeWidth / 4);
      BodyTopY = new Point2D.Double(x+planeWidth*2/3, y + planeWidth / 12);
      BodyBotY = new Point2D.Double(x+planeWidth*2/3, y + planeWidth / 4);
	  nosePoint = new Point2D.Double(x + planeWidth/2.5, y + planeWidth / 6);
	  bottomRightMost = new Point2D.Double(x+planeWidth*2/3+planeWidth*1.125, y + planeWidth / 4);
	  startOfTail = new Point2D.Double(x+planeWidth*2/3 + planeWidth - 1, y + planeWidth / 6);
   }
   
   public int getIden()
   {
	   return iden;
   }

   public void move()
   {
      x-=5;
   }
   
   public void translate(int dx, int dy)
   {
      x += dx;
      y += dy;
   }
   
   public boolean contains(Point2D p)
   {
      return x <= p.getX() && p.getX() <= x + planeWidth 
         && y <= p.getY() && p.getY() <= y + planeWidth / 2;
   }
   
   public boolean inFrame() {
		if(x < -1*((planeWidth*2/3+planeWidth*1.125) - (planeWidth*2/3-planeWidth/4)) - planeWidth/2.5)
			return false;
		return true;
	}
   
   public void reCalc()
   {
	  noseTipX = (int) (x+(planeWidth*2/3.0)-52.5);
      tailSideX = (int) (x+planeWidth*2/3+planeWidth*1.125);
      topTailY = (int) (y - planeWidth/10);
      bottomTailY = (int) (y + planeWidth / 4);
      BodyTopY = new Point2D.Double(x+planeWidth*2/3, y + planeWidth / 12);
      BodyBotY = new Point2D.Double(x+planeWidth*2/3, y + planeWidth / 4.05);
	  nosePoint = new Point2D.Double(x + planeWidth/2.5, y + planeWidth / 6);
	  bottomRightMost = new Point2D.Double(x+planeWidth*2/3+planeWidth*1.125, y + planeWidth / 4);
	  startOfTail = new Point2D.Double(x+planeWidth*2/3 + planeWidth - 1, y + planeWidth / 6);
   }
   
   private int getTopLineY(int xBound) 
   {
	   double slope = (nosePoint.getY()-BodyTopY.getY()) / (nosePoint.getX()-BodyTopY.getX());
	   double b = nosePoint.getY() - slope*nosePoint.getX();
	   return (int)(slope*(xBound + nosePoint.getX()) + b);
   }
   
   private int getBotLineY(int xBound) {
	   double slope = (nosePoint.getY()-BodyBotY.getY()) / (nosePoint.getX()-BodyBotY.getX());
	   double b = nosePoint.getY() - slope*nosePoint.getX();
	   return (int)(slope*(xBound + nosePoint.getX()) + b);
	}
   
   private int getTailLine(int xBound)
   {
	   double slope = (startOfTail.getY()-this.topTailY) / (startOfTail.getX()-this.tailSideX);
	   double b = startOfTail.getY() - slope*startOfTail.getX();
	   return (int)(slope*(xBound + startOfTail.getX()) + b);
   }
   
   public boolean checkConflictToDrone(Drone x) 
   {
	   reCalc();
	   boolean conflict = false;
	   int lineY = getTopLineY(x.getXBound() - noseTipX);
	   if(this.noseTipX <= x.getXBound() && x.getXBound() <= BodyTopY.getX() && lineY >= x.getY() && lineY <= x.getYBound())
	   {
		   conflict = true;
	   }
	   lineY = getBotLineY(x.getXBound() - noseTipX);
	   if(this.noseTipX <= x.getXBound() && x.getXBound() <= BodyBotY.getX() && lineY <= x.getY() && lineY + 105 >= x.getYBound())
	   {
		   conflict = true;
	   }
	   else if(this.BodyTopY.getX() <= x.getXBound() && this.startOfTail.getX() >= x.getXBound() && this.BodyTopY.getY() <= x.getYBound() && this.BodyTopY.getY() >= x.getY())
	   {
		   conflict = true;
	   }
	   else if(this.BodyBotY.getX() <= x.getXBound() && this.bottomRightMost.getX() >= x.getX() && this.BodyBotY.getY() >= x.getY() && this.BodyBotY.getY() <= x.getYBound())
	   {
		   conflict = true;
	   }
	   else if(this.startOfTail.getX() <= x.getX() && this.tailSideX >= x.getX() && this.topTailY <= x.getYBound() && x.getY() <= this.topTailY)
	   {
		   conflict = true;
	   }
	   lineY = getTailLine(x.getXBound() - (int)startOfTail.getX());
	   if(this.startOfTail.getX() <= x.getXBound() && x.getXBound() <= tailSideX && lineY <= x.getYBound() && x.getY() <= this.topTailY)
	   {
		   conflict = true;
	   }
		   //else if(x+planeWidth*2/3 + planeWidth - 1 )
		//   conflict = true;
	   return conflict;
	}



public boolean checkConflict(PlaneShape p) 
   {
	   // x-tip of nose equation = x+(planeWidth*2/3.0)-52.5;
	   // x-tail of plane is = x+planeWidth*2/3+planeWidth*1.125
	   
	   // y-top of tail is = y - planeWidth/10
	   // y-bottom of tail is = y + planeWidth / 4
	   
	   boolean conflict = false;
	   if(this.noseTipX <= p.tailSideX && this.tailSideX >= p.tailSideX && this.topTailY <= p.topTailY
			   && this.bottomTailY >= p.topTailY)
		   conflict = true;
	   else if(this.noseTipX <= p.noseTipX && this.tailSideX >= p.noseTipX && this.topTailY >= p.topTailY
			   && this.topTailY <= p.bottomTailY)
		   conflict = true;
	   else if(this.noseTipX <= p.noseTipX && this.tailSideX >= p.noseTipX && this.topTailY >= p.topTailY
			   && this.topTailY <= p.bottomTailY)
		   conflict = true;
	   else if(this.noseTipX <= p.noseTipX && this.tailSideX >= p.noseTipX && this.topTailY <= p.topTailY
			   && this.bottomTailY >= p.topTailY)
		   conflict = true;
	   else if(this.noseTipX < p.tailSideX && this.tailSideX >= p.tailSideX && this.topTailY <= p.bottomTailY
			   && this.bottomTailY >= p.bottomTailY)
		   conflict = true;
	   if(this.noseTipX >= p.noseTipX &&  this.noseTipX <= p.tailSideX && this.topTailY <= p.topTailY && 
			   this.topTailY >= p.bottomTailY)
		   conflict = true;
	   return conflict;
   }

   public void draw(Graphics2D g2)
   {   
	  // if(x < -1*((planeWidth*2/3+planeWidth*1.125) - (planeWidth*2/3-planeWidth/4)) - planeWidth/2.5)
		//   x = iconWidth;
	   //Rectangle2D.Double body2 = new Rectangle2D.Double(x, y, planeWidth - 1, planeWidth / 6);
	   Rectangle2D.Double body = new Rectangle2D.Double(x+planeWidth*2/3, y + planeWidth / 12, planeWidth - 1, planeWidth / 6);
	   //Arc2D.Double dome = new Arc2D.Double(x+planeWidth*2/3, y, planeWidth/4, planeWidth/6, 0, 180, 0);
	   Point2D.Double noseOfPlane = new Point2D.Double(x + planeWidth/2.5, y + planeWidth / 6);
	   Point2D.Double bottomLeftPointRect = new Point2D.Double(x+planeWidth*2/3, y + planeWidth / 4.05);
	   Point2D.Double topLeftPointRect = new Point2D.Double(x+planeWidth*2/3, y + planeWidth / 12);
	   Point2D.Double tailExtensionOfRect = new Point2D.Double(x+planeWidth*2/3+planeWidth*1.125, y + planeWidth / 4);
	   Point2D.Double rightTopRect = new Point2D.Double(x+planeWidth*2/3+planeWidth, y + planeWidth / 12);
	   Point2D.Double bottomLeftMostRect = new Point2D.Double(x+planeWidth*2/3+planeWidth*2/2, y + planeWidth / 4);
	   Point2D.Double tipOfTail = new Point2D.Double(x+planeWidth*2/3+planeWidth*1.125, y - planeWidth/10); 
	   Line2D.Double connectorLineFromBodyToTipOfTail = new Line2D.Double(rightTopRect,tipOfTail);
	   Line2D.Double backTailLine = new Line2D.Double(tipOfTail,tailExtensionOfRect);
	   Line2D.Double bottomTail = new Line2D.Double(tailExtensionOfRect,bottomLeftMostRect);
	   Line2D.Double topNose = new Line2D.Double(noseOfPlane,topLeftPointRect);
	   Line2D.Double bottomNose = new Line2D.Double(noseOfPlane,bottomLeftPointRect);
	   
	   g2.draw(connectorLineFromBodyToTipOfTail);
	   g2.draw(backTailLine);
	   g2.draw(bottomTail);
	   g2.draw(bottomNose);
	   g2.draw(topNose);
	   //g2.draw(dome);
	   g2.draw(body);
   }
   
   private int x;
   private int y;
   private int planeWidth;
   private int iconWidth;
   private int iden;



}
