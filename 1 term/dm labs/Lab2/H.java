import java.util.*;
import java.math.*;

public class H {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Character> list = new ArrayList<>();
        int sum = 0;
        BigDecimal[] temp = new BigDecimal[n];
        int count = 0;
        BigDecimal[] otr = new BigDecimal[n + 1];
        // MathContext mc = new MathContext(100);
        int jpl = 0;
        for(int i = 0; i < n; i++){
            int jm = sc.nextInt();
            if (jm == 0) {
                count++;
            } else {
                list.add((char)(97 + i));
                sum += jm;
                temp[jpl] = BigDecimal.valueOf(jm);
                // System.out.println(temp[jpl]);
                jpl++;
            }
        }
        String str = sc.next();
        MathContext mc = new MathContext(str.length());
        for (int i = jpl; i < n; i++) {
            temp[i] = BigDecimal.valueOf(0);
        }
        otr[0] = BigDecimal.valueOf(0);
        for (int i = 1; i < n + 1; i++) {
            // System.out.println(temp[i - 1]);
            otr[i] = temp[i - 1];
            otr[i] = otr[i].divide(BigDecimal.valueOf(sum), mc);
            // System.out.println(otr[i]);
        }

        // BigInteger int1 = new BigInteger(str, 2);
        // BigDecimal dec1 = new BigDecimal(int1);
        // // BigDecimal tmp = (dec1.divide(BigDecimal.valueOf(Math.pow(2, str.length())), mc));
        // BigDecimal dec1 = new BigDecimal(str);
        BigDecimal tmp = new BigDecimal(0);
        BigDecimal f = new BigDecimal(0.5);
        for (int i = 1; i < str.length() + 1; i++) {
            if (str.charAt(i - 1) == '0') {
                continue;
            } else {
                tmp = tmp.add(f.pow(i));
            }
        }
        // System.out.println(tmp);
        // 0 0.2
        // 0.2 0.5
        // ...
        // 0.8 1
        BigDecimal[][] ans = new BigDecimal[n][2];
        ans[0][0] = BigDecimal.valueOf(0);
        ans[0][1] = otr[1];
        // System.out.println(ans[0][0] + " " + ans[0][1]);
        // if (n - count > 1) {
            for (int i = 1; i < n; i++) {
                ans[i][0] = ans[i - 1][1];
                ans[i][1] = ans[i - 1][1].add(otr[i + 1]);
                // System.out.println(ans[i][0] + " " + ans[i][1]);
            }
        // }
        // System.out.println(sum);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sum; i++) {
            for (int j = 0; j < n; j++) {
                if ((tmp.compareTo(ans[j][0])) != -1 && (tmp.compareTo(ans[j][1])) == -1) {
                    sb.append(list.get(j));
                    // System.out.println(tmp);
                    tmp = (tmp.subtract(ans[j][0])).divide((ans[j][1].subtract(ans[j][0])), mc);
                    break;
                }
            }
        }
        // (tmp - left)/(right - left);
        // System.out.println(tmp);
        System.out.println(sb.toString());
    }
}
