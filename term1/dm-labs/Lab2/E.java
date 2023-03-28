import java.util.*;

public class E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        // Integer[] ans = new Integer[str.length()];
        List<Integer> ans = new ArrayList<>();
        int count = 0;
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < 26; i++){
            map.put(Character.toString((char)(97 + i)), count);
            count++;
        }
        int count1 = 0;
        // if (map.containsKey(str.charAt(0))) {
        //     ans.add(map.get(str.charAt(0)));
        //     count1++;
        // }
        int op = 0;
        int cnt = 0;
        int m = 0;
        boolean tr = true;
        // int qqq = 0;
        StringBuilder tmp = new StringBuilder();
        while (cnt < str.length()) {
            tmp = new StringBuilder();
            if (cnt == str.length() - 1) {
                if (tr) {
                    tmp.append(str.charAt(cnt));
                    // System.out.println(map.get("ab"));
                    // System.out.println(count1);
                    ans.add(map.get(tmp.toString()));
                    break;
                }
            } else {
                tmp.append(str.charAt(cnt));
                tmp.append(str.charAt(cnt + 1));
                m = 0;
                // if (map.containsKey(tmp.toString())) {
                //     int m = cnt + 2;
                    int h = cnt + 2;
                    while (map.containsKey(tmp.toString())) {
                        if (h == str.length()) {
                            if (map.containsKey(tmp.toString())) {
                                ans.add(map.get(tmp.toString()));
                                 tr = false;
                            }
                            break;
                        }
                        tmp.append(str.charAt(h));
                        h++;
                        // System.out.println(tmp.toString());
                        m = tmp.length() - 1;
                    }
                    if (!map.containsKey(tmp.toString())) {
                        ans.add(map.get(tmp.toString().substring(0, tmp.length() - 1)));
                        // System.out.println(tmp.toString());
                        map.put(tmp.toString(), count);
                        count++;

                        count1++;
                        tmp = new StringBuilder();
                    }
                    cnt = h - 1;
                    if (!tr) {
                        cnt = h;
                    }
                // if (m > 0) {
                //     cnt += m;
                // } else {
                //     cnt++;
                // }
                // cnt = m;
                // System.out.println(cnt);
                    // for (int i = cnt + 1; i < str.length(); i++) {
                    //     tmp.append(str.charAt(i));
                    //     if (map.containsKey(tmp.toString())) {
                    //
                    //     }
                    // }
                // }
                // tmp.append(str.charAt(cnt));
            //     for (int i = cnt + 1; i < str.length(); i++) {
            //         op = i;
            //         tmp.append(str.charAt(i));
            //         // System.out.println(map.get("ab"));
            //         // System.out.println(tmp.toString());
            //         if (map.containsKey(tmp.toString())) {
            //             ans.add(map.get(tmp.toString()));
            //             count1++;
            //             // qqq++;
            //             // System.out.println(2);
            //         } else {
            //             // System.out.println(10);
            //             map.put(tmp.toString(), count);
            //             count++;
            //             if (tmp.length() <= 2) {
            //                 ans.add(map.get(tmp.toString().substring(0, 1)));
            //                 count1++;
            //             }
            //             // System.out.println(1);
            //             tmp = new StringBuilder();
            //             break;
            //         }
            //     }
            //     // System.out.println(9);
            //     cnt = op;
            }
        }
        // System.out.println(map.get("bac"));

        // int count1 = 0;
        // for (int i = 1; i < str.length(); i++) {
            // StringBuilder tmp = new StringBuilder();
        //     int cnt = i;
        //     if (i == str.length() - 1) {
        //         tmp.append(str.charAt(i));
        //     } else {
        //         tmp.append(str.charAt(i)).append(str.charAt(i + 1));
        //     }
        // int cnt = 0;
        // int count1 = 0;
        // int o = 0;
        // StringBuilder tmp = new StringBuilder();
        // while (cnt < str.length()) {
        //     if (cnt == str.length() - 1 ) {
        //          tmp.append(str.charAt(cnt));
        //     } else {
        //          tmp.append(str.charAt(cnt)).append(str.charAt(cnt + 1));
        //     }
        //     for (int i = cnt; i < str.length(); i++) {
        //         if (map.containsKey(tmp.toString())) {
        //             ans[count1] = map.get(tmp.toString());
        //             o = tmp.length() - 1;
        //             tmp.append(str.charAt(i));
        //         } else {
        //             map.put(tmp.toString(), count);
        //             count++;
        //             System.out.println(tmp.toString() + " " + 2);
        //             String s = tmp.toString();
        //             System.out.println(s + "" +1);
        //             String h = s.substring(0, s.length() - 1);
        //             System.out.println(h + " " + s.length() + " " + cnt);
        //             ans[count1] = map.get(h);
        //             tmp = new StringBuilder();
        //         }
        //     }
        //     tmp = new StringBuilder();
        //     if (o > 0) {
        //         cnt += o;
        //     } else {
        //         cnt++;
        //     }
        // }

            // int cnt = 0;
            // int o = 0;
            // StringBuilder tmp = new StringBuilder();
            //
            // while (cnt < str.length()) {
            //     if (cnt == str.length() - 1) {
            //         tmp.append(str.charAt(cnt));
            //     } else {
            //         tmp.append(str.charAt(cnt)).append(str.charAt(cnt + 1));
            //     }
            //     if (map.containsKey(tmp.toString())) {
            //         ans[count1] = map.get(tmp.toString());
            //         o = tmp.length() - 1;
            //         cnt++;
            //     } else {
            //         map.put(tmp.toString(), count);
            //         count++;
            //         if (cnt > 0) {
            //             cnt += o;
            //         } else {
            //             cnt++;
            //         }
            //         // cnt += o;
            //         o = 0;
            //         String s = tmp.toString();
            //         String h = s.substring(0, s.length() - 1);
            //         System.out.println(h + " " + s + " " + cnt);
            //         ans[count1] = map.get(h);
            //         tmp = new StringBuilder();
            //     }
            // //     // cnt++;
            // //     count1++;
            // }
        // }
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }
}
