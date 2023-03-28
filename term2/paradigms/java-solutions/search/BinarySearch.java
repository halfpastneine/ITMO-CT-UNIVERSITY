package search;

import java.util.Random;

public class BinarySearch {
    //    Print in console ints[r]: ints[l] > a >= ints[r]
    public static void main(String[] args) {
        try {
//            for i: 0...args.length args[i] != null
            int num = Integer.parseInt(args[0]);
//            num = Integer.parseInt(args[0]);
            int[] ints = new int[args.length - 1];
//            ints = new int[args.length - 1];
            for (int i = 0; i < args.length - 1; i++) {
//                args[i] != null;
                ints[i] = Integer.parseInt(args[i + 1]);
//                ints[i] = Integer.parseInt(args[i + 1]);
            }
            Random random = new Random();
            if (random.nextInt() % 2 == 0) {
//                random.nextInt() % 2 == 0
//                i < j && ints[i] < ints[j]
                System.out.println(iterBinSearch(ints, num));
//                r' - l' <= 1 && ints[l'] > a >= ints[r']
//                ints[r' - 1] > a > ints[r' + 1}
//                ints[r'] <= a
            } else {
//                random.nextInt() % 2 != 0
//                l' = l; r' = r
                int l = -1;
                int r = args.length - 1;
//                l' = -1; r' = args.length - 1
//                i < j && ints[i] < ints[j]
                System.out.println(rekBinSearch(ints, num, l, r));
//                r' - l' <= 1 && ints[l'] > a >= ints[r']
//                ints[r' - 1] > a > ints[r' + 1}
//                ints[r'] <= a
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Out of Bounds " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Not a number " + e.getMessage());
        }
    }

    //      Pred: for i: 0...ints.length - 1 ints[i] >= ints[i + 1] && l > -1, r < ints.length && ints[l] > a >= ints[r]
    public static int iterBinSearch(int[] ints, int a) {
//      l' = l; r' = r' mid' = mid
        int l = -1;
        int r = ints.length;
        int mid = (l + r) / 2;
//        l' = -1; r' = ints.length; mid' = (l' + r') / 2
//        Inv: ints[l'] > a >= ints[r'] && r' - l' > 1 && l' >= 0, r' < ints.length;
        while (r - l > 1) {
//            ints[l'] > a >= ints[r'] && r' - l' > 1
            if (a < ints[mid]) {
//                ints[l'] > a >= ints[r'] && r' - l' > 1 && a < ints[mid']
                l = mid;
//                ints[l'] > a >= ints[r'] && r' - l' > 1 && l' = mid && a < ints[l']
//                Exp: a > ints[mid'] && l = mid => a < ints[l']
            }
//            ints[l'] > a >= ints[r'] && r' - l' > 1
            if (a >= ints[mid]) {
//                ints[l'] > a >= ints[r'] && r' - l' > 1 && a >= ints[mid']
                r = mid;
//                ints[l'] > a >= ints[r'] && r' - l' > 1 && a >= ints[r']
//                Exp: a <= ints[mid'] && r = mid => a >= ints[r']
            }
//            ints[l'] > a >= ints[r'] && r' - l' > 1 && l' <= mid' <= r'
//            ints[l'] > a >= ints[r'] && r' - l' > 1 && l' < (l' + r') / 2 < r'
            mid = (l + r) / 2;
//            ints[l'] > a >= ints[r'] && r' - l' > 1 && l' < mid' < r'
//            ints[l'] >= a >= ints[r']
        }
//        r' - l' <= 1 && ints[l'] >= a >= ints[r']
//        ints[r' - 1] > a >= ints[r' + 1]
        return r;
//        ints[r'] <= a && for i: 0...n ints[i] >= ints[i + 1]
    }
//    Post: R && ints[R] <= a && ints[R - 1] > a

    //      Pred: for i: 0...ints.length - 1 ints[i] >= ints[i + 1] && l > -1, r < ints.length && ints[l] > a >= ints[r]
    public static int rekBinSearch(int[] ints, int a, int l, int r) {
//       l' = l; r' = r mid' = mid
        int mid = (l + r) / 2;
//        ints[l'] > a >= ints[r'] && l' < (l' + r') / 2 < r' => l' < mid' < r'
        if (r - l == 1) {
//        r' - l' == 1 && ints[l'] > a >= ints[r']
            return r;
//        ints[r' - 1] > a > ints[r' + 1]
//        ints[r'] <= a
        }
        if (a < ints[mid]) {
//            r' - l' > 1 && a < ints[mid'] && ints[l'] > a >= ints[r'] && a < ints[mid']
            l = mid;
//            r' - l' > 1 && a < ints[l'] && ints[l'] > a >= ints[r']
            return rekBinSearch(ints, a, l, r);
//            ints[l'] > a >= ints[r']
        }
//        r' - l' > 1 && ints[l'] > a >= ints[r']
        if (a >= ints[mid]) {
//            r' - l' > 1 && a >= ints[mid'] && ints[l'] > a >= ints[r']
            r = mid;
//            r' - l' > 1 && a >= ints[r'] && ints[l'] > a >= ints[r']
            return rekBinSearch(ints, a, l, r);
//            ints[l'] > a >= ints[r']
        }
        return -1;
    }
//    Post: R && ints[R] <= a && ints[R - 1] > a


}
