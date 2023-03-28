import java.util.*;
import java.io.*;

public class WordStatWords{
    public static void main(String[] args) {
        String[] str = new String[100];
        int[] ints = new int[100];
        String fileinput = "";
        String fileoutput = "";
        try {
            fileinput = args[0];
            fileoutput = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong index" + e.getMessage());
            System.exit(-1);
        }
        int i = 0;
        int j = 0;
        FastScanner sc = new FastScanner(new File(fileinput), "utf-8");
        while (sc.hasNext()) {
            String r = sc.next();
            if (!r.isEmpty()) {
                String r1 = r.toLowerCase();
                boolean k = true;
                if (i == str.length) {
                    str = Arrays.copyOf(str, str.length * 2);
                    ints = Arrays.copyOf(ints, ints.length * 2);
                }
                for (j = 0; j < i; j++) {
                    if (str[j].equals(r1)) {
                        ints[j] += 1;
                        k = false;
                    }
                }
                if (k == true) {
                    str[i] = r1;
                    ints[i] += 1;
                    i++;
                }
            }
        }
        sc.close();
        try {
            BufferedWriter buffer1 = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(fileoutput),
                        "utf-8"
                        )
            );
            try {
                for (int m = 0; m < i; m++) {
                    for (int n = 0; n < i; n++) {
                        if (str[m].compareTo(str[n]) < 0) {
                            String str1 = str[m];
                            str[m] = str[n];
                            str[n] = str1;
                            int ints1 = ints[m];
                            ints[m] = ints[n];
                            ints[n] = ints1;
                        }
                    }
                }
                for (int k = 0; k < i; k++) {
                    buffer1.write(str[k]+ " " + ints[k] + "\n");
                }
            } finally {
                buffer1.close();
            }
        } catch  (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        } catch  (IOException e) {
            System.out.println("Cannot read or write" + e.getMessage());
        }
    }
}
