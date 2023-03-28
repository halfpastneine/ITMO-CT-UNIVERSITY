package md2html;

import java.util.*;

public class MH {

    private final StringBuilder sb;
    private final List<Character> list = List.of('*', '_', '-', '`', '<', '>', '\\', '&', '%');

    public MH(String mh) {
        int num = getNum(mh);
        sb = new StringBuilder();
        if (num > 0) {
            sb.append("<h").append(num).append(">");
        } else {
            sb.append("<p>");
        }
        getMark(sb, mh, num);
        if (num > 0) {
            sb.append("</h").append(num).append(">");
        } else {
            sb.append("</p>");
        }

    }

    public StringBuilder getRes() {
        return sb;
    }

    public int getNum(String str) {
        int Num = 0;
        while (str.charAt(Num) == '#') {
            Num++;
        }
        if (str.charAt(Num) == ' ') {
            return Num;
        }
        return 0;
    }

    private StringBuilder getMark(StringBuilder s, String line, int Num) {
        int count;
        int codeflag = 0;
        int emflag = 0;
        int strflag = 0;
        int sflag = 0;
        int varflag = 0;
        if (Num > 0) {
            count = Num + 1;
        } else {
            count = Num;
        }
        while (count != line.length()) {
            if (list.contains(line.charAt(count))) {
                switch (line.charAt(count)) {
                    case '*':
                    case '_':
                        if (line.charAt(count) == line.charAt(count + 1)) {
                            getStrong(s, count, line, strflag);
                            if (strflag == 0) {
                                count += 2;
                                strflag = s.length() - 1;
                            } else {
                                count += 2;
                                strflag = 0;
                            }
                            break;
                        } else {
                            getEm(s, count, line, emflag);
                            if (emflag == 0) {
                                count++;
                                emflag = s.length() - 1;
                            } else {
                                emflag = 0;
                                count++;
                            }
                        }
                        break;
                    case '-':
                        getS(s, count, line, sflag);
                        if (Character.isLetter(line.charAt(count - 1)) && (Character.isLetter(line.charAt(count + 1)))) {
                            count++;
                            break;
                        }
                        if (sflag == 0) {
                            count += 2;
                            sflag = s.length() - 1;
                        } else {
                            count += 2;
                            sflag = 0;
                        }
                        break;
                    case '`':
                        if (codeflag == 0) {
                            s.append("<code>");
                            count++;
                            codeflag++;
                        } else {
                            codeflag--;
                            count++;
                            s.append("</code>");
                        }
                        break;
                    case '\\' :
                        if (line.charAt(count + 1) == '*' || line.charAt(count + 1) == '_') {
                            getEm(s, count, line, emflag);
                            count += 2;
                            break;
                        }
                        if (line.charAt(count + 1) == '%') {
                            s.append('%');
                            count += 2;
                            break;
                        }

                    case '<':
                    case '>':
                    case '&':
                        getSign(s, count, line);
                        count++;
                        break;
                    case '%':
                        getVar(s, count, line, varflag);
                        if (varflag == 0) {
                            varflag = s.length() - 1;
                            count++;
                        } else {
                            count++;
                            varflag = 0;
                        }
                        break;
                }
            } else {
                s.append(line.charAt(count));
                count++;
            }
        }
        return s;
    }

    private StringBuilder getStrong(StringBuilder s, int count, String line, int strflag) {
        if (strflag == 0) {
            s.append(line.charAt(count));
            return s;
        } else {
            s.replace(strflag, strflag + 1, "<strong>");
            s.append("</strong>");
            return s;
        }
    }

    private StringBuilder getEm(StringBuilder s, int count, String line, int emflag) {
        if (line.charAt(count) == '\\') {
            s.append(line.charAt(count + 1));
            return s;
        }
        if (emflag == 0 || (emflag > 0 && line.charAt(count) != s.charAt(emflag))) {
            s.append(line.charAt(count));
            return s;
        } else {
            s.replace(emflag, emflag + 1, "<em>");
            s.append("</em>");
            return s;
        }
    }

    private StringBuilder getS(StringBuilder s, int count, String line, int sflag) {
        if (Character.isLetter(line.charAt(count - 1)) && (Character.isLetter(line.charAt(count + 1)))) {
            s.append(line.charAt(count));
        } else {
            if (sflag == 0) {
                s.append(line.charAt(count));
                return s;
            } else {
                s.replace(sflag, sflag + 1, "<s>");
                s.append("</s>");
                return s;
            }
        }
        return s;
    }

    private StringBuilder getSign(StringBuilder s, int count, String line) {
        if (line.charAt(count) == '<') {
            s.append("&lt;");
            return s;
        }
        if (line.charAt(count) == '>') {
            s.append("&gt;");
            return s;
        }
        if (line.charAt(count) == '&') {
            s.append("&amp;");
            return s;
        }
        return s;
    }

    private StringBuilder getVar(StringBuilder s, int count, String line, int varflag) {
        if (varflag == 0) {
            s.append(line.charAt(count));
            return s;
        } else {
            s.replace(varflag, varflag + 1, "<var>");
            s.append("</var>");
            return s;
        }
    }
}
