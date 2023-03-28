package markup;

public class Text implements Interface{
    private final String str;

    public Text(String str) {
        this.str = str;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(str);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(str);
    }
}
