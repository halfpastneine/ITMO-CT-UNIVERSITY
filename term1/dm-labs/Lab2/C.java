import java.util.*;

public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Collections.sort(list);
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                if (i == 0) {
                    sb.append(str.charAt(j));
                    list.add(sb.toString());
                    sb = new StringBuilder();
                } else {
                    sb.append(str.charAt(j)).append(list.get(j));
                    list.set(j, sb.toString());
                    sb = new StringBuilder();
                }
            }
            Collections.sort(list);
        }
        System.out.println(list.get(0));
    }
}
