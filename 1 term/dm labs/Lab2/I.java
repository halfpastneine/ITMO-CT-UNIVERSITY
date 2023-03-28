import java.util.Scanner;

public class I {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 1) {
            String str = sc.next();
            StringBuilder sb = new StringBuilder();
            int st = 0;
            int qr = 0;
            for (int i = 1; i < str.length() + 1; i++) {
                if (i == Math.pow(2, st)) {
                    st++;
                    sb.append("0");
                } else {
                    sb.append(str.charAt(qr));
                    qr++;
                }
            }
            while (qr < str.length()) {
                sb.append(str.charAt(qr));
                qr++;
            }
            int s = 1;
            for (int i = 0; i < sb.length(); i++) {
                if (i == s) {
                    int count = 0;
                    int count2 = 1;
                    int j = s;
                    while (j < sb.length()) {
                        while (count2 < s) {
                            if (j + count2 > sb.length()) {
                                break;
                            }
                            if (sb.charAt(j + count2 - 1) == '1') {
                                count++;
                            }
                            count2++;
                        }
                        if (j + 2 * s > sb.length()) {
                            break;
                        } else {
                            j += 2 * s;
                            if (sb.charAt(j - 1) == '1') {
                                count++;
                            }
                            count2 = 1;
                        }
                    }
                    s = 2 * s;
                    if (count % 2 == 1) {
                        sb.replace(i - 1, i, "1");
                    }
                }
            }
            System.out.println(sb.toString());
        } else {
            String str = sc.next();
            StringBuilder sb = new StringBuilder().append(str);
            int s = 1;
            int cnt = 0;
            for (int i = 0; i < sb.length(); i++) {
                if (i == s) {
                    int count = 0;
                    int count2 = 1;
                    int j = s;
                    while (j < sb.length()) {
                        while (count2 < s) {
                            if (j + count2 > sb.length()) {
                                break;
                            }
                            if (sb.charAt(j + count2 - 1) == '1') {
                                count++;
                            }
                            count2++;
                        }
                        if (j + 2 * s > sb.length()) {
                            break;
                        } else {
                            j += 2 * s;
                            if (sb.charAt(j - 1) == '1') {
                                count++;
                            }
                            count2 = 1;
                        }
                    }
                    s = 2 * s;
                    if (count % 2 != Character.getNumericValue(sb.charAt(i - 1))) {
                        cnt += i;
                    }
                }
            }
            if (cnt != 0) {
                if (Character.getNumericValue(sb.charAt(cnt - 1)) == 0) {
                    sb.replace(cnt - 1, cnt, "1");
                } else {
                    sb.replace(cnt - 1, cnt, "0");
                }
            }
            StringBuilder sq = new StringBuilder();
            int st = 0;
            for (int i = 0; i < sb.length(); i++) {
                if (i == Math.pow(2, st) - 1) {
                    st++;
                } else {
                    sq.append(sb.charAt(i));
                }
            }
            System.out.println(sq.toString());
        }
    }
}
