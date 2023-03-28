package md2html;

import java.io.*;

public class Md2Html {
    public static void main(String[] args) {
        String input = args[0];
        String output = args[1];
        try {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(input),
                            "utf-8"
                    )
            );
            BufferedWriter wr = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(output),
                            "utf-8"
                    )
            );
//            BufferedReader rd = new BufferedReader(
//                    new InputStreamReader(
//                            new FileInputStream("C:\\Users\\dortm\\Desktop\\homework\\prog\\H9\\src\\md2html\\1.txt"),
//                            "utf-8"
//                    )
//            );
//            BufferedWriter wr = new BufferedWriter(
//                    new OutputStreamWriter(
//                            new FileOutputStream("C:\\Users\\dortm\\Desktop\\homework\\prog\\H9\\src\\md2html\\2.txt"),
//                            "utf-8"
//                    )
//            );
            try {
                String str = rd.readLine();
                while (str != null) {
                    StringBuilder s = new StringBuilder();
                    while (str.isEmpty()) {
                        str = rd.readLine();
                    }
                    while (str != null && !str.isEmpty()) {
                        if (s.length() != 0) {
                            s.append(System.lineSeparator());
                        }
                        s.append(str);
                        str = rd.readLine();
                    }
                    if (s.length() > 0) {
                        MH mh = new MH(s.toString());
                        wr.write(mh.getRes().toString());
                        wr.newLine();
                    }
                }
            } finally {
                rd.close();
                wr.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("+2 очка Слизерин" + e.getMessage());
        } catch (IOException e) {
            System.out.println("-2 очка Гриффиндору" + e.getMessage());
        }
    }
}