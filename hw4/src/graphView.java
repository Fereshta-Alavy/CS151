import javax.swing.*;
import java.awt.*;

public class graphView extends JFrame {
    private int line1y ;
    private int line2y ;
    private int line3y ;
    private int line4y ;

    public graphView(int width ,int height){
        line1y = 0;
        line2y = 0;
        line3y = 0;
        line4y = 0;

        setTitle("Graph");
        setSize(width,height);
        setVisible(true);


    }

    public void setLine1y(int line1y) {
        this.line1y = line1y;
    }

    public void setLine2y(int line2y) {
        this.line2y = line2y;
    }

    public void setLine3y(int line3y) {
        this.line3y = line3y;
    }

    public void setLine4y(int line4y) {
        this.line4y = line4y;
    }

    public void paint(Graphics g){
        g.drawLine(50, 0, 50, line1y);
        g.drawLine(100, 0, 100, line2y);
        g.drawLine(150, 0, 150, line3y);
        g.drawLine(200, 0, 200, line4y);


    }







}
