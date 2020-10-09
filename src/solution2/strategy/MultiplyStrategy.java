package solution2.strategy;

public class MultiplyStrategy implements ComputeStrategy {
    @Override
    public Double compute(Double operand1, Double operand2) {
        return operand1 * operand2;
    }
}
