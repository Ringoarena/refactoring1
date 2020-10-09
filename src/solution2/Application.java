package solution2;

import solution2.view.SimpleWindow;

public class Application {
    public static void main(String[] args) {
        RPNCalculator rpnCalculator = new RPNCalculator();
        Controller controller = new Controller(rpnCalculator);
        Boundary boundary = new Boundary(controller);
        new SimpleWindow("RPNCalc", boundary);
    }
}
