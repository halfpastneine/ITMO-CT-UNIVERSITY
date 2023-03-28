import java.util.*;
class A {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hs = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hs.containsKey(nums[i])) {
                System.out.println("[" + hs.get(nums[i]) + "," + i + "]");
                break;
            } else {
                hs.put(target - nums[i], i);
            }
        }
    }
}