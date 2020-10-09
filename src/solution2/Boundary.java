package solution2;

import java.util.Stack;

public class Boundary {
    Controller controller;

    public Boundary(Controller controller) {
        this.controller = controller;
    }

    public void handleInput(String string) {
        controller.handleInput(string);
    }

    public Boolean isIllegalCommand() {
        return controller.isIllegalCommand();
    }

    public Stack<Double> getStack() {
        return controller.getStack();
    }

}
