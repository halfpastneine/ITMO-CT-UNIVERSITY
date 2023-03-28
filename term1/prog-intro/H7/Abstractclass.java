package markup;
import java.util.List;

abstract class AbstractClass implements Interface {
    protected List<Interface> list;

    protected AbstractClass(List<Interface> list) {
        this.list = list;
    }
}
