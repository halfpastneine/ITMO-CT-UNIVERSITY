package expression;

public class Add extends BinOperation {

    public Add(Exp f, Exp s) {
        super(f, s);
    }

    @Override
    public char getBinOperator() {
        return '+';
    }

    @Override
    public int getAns(int f, int s) {
        return f + s;
    }


}
