package expression;

import java.util.Objects;

public abstract class BinOperation implements Exp {

    protected Exp f;
    protected Exp s;

    public BinOperation(Exp f, Exp s) {
        this.f = f;
        this.s = s;
    }

    protected abstract int getAns(int a, int b);

    protected abstract char getBinOperator();

    public int evaluate(int x) {
        return (getAns(f.evaluate(x), s.evaluate(x)));
    }

    public int evaluate(int x, int y, int z) {
        return getAns(f.evaluate(x, y, z), s.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return '(' + f.toString() + ' ' + getBinOperator() + ' ' + s.toString() + ')';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        final BinOperation bin = (BinOperation) obj;
        return Objects.equals(f, bin.f) && Objects.equals(s, bin.s);
    }

    @Override
    public int hashCode() {
        return ((f.hashCode() * 17 + s.hashCode()) * 17) + getBinOperator();
    }
}
