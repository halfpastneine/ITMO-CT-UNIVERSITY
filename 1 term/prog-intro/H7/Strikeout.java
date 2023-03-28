package markup;
import java.util.List;

public class Strikeout extends AbstractClass implements Interface {

    public Strikeout(List<Interface> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append("~");
        for (Interface list : list) {
            list.toMarkdown(sb);
        }
        sb.append("~");
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append("[s]");
        for (Interface list : list) {
            list.toBBCode(sb);
        }
        sb.append("[/s]");
    }
}