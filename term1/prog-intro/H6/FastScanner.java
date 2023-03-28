import java.io.*;
import java.util.*;

public class FastScanner {


    private String Next() {
        int i = 0;
        while (Character.isWhitespace(lineBuffer.charAt(i))) {
            if (i == lineBuffer.length() - 1) {
                break;
            }
            i++;
        }
        int h = i;
        while (!Character.isWhitespace(lineBuffer.charAt(i))  ) {

            if (i == lineBuffer.length() - 1) {
                break;
            }
            i++;
        }
        String tmp = lineBuffer.substring(h,i + 1);
        tmp.trim();
        lineBuffer = lineBuffer.substring(i + 1);
        return tmp;
    }


    private BufferedReader rd;
    private String lineBuffer = null;
    private StringBuilder intBuffer = new StringBuilder();
    private StringBuilder nextBuffer = new StringBuilder();

    public FastScanner(InputStream input) {
        rd = new BufferedReader(new InputStreamReader(input));
    }

    public FastScanner(String input) {
        rd = new BufferedReader(new StringReader(input));
    }

    public FastScanner(File file, String string) {
        try {
            rd = new BufferedReader(new InputStreamReader(new FileInputStream(file), string));
        } catch (FileNotFoundException e) {
            System.out.println("Вы проиграли " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println("Вы проиграли " + e.getMessage());
        }
    }

    public FastScanner(File file) {
        try {
            rd = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException a) {
            System.out.println("Вы проиграли " + a.getMessage());
        }
    }

    public void close() {
        try {
            rd.close();
        } catch (IOException e) {
            System.out.print("Вы проиграли " + e.getMessage());
        }
    }

    public boolean hasNextLine() {
        if (lineBuffer != null){
            return true;
        } else {
            try {
                lineBuffer = rd.readLine();
                return lineBuffer != null;
            } catch (IOException e) {
                System.out.print("Вы проиграли " + e.getMessage());
                return false;
            }
        }
    }


    public String nextLine() {
        hasNextLine();
        String tmp = lineBuffer;
        lineBuffer = null;
        return tmp;
    }

    public boolean hasNextInt() {
        if (intBuffer.length() > 0) {
            return true;
        } else {
            try {
                int tmp;
                while ((tmp = rd.read()) != -1) {
                    char ch = (char) tmp;
                    if (Character.isDigit(ch) || ch == '-' || ch == '+') {
                        intBuffer.append(ch);
                    } else {
                        return hasNextInt();
                    }
                }
                return intBuffer.length() > 0;
            } catch (IOException e) {
                System.err.print("Вы проиграли " + e.getMessage());
                return false;
            }
        }
    }

    public Integer nextInt() {
        if(hasNextInt()) {
            Integer tmp = Integer.parseInt(intBuffer.toString());
            intBuffer.setLength(0);
            return tmp;
        } else return null;
    }

    public boolean hasNext() {
    if (lineBuffer != null) {
        return true;
    }
        if (nextBuffer.length() > 0) {
            return true;
        } else {
            try {
                int tmp;
                while ((tmp = rd.read()) != -1) {
                    char ch = (char) tmp;
                    if (Character.isLetter(ch) || Character.getType(ch) == Character.DASH_PUNCTUATION || ch == '\'') {
                        nextBuffer.append(ch);
                    } else {
                        return hasNext();
                    }
                }
                return nextBuffer.length() > 0;
            } catch (IOException e) {
                System.out.print("Вы проиграли " + e.getMessage());
                return false;
            }
        }
    }

    public String next() {
        if (lineBuffer != null) {
            return Next();
        }
        if(hasNext()) {
            String tmp = nextBuffer.toString();
            nextBuffer.setLength(0);
            return tmp;
        } else return null;
    }


}
