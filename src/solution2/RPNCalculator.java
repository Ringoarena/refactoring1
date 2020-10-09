package solution2;

import solution2.strategy.*;

import java.util.*;

public class RPNCalculator {
    Stack<Double> stack;
    Map<String, ComputeStrategy> strategies;

    public RPNCalculator() {
        stack = new Stack<>();
        strategies = new HashMap<>();
        strategies.put("+", new PlusStrategy());
        strategies.put("-", new MinusStrategy());
        strategies.put("*", new MultiplyStrategy());
        strategies.put("/", new DivideStrategy());
        strategies.put("^", new PowerOfStrategy());
        strategies.put("%", new ModulusStrategy());
    }

    private Optional<Double> compute(ComputeStrategy strategy) {
        Optional<Double> result = Optional.empty();
        if (stack.size() >= 2) {
            double operand2 = stack.pop();
            double operand1 = stack.pop();
            result = Optional.of(strategy.compute(operand1, operand2));
        }
        return result;
    }

    public boolean supportsOperator(String operator) {
        return strategies.containsKey(operator);
    }

    public void calculate(String operator) {
        ComputeStrategy strategy = strategies.get(operator);
        Optional<Double> result = compute(strategy);
        if (result.isPresent()) {
            push(result.get());
        }
    }

    public void push(Double d) {
        stack.push(d);
    }

    public Stack<Double> getStack() {
        return stack;
    }

}
