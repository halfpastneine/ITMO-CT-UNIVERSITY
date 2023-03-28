public class SumLong{
    public static void main(String[] args){
        long sum = 0;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < args.length; i++){
            for (int j = 0; j < args[i].length(); j++){
                char ch = args[i].charAt(j);
                if (!Character.isWhitespace(ch)) {
                    str.append(ch);
                } else if(str.length() != 0){
                    sum += Long.parseLong(str.toString());
                    str = new StringBuilder();
                }
            }
            if (str.length() != 0){
                sum += Long.parseLong(str.toString());
                str = new StringBuilder();
            }
        }
        System.out.println(sum);
    }
}
