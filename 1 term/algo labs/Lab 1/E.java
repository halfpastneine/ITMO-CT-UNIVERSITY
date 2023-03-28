import java.util.Scanner;

public class E {
    public static void main(String[] args) {
        boolean tr = true;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n + 1];
        for(int i = 1; i < n + 1; i++) {
            array[i] = sc.nextInt(); 
        }
        for (int i = 2; i < n + 1; i++) {
            if (array[i/2] > array[i]) {
                tr = false;
                break;
            }
        }
        if (tr) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}