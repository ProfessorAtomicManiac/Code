package SortingAlgorithms.CountingSortLeetProblems;

/* 912. Sort an Array
 * Given an array of integers nums, sort the array in ascending order.
 */

public class SortAnArray {
    static class Solution {
        public int[] sortArray(int[] nums) {
            int[] result = new int[nums.length];

            int[] count = new int[100001];
            for (int num : nums) {
                count[num + (50000)]++;
            }
            for (int i = 1; i < count.length; i++)
            {
                count[i] += count[i-1];
            }
            for (int num : nums) {
                result[count[num + 50000] - 1] = num;
                --count[num + 50000];
            }
            return result;
        }
    }

    public static void printArray(int[] result)
    {
        System.out.print("[");
        for (int i = 0; i < result.length; i++)
        {
            System.out.print(result[i]);
            if (i != result.length-1){
                System.out.print(',');
            }
        }
        System.out.print("]");
    }

    public static void main(String[] args)
    {
        int[] arr1 = {5,2,3,1};
        int[] arr2 = {5,1,1,2,0,0};

        Solution solution = new Solution();
        printArray(solution.sortArray(arr1));
        System.out.println();
        printArray(solution.sortArray(arr2));

    }
}
