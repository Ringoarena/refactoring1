package solution1.strategy;

public class ModulusStrategy implements ComputeStrategy {
    @Override
    public Double compute(Double operand1, Double operand2) {
        return operand1 % operand2;
    }
}
