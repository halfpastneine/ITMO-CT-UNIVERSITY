import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new FileReader("automaton.in"));
        int n = sc.nextInt();
        String k = sc.next();
        HashSet<String> np = new HashSet<>();
        HashSet<String> all = new HashSet<>();
        String[][] delta = new String[n][2];
        sc.nextLine();
        all.add(k);
        ArrayList<String> nan = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            String[] strings = str.split(" ");
            if (strings.length == 2) {
                delta[i][0] = strings[0];
                delta[i][1] = "";
            } else {
                delta[i][0] = strings[0];
                delta[i][1] = strings[2];
            }
            all.add(delta[i][0]);
            boolean tr = false;
            for (int j = 0; j < delta[i][1].length(); j++) {
                if (Character.isUpperCase(delta[i][1].charAt(j))) {
                    all.add(String.valueOf(delta[i][1].charAt(j)));
                    tr = true;
                }
            }
            if (!tr) {
                np.add(delta[i][0]);
                nan.add(delta[i][0] + " " + delta[i][1]);
            }

        }
        ArrayList<String> as = new ArrayList<>();
        int size;
        while (true) {
            size = np.size();
            for (String[] s : delta) {
                boolean tr = true;
                for (int i = 0; i < s[1].length(); i++) {
                    if (Character.isUpperCase(s[1].charAt(i))) {
                        if (!np.contains(String.valueOf(s[1].charAt(i)))) {
                            tr = false;
                            break;
                        }
                    }
                }
                if (tr) {
                    np.add(s[0]);
                    if (!nan.contains(s[0] + " " + s[1]))
                        nan.add(s[0] + " " + s[1]);
                }
            }
            if (size == np.size()) {
                break;
            }
        }
        System.out.println(np);
        for (String d : all) {
            if (!np.contains(d)) {
                as.add(d);
            }
        }
        System.out.println(as);
        int count = 0;
        String[][] delta1 = new String[n][2];
        for (String s : nan) {
            String[] str = s.split(" ");
            delta1[count][0] = str[0];
            if (str.length == 1)
                delta1[count][1] = "";
            else
                delta1[count][1] = str[1];
            count++;
        }
        HashSet<String> tl = new HashSet<>();
        tl.add(k);
        while (true) {
            size = tl.size();
            for (String[] s : delta1) {
                if (tl.contains(s[0])) {
                    for (int i = 0; i < s[1].length(); i++) {
                        if (Character.isUpperCase(s[1].charAt(i))) {
                            tl.add(String.valueOf(s[1].charAt(i)));
                        }
                    }
                }
            }
            if (size == tl.size()) {
                break;
            }
        }
        System.out.println(tl);
        for (String d : all) {
            if (!tl.contains(d)) {
                if (!d.equals(k))
                    if (!as.contains(d))
                        as.add(d);
            }
        }
        Collections.sort(as);
        FileWriter wr = new FileWriter("automaton.out");
        for (String s : as) {
            wr.write(s + " ");
        }
        sc.close();
        wr.close();
    }
}