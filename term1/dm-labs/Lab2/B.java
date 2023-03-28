import java.util.*;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        StringBuilder result = new StringBuilder();
        list.add(str);
            for (int j = 1; j < str.length(); j++) {
                sb.append(str.substring(j, str.length())).append(str.substring(0, j));
                list.add(sb.toString());
                sb = new StringBuilder();
        }
        Collections.sort(list);
        for (int i = 0; i < str.length(); i++) {
            String q = list.get(i);
            result.append(q.charAt(str.length() - 1));
        }
        System.out.println(result.toString());
    }
}
