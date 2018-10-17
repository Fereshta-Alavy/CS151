import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class controllerInterface{
    private modelData model;
    private graphView graph;
    private textFieldView text;

    public controllerInterface(modelData model, graphView graph , textFieldView text){
        this.model = model;
        this.graph= graph;
        this.text= text;
        this.text.button.addActionListener(e -> changeGraph());
    }

    private void changeGraph(){
        this.model.numberArray[0] = Integer.parseInt(this.text.num1.getText());
        this.model.numberArray[1] = Integer.parseInt(this.text.num2.getText());
        this.model.numberArray[2] = Integer.parseInt(this.text.num3.getText());
        this.model.numberArray[3] = Integer.parseInt(this.text.num4.getText());
        this.graph.setLine1y(this.model.numberArray[0]);
        this.graph.setLine2y(this.model.numberArray[1]);
        this.graph.setLine3y(this.model.numberArray[2]);
        this.graph.setLine4y(this.model.numberArray[3]);
        this.graph.repaint();
    }
}

