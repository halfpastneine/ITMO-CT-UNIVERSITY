import java.util.*;

public class A{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder s = new StringBuilder();
        int n = sc.nextInt();
        boolean RF = false;
        boolean AR = false;
        boolean SM = false;
        boolean ASM = true;
        boolean TR = true;
        boolean RF1 = false;
        boolean AR1 = false;
        boolean SM1 = false;
        boolean ASM1 = true;
        boolean TR1 = true;
        int[][] ints = new int[n][n];
        int[][] intq = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ints[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                intq[i][j] = sc.nextInt();
            }
        }
        int wq = 0;
        int qw = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j && ints[i][j] == 1) {
                    wq++;
                    // System.out.println(wq);
                }
                if (i == j && intq[i][j] == 1) {
                    qw++;
                }
            }
        }
        // System.out.println(wq);
        if (wq == n) {
            RF = true;
        }
        if (qw == n) {
            RF1 = true;
        }
        int count2 = 0;
        int count1 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j && ints[i][j] == 0) {
                    count2++;
                    // System.out.println(count2);
                }
                if (i == j && intq[i][j] == 0) {
                    count1++;
                }
            }
        }

        // System.out.println(count2);
        if (count2 == n) {
            AR = true;
        }
        if (count1 == n) {
            AR1 = true;
        }
        int bl = 0;
        int bl1 = 0;
        if (n > 1){
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (ints[i][j] == ints[j][i]) {
                        bl++;
                    }
                    if (intq[i][j] == intq[j][i]) {
                        bl1++;

                    }
                }
            }
            // System.out.println(bl);
            if (bl == n*n) {
                SM = true;
            }
            if (bl1 == n*n) {
                SM1 = true;
            }
        } else {
            SM = true;
            SM1 = true;
        }
        int sk = 0;
        int sk1 = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (ints[i][j] == ints[j][i] && (ints[i][j] == 1)) {
                        ASM = false;
                        // System.out.println(sk);
                    }
                    if ((intq[i][j] == intq[j][i]) && (intq[i][j] == 1)) {
                        ASM1 = false;
                    }
                }
            }
            // if (sk == n*n - n) {
            //     ASM = true;
            // }
            // if (sk1 == n*n - n) {
            //     ASM1 = true;
            // }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if (ints[i][j] == 1){
                    for (int z = 0; z < n; z++){
                        if (ints[j][z] == 1 && ints[i][z] == 0) {
                            TR = false;
                            break;
                        }
                    }
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if (intq[i][j] == 1){
                    for (int z = 0; z < n; z++){
                        if (intq[j][z] == 1 && intq[i][z] == 0) {
                            TR1 = false;
                            break;
                        }
                    }
                }
            }
        }
        // int[][] res = new int[n][n];
        // int[][] res1 = new int[n][n];
        // int[][] res2 = new int[n][n];
        // int[][] res3 = new int[n][n];
        // for(int i = 0; i < n; i++) {
        //     for(int j = 0; j < n; j++) {
        //         res1[i][j]=ints[i][j];
        //         res2[i][j]=intq[i][j];
        //     }
        // }
        // int k2 = 0;
        // int k1 = 0;
        // for(int i = 0; i < n; i++) {
		// 	for(int u = 0; u < n; u++) {
		// 		for(int j = 0; j < n; j++) {
		// 			res[i][u] +=ints[i][j]*res1[j][u];
        //             res3[i][u] +=ints[i][j]*res2[j][u];
		// 		}
        //         System.out.print(res3[i][u]);
		// 	}
        //     System.out.println();
        //
		// }
        // for(int i = 0; i < n; i++) {
		// 	for(int u = 0; u < n; u++) {
        //         if (res[i][u] != 0) {
        //             res[i][u] = 1;
        //         }
        //         if (res3[i][u] != 0) {
        //             res3[i][u] = 1;
        //         }
        //         System.out.print(res3[i][u]);
        //     }
        //     System.out.println();
        // }
        // int t = 0;
        // int t1 = 0;
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         if (res[i][j] == ints[i][j]){
        //             t++;
        //         }
        //         if (res3[i][j] == intq[i][j]){
        //             t1++;
        //         }
        //     }
        // }
        // System.out.println(t +" " + t1);
        // if (t == n*n) {
        //     TR = true;
        // }
        // if (t1 == n*n){
        //     TR1 = true;
        // }

        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         if (i != j) {
        //             for (int k = 0; k < n; k++) {
        //                 if ((ints[i][j] + ints[j][k] == 2) && (k != i) && (k != j)) {
        //                     TR = false;
        //                 }
        //             }
        //         }
        //     }
        // }
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         if (i != j) {
        //             for (int k = 0; k < n; k++) {
        //                 if ((intq[i][j] + intq[j][k] == 2) && (k != i) && (k != j)) {
        //                     TR1 = false;
        //                 }
        //             }
        //         }
        //     }
        // }
        if (RF) {
            s.append("1 ");
        }else s.append("0 ");
        if (AR) {
            s.append("1 ");
        }else s.append("0 ");
        if (SM) {
            s.append("1 ");
        }else s.append("0 ");
        if (ASM) {
            s.append("1 ");
        }else s.append("0 ");
        if (TR) {
            s.append("1");
        }else s.append("0");
        System.out.println(s.toString());
        s = new StringBuilder();
        if (RF1) {
            s.append("1 ");
        }else s.append("0 ");
        if (AR1) {
            s.append("1 ");
        }else s.append("0 ");
        if (SM1) {
            s.append("1 ");
        }else s.append("0 ");
        if (ASM1) {
            s.append("1 ");
        }else s.append("0 ");
        if (TR1) {
            s.append("1");
        }else s.append("0");
        System.out.println(s.toString());
        int count = 0;
        int[][] w = new int[n][n];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                if (ints[i][j] == 1) {
                    for (int k = 0; k < n; k ++) {
                        if (intq[j][k] == 1) {
                            w[i][k] = 1;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                if (w[i][j] != 1) {
                    w[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                System.out.print(w[i][j] + " ");
            }
            System.out.println();
        }
    }
}
