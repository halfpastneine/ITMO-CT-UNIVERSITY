import java.util.*;

public class K {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        int t = 100000000;
        for (int i = 0; i < n; i++) {
            String ch = sc.next();
            if (ch.equals("+")) {
                int tmp = sc.nextInt();
                list.add(tmp);
                if (t > tmp) {
                    t = tmp;
                }
                System.out.println(t);
            } else {
                if (list.size() == 1) {
                    list.remove(0);
                    t = 100000000;
                    System.out.println(-1);
                } else {
                    if (t == list.get(0)) {
                        list.remove(0);
                        t = 100000000;
                        for (int j = 0; j < list.size(); j++) {
                            if (t > list.get(j)) {
                                t = list.get(j);
                            }
                        }
                    } else {
                        list.remove(0);
                    }
                    System.out.println(t);
                }
                // System.out.println(list);
            }
        }
    }
}
