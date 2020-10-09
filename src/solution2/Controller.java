package solution2;

import java.util.Stack;

public class Controller {
    RPNCalculator rpnCalculator;
    Boolean isIllegalCommand = false;

    public Controller(RPNCalculator rpnCalculator) {
        this.rpnCalculator = rpnCalculator;
    }

    private void setIsIllegalCommand(Boolean b) {
        this.isIllegalCommand = b;
    }

    private boolean isDouble(String input) {
        return input.matches("\\d+(\\.\\d*)?");
    }

    public void handleInput(String input) {
        setIsIllegalCommand(false);
        if (isDouble(input)) {
            rpnCalculator.push(Double.valueOf(input));
        } else if (rpnCalculator.supportsOperator(input)) {
            rpnCalculator.calculate(input);
        } else {
            setIsIllegalCommand(true);
        }
    }

    public boolean isIllegalCommand() {
        return isIllegalCommand;
    }

    public Stack<Double> getStack() {
        return rpnCalculator.getStack();
    }

}
