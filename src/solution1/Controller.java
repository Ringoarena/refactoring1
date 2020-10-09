package solution1;

import solution1.view.UserInterface;

public class Controller {
    RPNCalculator rpnCalculator;
    UserInterface ui;
    String input;
    boolean error;

    public Controller(RPNCalculator rpnCalculator, UserInterface ui) {
        this.rpnCalculator = rpnCalculator;
        this.ui = ui;
        run();
    }

    private void run() {
        boolean timeToExit = false;
        while (!timeToExit) {
            render();
            getInput();
            timeToExit = handleInput();
        }
        ui.exit();
    }

    private void render() {
        if (rpnCalculator.stackIsEmpty()) {
            renderReadme();
        } else {
            renderOutput();
        }
        if (error) {
            renderErrorMessage();
        }
    }

    private void renderReadme() {
        ui.clear();
        ui.addString("[empty]\n");
        ui.addString("Commands: q=quit c=clear + - * / number");
    }

    private void renderOutput() {
        ui.clear();
        ui.addString(rpnCalculator.getOutput());
    }

    private void renderErrorMessage() {
        ui.addString("\nIllegal command");
    }

    private void getInput() {
        error = false;
        input = ui.getString().trim();
    }

    private boolean handleInput() {
        if (isDouble(input)) {
            rpnCalculator.push(Double.valueOf(input));
        } else if (rpnCalculator.supportsOperator(input)) {
            rpnCalculator.calculate(input);
        } else if (input.equalsIgnoreCase("c")) {
            rpnCalculator.clear();
        } else if (input.equalsIgnoreCase("q")) {
            return true;
        } else {
            error = true;
        }
        return false;
    }

    private boolean isDouble(String input) {
        try {
            Double.valueOf(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
