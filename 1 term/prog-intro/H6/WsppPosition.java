import java.util.*;
import java.io.*;

public class WsppPosition {
    public static void main(String[] args) {
        List<String> str = new ArrayList<>();
        List<ArrayList<Integer>> ints = new ArrayList<ArrayList<Integer>>();
        String fileinput = "";
        String fileoutput = "";
        int j = 0;
        int line = 0;
        int num = 0;
        FastScanner sc = new FastScanner(new File(args[0]), "utf-8");
        while (sc.hasNextLine()) {
            line++;
            j = 0;
            FastScanner sc1 = new FastScanner(sc.nextLine());
            while (sc1.hasNext()) {
                String r = sc1.next();
                if (!r.isEmpty()) {
                    j++;
                    String s = r.toString().toLowerCase();
                    if (str.contains(s)) {
                        int index = str.indexOf(s);
                        int sum = ints.get(index).get(0);
                        sum = sum + 1;
                        ints.get(index).set(0, sum);
                        ints.get(index).add(line);
                        ints.get(index).add(j);
                    } else {
                        ints.add(new ArrayList<Integer>());
                        str.add(s);
                        int index = str.indexOf(s);
                        ints.get(index).add(1);
                        ints.get(index).add(line);
                        ints.get(index).add(j);
                    }
                }
            }
        }
        sc.close();
        try {
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                        "utf-8"
                        )
            );
            try {
                for (int i = 0; i < str.size(); i++) {
                    writer.write(str.get(i) + " ");
                    writer.write(ints.get(i).get(0) + " ");
                    for (int l = 1; l < ints.get(i).size() - 1; l = l + 2) {
                        if (l ==  ints.get(i).size() - 2) {
                            writer.write(ints.get(i).get(l) + ":" );
                        } else {
                            writer.write(ints.get(i).get(l) + ":" + ints.get(i).get(l + 1) + " ");
                        }
                    }
                    int h = ints.get(i).size() - 1;
                    writer.write(ints.get(i).get(h) + System.lineSeparator());
                }
                } finally {
                    writer.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found" + e.getMessage());
            } catch  (IOException e) {
                System.out.println("Cannot read or write" + e.getMessage());
            }
        }
    }
