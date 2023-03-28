package search;


import java.util.Random;

public class BinarySearchMissing {
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
                System.out.println(iterBinarySearch(ints, num));
//                r' - l' <= 1 && ints[l'] < a <= ints[r']
//                ints[r' - 1] > a > ints[r' + 1}
//                ints[r'] <= a
            } else {
//                random.nextInt() % 2 != 0
//                l' = l; r' = r
                int l = -1;
                int r = args.length - 1;
//                l' = -1; r' = args.length - 1
//                i < j && ints[i] < ints[j]
                    System.out.println(rekBinarySearch(ints, num, l, r));
//                r' - l' <= 1 && ints[l'] < a <= ints[r']
//                ints[r' - 1] > a > ints[r' + 1}
//                ints[r'] <= a
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Out of Bounds " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Not a number " + e.getMessage());
        }
    }
//      Pred: for i: 0...ints.length - 1 ints[i] <= ints[i + 1]
    public static int iterBinarySearch(int[] ints, int a) {
        int l = -1;
        int r = ints.length;
        int mid = (l + r) / 2;
//        l' = -1; r' = ints.length; mid' = (l + r) / 2
//        ints[l'] < a <= ints[r']
//        Inv: ints[l'] <= a <= ints[r'] && r' - l' > 1 && l' >= 0, r' < ints.length;
        while (r - l > 1) {
//           ints[l'] < a <= ints[r'] && r' - l' > 1
            if (a > ints[mid]) {
//                ints[l'] < a <= ints[r'] && r' - l' > 1 && a > ints[mid']
                l = mid;
//                ints[l'] < a <= ints[r'] && r' - l' > 1 && l' = mid && a > ints[l']
//                Exp: a > ints[mid'] && l = mid => a > ints[l']
            }
//            ints[l'] < a <= ints[r'] && r' - l' > 1
            if (a <= ints[mid]) {
//                ints[l'] < a <= ints[r'] && r' - l' > 1 && a <= ints[mid']
                r = mid;
//                ints[l'] < a <= ints[r'] && r' - l' > 1 && a <= ints[r']
//                Exp: a <= ints[mid'] && r = mid => a <= ints[r']
            }
//            ints[l'] < a <= ints[r'] && r' - l' > 1 && l' <= mid' <= r'
//            ints[l'] < a <= ints[r'] && r' - l' > 1 && l' < (l' + r') / 2 < r'
            mid = (l + r) / 2;
//            ints[l'] < a <= ints[r'] && r' - l' > 1 && l' < mid' < r'
//            ints[l'] >= a >= ints[r']
        }
//        ints[l'] < a <= ints[r'] && r' - l' <= 1
        if (r < ints.length) {
            if (a == ints[r]) {
//              a = ints[r'] && r' - l' > 1
                return r;
            } else {
//              a != ints[r'] && r' - l' > 1
                return -r - 1;
            }
        } else {
//            a > ints[r'] && r' - l' > 1
            return -ints.length - 1;
        }
    }
//    Post: 1) (R && ints[R] == a && ints[R - 1] < a) R = R
//          2) (-R - 1) && !∃ ints[R] == a && ints[R - 1] < a < ints[R] && R = -R - 1
//          3) (-ints.length - 1) && ints[ints.length - 1] < a && R = -ints.length - 1

//      Pred: for i: 0...ints.length - 1 ints[i] <= ints[i + 1] && l > -1, r < ints.length
//      && ints[l] <= a <= ints[r] && l < r
    public static int rekBinarySearch(int[] ints, int a, int l, int r) {
//       l' = l; r' = r
//       mid = (l + r) / 2
        int mid = (l + r) / 2;
//        mid' = mid
        if (r - l == 1) {
//            r' - l' == 1 && ints[l'] <= a <= ints[r']
//        ints[r' - 1] < a < ints[r' + 1}
//        ints[r'] >= a
            if (r < ints.length) {
                if (a == ints[r]) {
//              a = ints[r'] && r' - l' == 1
                    return r;
                } else {
//              a != ints[r'] && r' - l' == 1
                    return -r - 1;
                }
            } else {
//            a > ints[r'] && r' - l' == 1
                return -ints.length - 1;
            }
        }
//        r' - l' > 1 && ints[l'] <= a <= ints[r']
        if (a > ints[mid]) {
//            r' - l' > 1 && a > ints[mid'] && ints[l'] < a <= ints[r'] && a < ints[mid']
            l = mid;
//            r' - l' > 1 && a > ints[l'] && ints[l'] < a <= ints[r']
            return rekBinarySearch(ints, a, l, r);
//            ints[l'] < a <= ints[r']
        }
//        r' - l' > 1 && ints[l'] < a <= ints[r']
        if (a <= ints[mid]) {
//            r' - l' > 1 && a <= ints[mid'] && ints[l'] < a <= ints[r']
            r = mid;
//            r' - l' > 1 && a <= ints[r'] && ints[l'] < a <= ints[r']
            return rekBinarySearch(ints, a, l, r);
//            ints[l'] < a <= ints[r']
        }
        return -1;
    }
//    Post: 1) (R && ints[R] == a && ints[R - 1] < a) R = R
//          2) (-R - 1) && !∃ ints[R] == a && ints[R - 1] < a < ints[R] && R = -R - 1
//          3) (-ints.length - 1) && ints[ints.length - 1] < a && R = -ints.length - 1
}
