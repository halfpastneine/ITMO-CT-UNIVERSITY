import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class B {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new FileReader("automaton.in"));
        int n = sc.nextInt();
        String s = sc.next();
        String[][] delta = new String[n][2];
        sc.nextLine();
        HashSet<String> eps = new HashSet<>();
        ArrayList<String> as = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            String[] strings = str.split(" ");
            if (strings.length == 2) {
                delta[i][0] = "0";
                delta[i][1] = "0";
                eps.add(strings[0]);
                as.add(strings[0]);
            } else {
                delta[i][0] = strings[0];
                delta[i][1] = strings[2];
            }
        }
        int size;
        while (true) {
            size = eps.size();
            for (String[] strings : delta) {
                int count = 0;
                for (int j = 0; j < strings[1].length(); j++) {
                    if (eps.contains(String.valueOf(strings[1].charAt(j))))
                        count++;
                }
                if (count == strings[1].length()) {
                    as.add(strings[0]);
                    eps.add(strings[0]);
                    strings[0] = "0";
                    strings[1] = "0";
                    break;
                }
            }
            if (size == eps.size()) {
                break;
            }
        }
        FileWriter wr = new FileWriter("automaton.out");
        Collections.sort(as);
        for (String d : as)
            wr.write(d + " ");
        sc.close();
        wr.close();
    }
}
