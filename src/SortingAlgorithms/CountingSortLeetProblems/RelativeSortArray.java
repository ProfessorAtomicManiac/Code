package SortingAlgorithms.CountingSortLeetProblems;

/* 1122. Relative Sort Array
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.
 * Elements that do not appear in arr2 should be placed at the end of arr1 in ascending order.
 */

public class RelativeSortArray {
    static class Solution {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            int[] count = new int[1001];
            int[] result = new int[arr1.length];

            for (int element : arr1)
            {
                ++count[element];
            }

            int length = 0;
            int index = 0;

            // Set position of arr2 into arr1
            for (int j : arr2) {
                while (count[j] > 0) {
                    result[index] = j;
                    --count[j];
                    ++index;
                    ++length;
                }
            }

            for (int i = 1; i < count.length; i++)
            {
                count[i] += count[i-1];
            }

            // This part of the code is messy and probably can be optimized/cleaned up, but it works for this problem
            for (int j : arr1) {
                if (j == 0) {
                    if (count[j] > 0) {
                        result[count[j] + length - 1] = j;
                        --count[j];
                    }
                } else {
                    if (count[j] > 0 && count[j - 1] != count[j]) {
                        result[count[j] + length - 1] = j;
                        --count[j];
                    }
                }
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
        Solution solution = new Solution();
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        int[] result1 = solution.relativeSortArray(arr1, arr2);

        int[] arr3 = {28,6,22,8,44,17};
        int[] arr4 = {22,28,8,6};
        int[] result2 = solution.relativeSortArray(arr3, arr4);

        printArray(result1);
        System.out.println();
        printArray(result2);
    }
}
