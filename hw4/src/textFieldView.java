import javax.swing.*;

public class textFieldView {
    public JTextField num1;
    public JTextField num2;
    public JTextField num3;
    public JTextField num4;
    public JButton button;
    public textFieldView(){
        super();
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.add(panel);
        num1 = new JTextField(20);
        panel.add(num1);
        num2 = new JTextField(20);
        panel.add(num2);
        num3 = new JTextField(20);
        panel.add(num3);
        num4 = new JTextField(20);
        panel.add(num4);
        button = new JButton("Done");
        panel.add(button);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
