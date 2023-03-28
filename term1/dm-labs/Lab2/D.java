import java.util.*;

public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int[] bit = new int[str.length()];
        char[] buffer = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            buffer[i] = str.charAt(i);
        }
         char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
         for (int i = 0; i < str.length(); i++) {
             int tmp = 0;
             for (int j = 0; j < alphabet.length; j++) {
                 if (buffer[i] == alphabet[j]) {
                     bit[i] = j + 1;
                     tmp = j;
                     break;
                 }
             }
             char[] tm = new char[alphabet.length];
             System.arraycopy(alphabet, tmp, tm, 0, 1);
             System.arraycopy(alphabet, 0, tm, 1, tmp);
             System.arraycopy(alphabet, tmp + 1, tm, tmp + 1, alphabet.length - tmp - 1);
             System.arraycopy(tm, 0, alphabet, 0, tm.length);
         }
         for (int i = 0; i < str.length(); i++) {
             System.out.print(bit[i] + " ");
         }
    }
}
