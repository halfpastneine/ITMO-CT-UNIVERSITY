package markup;
import java.util.List;

public class Strong extends AbstractClass implements Interface {

    public Strong(List<Interface> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append("__");
        for (Interface list : list) {
            list.toMarkdown(sb);
        }
        sb.append("__");
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append("[b]");
        for (Interface list : list) {
            list.toBBCode(sb);
        }
        sb.append("[/b]");
    }
}
