package solution1;

import solution1.view.SimpleWindow;
import solution1.view.UserInterface;

public class Application {

    public static void main(String[] args) {
        RPNCalculator rpnCalculator = new RPNCalculator();
        UserInterface UI = new SimpleWindow("Calculator");
        new Controller(rpnCalculator, UI);
    }
}
