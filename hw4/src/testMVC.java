public class testMVC {
    public static void main (String args[]){
        textFieldView view1 = new textFieldView();
        graphView view2 = new graphView(1000,1000);
        modelData model = new modelData();
        controllerInterface controller = new controllerInterface(model,view2, view1);
    }
}
