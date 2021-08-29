package SortingAlgorithms.CountingSortLeetProblems;

/* 1051. Height Checker
 * A school is trying to take an annual photo of all the students. The students are asked to stand in a single file line in non-decreasing order by height. Let this ordering be represented by the integer array expected where expected[i] is the expected height of the ith student in line.
 * You are given an integer array heights representing the current order that the students are standing in. Each heights[i] is the height of the ith student in line (0-indexed).
 * Return the number of indices where heights[i] != expected[i].
 */

public class HeightChecker {
    static class Solution {
        public int heightChecker(int[] heights) {
            int[] count = new int[101]; // bound of 1 to 100
            for (int element : heights)
            {
                count[element]++;
            }
            for (int i = 1; i < count.length; i++)
            {
                count[i] += count[i-1];
            }

            int[] expected = new int[heights.length];

            for (int i = heights.length - 1; i >= 0; i--) {
                expected[count[heights[i]]-1] = heights[i];
                --count[heights[i]];
            }

            int unexpected = 0;
            for (int i = 0; i < heights.length; i++)
            {
                if (heights[i] != expected[i])
                {
                    ++unexpected;
                }
            }

            return unexpected;
        }
    }

    public static void main(String[] args)
    {
        Solution solution = new Solution();
        int[] test1 = {1, 1, 4, 2, 1, 3};
        int[] test2 = {5, 1, 2, 3, 4};
        int[] test3 = {1, 2, 3, 4, 5};
        int[] test4 = {2,1,2,1,1,2,2,1};

        System.out.println(solution.heightChecker(test1));
        System.out.println(solution.heightChecker(test2));
        System.out.println(solution.heightChecker(test3));
        System.out.println(solution.heightChecker(test4));
    }
}
