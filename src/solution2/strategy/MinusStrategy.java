package solution2.strategy;

public class MinusStrategy implements ComputeStrategy {
    @Override
    public Double compute(Double operand1, Double operand2) {
        return operand1 - operand2;
    }
}
