package SortingAlgorithms.CountingSortLeetProblems;

/* 274. H-Index
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return compute the researcher's h-index.
 * According to the definition of h-index on Wikipedia: A scientist has an index h if h of their n papers have at least h citations each, and the other n − h papers have no more than h citations each.
 * If there are several possible values for h, the maximum one is taken as the h-index.
 */

public class HIndex {
    static class Solution {
        public int hIndex(int[] citations) {
            // Iterate through array to put into counting array
            int[] count = new int[5000];

            // Variable to store answer
            int answer = 0;

            // Also store total number of citations for checking for "at least h citations"
            int maxCitations = 0;

            for (int citation : citations) {
                count[citation]++;
                if (citation > maxCitations)
                    maxCitations = citation;
            }
            // Have count array sum up values less than it (Not equal to)
            // Store it in another array (probably can be optimized but I don't know)
            int[] countLess = new int[maxCitations+1];
            countLess[0] = 0;
            for (int i = 1; i < countLess.length; i++)
            {
                countLess[i] = count[i-1] + countLess[i-1];
            }


            // use if n-h = that value
            for (int h = 0; h < countLess.length; h++)
            {
                if (countLess[h] <= citations.length-h)
                {
                    if (h > answer)
                        answer = h;
                }
            }


            return answer;
        }
    }
    public static void main(String[] args)
    {
        int[] test1 = {3, 0, 6, 1, 5};
        int[] test2 = {1, 3, 1};
        Solution solution = new Solution();
        System.out.println(solution.hIndex(test1));
        System.out.println(solution.hIndex(test2));
    }
}
