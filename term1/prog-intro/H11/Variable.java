package expression;

import java.util.Objects;

public class Variable implements Exp {

    private final String x;

    public Variable(String x) {
        this.x = x;
    }

    public int evaluate(int x) {
        return x;
    }

    public int evaluate(int x, int y, int z) {
        return switch (this.x) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return x;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        final Variable var = (Variable) obj;
        return Objects.equals(this.x, var.x);
    }

    @Override
    public int hashCode() {
        return x.hashCode() * 17;
    }
}
