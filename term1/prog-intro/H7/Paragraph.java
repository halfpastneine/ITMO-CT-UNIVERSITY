package markup;
import java.util.List;

public class Paragraph extends AbstractClass implements Interface {

    public Paragraph(List<Interface> list) {
        super(list);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (Interface list : list) {
            list.toMarkdown(sb);
        }
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        for (Interface list : list) {
            list.toBBCode(sb);
        }
    }
}