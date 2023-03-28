import java.io.*;

public class FastScanner {

    private BufferedReader rd;
    private String tmp;
    private StringBuilder sb = new StringBuilder();
    boolean tr = true;

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
            System.out.println("File not found " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println("File not found " + e.getMessage());
        }
    }

    public void close() {
        try {
            rd.close();
        } catch (IOException e){}
    }

    public void read() {
        try {
            tmp = rd.readLine();
        } catch (IOException e) {
        }
    }

    public boolean hasNextLine() {
        if (tmp == null) {
            read();
        }
        return tmp != null;
    }

    public String nextLine() {
        hasNextLine();
        String t = tmp;
        tmp = null;
        return t;
    }

    public boolean hasNext() {
        return tr;
    }

    public String next() {
        try {
            skipWhiteSpace();
            int read;
            while ((read = rd.read()) != -1) {
                char ch = (char) read;
                if (Character.isLetter(ch)
                        || Character.getType(ch) == Character.DASH_PUNCTUATION
                        || ch == '\'' || ch == '-' || ch == '+' || Character.isDigit(ch)) {
                    sb.append(ch);
                } else {
                    String pop = sb.toString();
                    sb = new StringBuilder();
                    return pop;
                }
            }
            tr = false;
        }catch (IOException e){}
        return sb.toString();
    }

    public void skipWhiteSpace() {
        try {
            int read;
            while ((read = rd.read()) != -1) {
                char ch = (char) read;
                if (Character.isLetter(ch)
                        || Character.getType(ch) == Character.DASH_PUNCTUATION
                        || ch == '\'' || ch == '-' || ch == '+' || Character.isDigit(ch)) {
                    sb.append(ch);
                    break;
                }
            }
        } catch (IOException e) {
        }
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
