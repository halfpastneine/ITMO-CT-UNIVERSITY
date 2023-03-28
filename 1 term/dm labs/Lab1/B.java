import java.util.*;

public class B{
    // int num = 0;
    // int num1 = 0;
    // int cunt = 0;
    // int cunt1 = 0;
    // boolean tr = true;
    //
    // public static boolean Mon(boolean tr) {
    //     int[] left = getLeft(buffer);
    //     int[] right = getRight(buffer);
    //     if (left.length == 1) {
    //         return tr;
    //     }
    //     if (!tr) {
    //         return tr;
    //     }
    //     for (int i = 0; i < left.length; i++) {
    //         if (left[i] > right[i]) {
    //             tr = false;
    //         }
    //     }
    // }
    //
    // public static int[] getLeft(int buffer[]) {
    //     if ((buffer.length / press) * 2 * cunt == buffer.lenth()) {
    //         num = 0;
    //         cunt = 0;
    //     }
    //     int[] x = x.arraycopy(buffer, num, x, 0, buffer.length/press - 1);
    //     num += buffer.length/press * 2;
    //     cunt++;
    //     return x;
    // }

    // public static int[] getRight(int num1, int press, int buffer[]) {
    //     num1 += buffer.length/press * 2;
    //     int[] x = x.arraycopy(buffer, num1, x, 0,buffer.length/press);
    //     num1 += buffer.length/press * 2;
    //     cun1++;
    //     if ((buffer.length / press) * 2 * cunt1 == buffer.lenth()) {
    //         num = 0;
    //         cunt1 = 0;
    //     }
    //     return x;
    // }
    public static boolean isMon(int c , String str) {
        for (int i = 0; i < str.length(); i++) {
            int[] h =  new int[c];
            int d = i;
            for (int j = 0; j < c; j++) {
                h[c - j - 1] = d % 2;
                d = d / 2;
            }
            int[] h1 = new int[1];
            for (int k = 0; k < h.length; k++) {
                if (h[k] == 0) {
                    h[k] = 1;
                    h1[h1.length - 1] = getInt(h);
                    h[k] = 0;
                    h1 = Arrays.copyOf(h1, h1.length + 1);
                }
            }
            if (h1[0] != 0) {
                for (int j = 0; j < h1.length - 1; j++) {
                    // System.out.println(1111);
                    if (str.charAt(i) > str.charAt(h1[j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static int getInt(int[] h) {
        int number = 0;
        for (int i = h.length - 1; i >= 0; i--) {
            number += Math.pow(2, h.length - i - 1) * h[i];
        }
        return number;
    }

    public static void main(String[] args) {
        boolean F0 = true;
        boolean F1 = true;
        boolean FSD = true;
        boolean FL = true;
        boolean FM = true;
        int count = 0;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] string = new String[n];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
            string[i] = sc.next();
            int c = p[i];
            String str = string[i];
            if (FM) {
                FM = isMon(c, str);
            }
        }
        for (int q = 0; q < n; q++) {
            char[] buffer = string[q].toCharArray();
            double b = Math.pow(2, p[q]);
            if (Character.getNumericValue(buffer[0]) == 1) {
                F0 = false;
                System.out.println("1");
            }
            if (Character.getNumericValue(buffer[buffer.length - 1]) == 0) {
                F1 = false;
                System.out.println("2");
            }
            if (buffer.length == 1) {
                FSD = false;
            } else {
                for (int i = 0; i < buffer.length/2; i++) {
                    if (Character.getNumericValue(buffer[i]) == Character.getNumericValue(buffer[buffer.length - i - 1])){
                        FSD = false;
                        System.out.println("3");
                        break;
                    }
                }
            }
            // if (buffer.length > 2) {
            //     for (int press = 2; press <= (int) b/2; press = press * 2) {
            //         if (!buffer.Mon()) {
            //             FM = false;
            //         }
                    // int y = 0;
                    // while (y < buffer.length - press) {
                    //     if (buffer[y] > buffer[y + press]){
                    //         FM = false;
                    //         // System.out.println(4);
                    //         break;
                    //     }
                    //     else {
                    //         y++;
                    //     }
                    //     for (int z = 1; z < p[q]; z++) {
                    //         if (buffer[y - 1] == (int) Math.pow(2, z) - 1) {
                    //             y = y + press + 1;
                    //         }
                    //     }
                    // }


            // if (buffer.length > 2) {
            //     for (int i = 0; i < buffer.length - 1; i++) {
            //         for (int j = i; j < buffer.length; j++) {
            //             if (buffer[i] > buffer[j]) {
            //                 FM = false;
            //                 break;
            //             }
            //         }
            //     }
            // }
            // for (int i = 2; i < Math.pow(2**n); i = i * 2) {
            //     int[] left = Arrays.copyOfRange(buffer, 0, buffer.length/i);
            //     int[] right = Arrays.copyOfRange(buffer, buffer.length/i, buffer.length);
            // }
// 00100110
            int a = (int) b;
            int[][] z = new int[a][a];
            int[] intq = new int[a];
            for (int u = 0; u < buffer.length; u++) {
                z[0][u] = Character.getNumericValue(buffer[u]);
            }
            for (int h = 1; h < a; h++) {
                for (int m = 0; m < a; m++) {
                    if (m == a - 1) {
                        break;
                    } else {
                        z[h][m] = (z[h-1][m] + z[h-1][m + 1]) % 2;
                    }
                }
            }

            for (int r = 0; r < a; r++) {
                intq[r] = z[r][0];
            }
            int s = 0;
            for (int c = 1; c < a; c++) {
                if (c == Math.pow(2, s)) {
                    s++;
                }
                else {
                    if (intq[c] == 1)  {
                        FL = false;
                        System.out.println(5);
                    }
                }
            }
        }
        if (!FM) {
            System.out.println(1111111);
        }
        if (!F0 && !F1  && !FM  && !FL && !FSD) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
    }
}
