import java.util.*;

public class F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer, String> map = new HashMap<>();
        int count = 0;
        StringBuilder sb = new StringBuilder();
        int j = 1;
        for(int i = 0; i < 26; i++){
            map.put(count, Character.toString((char)(97 + i)));
            count++;
        }
        for (int i = 0; i < n; i++) {
            int tmp = sc.nextInt();
            if (map.containsKey(tmp)) {
                if (i == 0) {
                    map.put(count, map.get(tmp));
                    count++;
                } else {
                    String tp = map.get(count - 1);
                    StringBuilder temp = new StringBuilder();
                    String t = map.get(tmp);
                    temp.append(tp).append(t.charAt(0));
                    map.put(count - 1, temp.toString());
                    map.put(count, map.get(tmp));
                    count++;
                }
            }
            sb.append(map.get(tmp));
        }
        System.out.println(sb.toString());
        System.out.println(map);
    }
}
