import java.util.*;
import java.math.*;
public class G {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(n);
        String str = sc.next();
        BigDecimal[][] ans = new BigDecimal[str.length()][2];
        BigDecimal[] d = new BigDecimal[n];
        MathContext mc = new MathContext(10);
        ArrayList<Character> list = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            list.add((char)(97 + i));
        }
        for (int i = 0; i < n; i++) {
            d[i] = BigDecimal.valueOf(0);
        }
        for (int i = 0; i < str.length(); i++) {
            if (list.contains(str.charAt(i))) {
                d[list.indexOf(str.charAt(i))] = d[list.indexOf(str.charAt(i))].add(BigDecimal.valueOf(1));
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(d[i] + " ");
        }
        BigDecimal[] t = new BigDecimal[d.length + 1];
        t[0] = BigDecimal.valueOf(0);
        for (int i = 0; i < d.length; i++) {
            d[i] = d[i].divide(BigDecimal.valueOf(str.length()), mc);
        }
        for (int i = 1; i < d.length + 1; i++) {
            t[i] = d[i - 1].add(t[i - 1]);
        }
        ans[0][0] = (t[list.indexOf(str.charAt(0))]);
        ans[0][1] = (t[list.indexOf(str.charAt(0)) + 1]);
        for (int i = 1; i < str.length(); i++) {

            ans[i][0] = ans[i - 1][0].add((ans[i - 1][1].subtract(ans[i - 1][0])).multiply((t[list.indexOf(str.charAt(i))])));
            ans[i][1] = ans[i - 1][0].add((ans[i - 1][1].subtract(ans[i - 1][0])).multiply((t[list.indexOf(str.charAt(i)) + 1])));
            System.out.println(ans[i][0] + " " + ans[i][1]);
        }
        StringBuilder sb = new StringBuilder();
        BigDecimal r = new BigDecimal(0);
        BigDecimal temp = new BigDecimal(0.5);
        int st = 1;
        BigDecimal f = new BigDecimal(0.5);
        while (r.compareTo(ans[str.length() - 1][0]) == -1) {
            temp = f.pow(st);
            st++;
            if (r.add(temp).compareTo(ans[str.length() - 1][1]) == -1) {
                    r = r.add(temp);
                    sb.append("1");
                    System.out.println(temp);
            } else {
                sb.append("0");
                System.out.println(temp);
            }
        }
        System.out.println(r);
        if (r.compareTo(BigDecimal.valueOf(0)) == 0) {
            sb.append("0");
        }
        System.out.println();
        System.out.println(sb.toString());
    }
}
