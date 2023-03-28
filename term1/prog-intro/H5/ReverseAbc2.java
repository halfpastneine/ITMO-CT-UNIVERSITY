import java.util.*;


public class ReverseAbc2{

    private static Map<Character, Character> map = new HashMap<>();
    private static int  toInt(String str) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            s.append(map.get(str.charAt(i)));
        }
        return Integer.parseInt(s.toString());
    }

    public static void main(String args[]){
        map.put('a', '0');
        map.put('b', '1');
        map.put('c', '2');
        map.put('d', '3');
        map.put('e', '4');
        map.put('f', '5');
        map.put('g', '6');
        map.put('h', '7');
        map.put('i', '8');
        map.put('j', '9');
        map.put('-', '-');

        FastScanner sc = new FastScanner (System.in);
        int arr [] = new int [1000000];
        int res [][] = new int [1000000][];
        int i = 0;
        int j = 0;
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            FastScanner sc1 = new FastScanner(s);
            while(sc1.hasNext()){
                arr[i] = toInt(sc1.next());
                i++;
            }
            int ints [] = new int [i];
            for (int x = 0; x<i; x++ ){
                ints[x] = arr[x];
            }
            i = 0;
            res[j] = ints;
            j++;
        }
        for ( i = j - 1; i >= 0; i-- ){
            for ( j = res[i].length - 1 ; j >= 0 ; j--){
                System.out.print(res[i][j]+" ");
            }
            System.out.println();
        }
    }
}
