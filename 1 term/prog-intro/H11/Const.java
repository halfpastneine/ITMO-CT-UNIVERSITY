package expression;

import java.util.Objects;

public class Const implements Exp {

    private final int constant;

    public Const(int constant) {
        this.constant = constant;
    }

    public int evaluate(int x) {
        return constant;
    }

    public int evaluate(int x, int y, int z) {
        return constant;
    }

    @Override
    public String toString() {
        return Integer.toString(constant);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        final Const con = (Const) obj;
        return Objects.equals(this.constant, con.constant);
    }

    @Override
    public int hashCode() {
        return constant;
    }
}
