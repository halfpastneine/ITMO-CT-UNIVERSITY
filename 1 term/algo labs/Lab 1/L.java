import java.util.*;

public class L {

    static int getLeftPos(int x, int n, ArrayList<Integer> a) {
        int l = -1;
        int r = n;
        int mid;
        while(r - l > 1) {
            mid = (l+r)/2;
            if(a.get(mid) < x){
                l = mid;
            } else {
                r = mid;
            }
        }
        return r;
    }

    static int getRightPos(int y, int n, ArrayList<Integer> a) {
        int l = -1;
        int r = n;
        int mid;
        while(r - l > 1) {
            mid = (l + r) / 2;
            if(a.get(mid) <= y){
                l = mid;
            } else {
                r = mid;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        Collections.sort(list);
        int k = sc.nextInt();
        for (int i = 0; i < k; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            System.out.print(getRightPos(y, n, list) - getLeftPos(x, n, list)  + " ");
        }
    }
}
