package SortingAlgorithms.CountingSortLeetProblems;

/* 561. Array Partition I
 * Given an integer array nums of 2n integers, group these integers into n pairs (a1, b1), (a2, b2), ..., (an, bn) such that
 * the sum of min(ai, bi) for all i is maximized. Return the maximized sum.
 */

public class ArrayPartitionI {
    static class Solution {
        public int arrayPairSum(int[] nums) {
            // return value
            int ans = 0;

            // Use counting sort to list out numbers (Include -10^4 and 10^4
            int[] count = new int[20001];
            for (int num : nums) {
                count[num + (10000)]++;
            }

            // Then iterate through count array backwards and add to ans the max value
            int maxNum = nums.length/2;
            boolean min = false;
            for (int i = count.length-1; i >= 0; i--)
            {
                for (int k = 0; k < count[i]; k++)
                {
                    if (maxNum > 0)
                    {
                        if (!min)
                            min = true;
                        else {
                            ans += i-(10000);
                            min = false;
                            maxNum--;
                        }
                    } else return ans;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();

        int[] test1 = {1, 4, 3, 2};
        int[] test2 = {6, 2, 6, 5, 1, 2};
        System.out.println(solution.arrayPairSum(test1));
        System.out.println(solution.arrayPairSum(test2));
    }
}
