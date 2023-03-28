import java.util.Scanner;

public class W {

    public static char[] prevVec(char[] buffer) {
        int pos = 0;
        boolean tr = false;
        for (int i = buffer.length - 1; i >= 0; i--) {
            if (buffer[i] == '1') {
                buffer[i] = '0';
                pos = i;
                tr = true;
                break;
            }
        }
        for (int i = pos + 1; i < buffer.length; i++) {
            buffer[i] = '1';
        }
        if (tr) {
            return buffer;
        } else {
            return null;
        }
    }

    public static char[] nextVec(char[] buffer) {
        int n = buffer.length - 1;
        while (n >= 0 && buffer[n] != '0') {
            buffer[n] = '0';
            n--;
        }
        if (n == -1) {
            return null;
        }
        buffer[n] = '1';
        return buffer;
    }
    //10000
    //0
    //1
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        char[] buffer = s.toCharArray();
        char[] buffer1 = s.toCharArray();
        if (prevVec(buffer) != null) {
            System.out.println(buffer);
        } else {
            System.out.println("-");
        }
        if (nextVec(buffer1) != null) {
            System.out.println(buffer1);
        } else {
            System.out.println("-");
        }
    }
}
