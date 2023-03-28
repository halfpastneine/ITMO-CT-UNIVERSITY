import java.util.*;

public class I {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ints = new int[n];
        int[][] ans = new int[2*n][2];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ints[i] = sc.nextInt();
        }
        int j = 0;
        int count = 1;
        int cnt = 0;
        boolean tr = false;
        for (int i = 0; i < n; i++) {
            if (ints[i] == count) {
                list.add(ints[i]);
                ans[cnt][0] = 1;
                ans[cnt][1]++;
                cnt++;
                ans[cnt][0] = 2;
                while (list.get(list.size() - 1) == count) {
                    ans[cnt][1]++;
                    count++;
                    list.remove(list.size() - 1);
                    if (list.size() == 0) {
                        break;
                    }
                }
                cnt++;
            } else {
                ans[cnt][0] = 1;
                ans[cnt][1]++;
                list.add(ints[i]);
            }
        }
        boolean tr1 = false;
        if (list.size() == 0) {
            for (int i = 0; i < cnt; i++) {
                System.out.println(ans[i][0] + " " + ans[i][1]);
            }
        } else {
            cnt++;
            ans[cnt][0] = 2;
            ans[cnt][1] = 0;
            for (int i = list.size() - 1; i > 0; i--) {
                if (list.get(list.size() - 1) != count) {
                    System.out.println(0);
                    tr1 = true;
                    break;
                } else {
                    if (list.size() == 0) {
                        break;
                    }
                    list.remove(list.size() - 1);
                    count++;
                    ans[cnt][1]++;
                }
            }
            if (!tr1) {
                for (int i = 0; i < cnt; i++) {
                    System.out.println(ans[cnt][0] + " " + ans[cnt][1]);
                }
            }
        }
    }
}
