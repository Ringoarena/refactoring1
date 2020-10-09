package solution2.strategy;

public class PowerOfStrategy implements ComputeStrategy {
    @Override
    public Double compute(Double operand1, Double operand2) {
        return Math.pow(operand1, operand2);
    }
}
