import java.util.*;

public class E {
    public static void main(String[] args) {
        boolean tr = true;
        Map <Character, Character> map = Map.of(')', '(', ']', '[', '}', '{');
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char[] buffer = new char[str.length()];
        int pos = -1;
        for (int i = 0; i < buffer.length; i++) {
            if (map.containsValue(str.charAt(i))) {
                pos++;
                buffer[pos] = str.charAt(i);
            } else {
                if (pos == -1) {
                    tr = false;
                    break;
                } else {
                    if (buffer[pos] != map.get(str.charAt(i))) {
                        tr = false;
                        break;
                    }
                    pos--;
                }
            }
        }
        if (pos > -1 || !tr) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }
}