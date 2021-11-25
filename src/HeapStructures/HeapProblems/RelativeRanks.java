package HeapStructures.HeapProblems;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LeetCode Problem #506 - Relative Ranks
 * You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition. All the scores are guaranteed to be unique.
 *
 * The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:
 *
 * The 1st place athlete's rank is "Gold Medal".
 * The 2nd place athlete's rank is "Silver Medal".
 * The 3rd place athlete's rank is "Bronze Medal".
 * For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
 * Return an array answer of size n where answer[i] is the rank of the ith athlete.
 */
public class RelativeRanks {

    PriorityQueue<Entry> pq = new PriorityQueue<>(new Sort());
    public String[] findRelativeRanks(int[] score) {
        String[] answer = new String[score.length];
        for (int i = 0; i < score.length; i++)
            pq.add(new Entry(score[i], i));
        for (int i = 0; i < score.length; i++)
        {
            Entry temp = pq.poll();
            assert temp != null;
            switch (i) {
                case 0 -> answer[temp.index] = "Gold Medal";
                case 1 -> answer[temp.index] = "Silver Medal";
                case 2 -> answer[temp.index] = "Bronze Medal";
                default -> answer[temp.index] = String.valueOf(i + 1);
            }
        }
        return answer;
    }

    static class Entry
    {
        int val;
        int index;
        Entry(int val, int index)
        {
            this.val = val;
            this.index = index;
        }
    }

    static class Sort implements Comparator<Entry>
    {
        public int compare(Entry a, Entry b)
        {
            return Integer.compare(b.val, a.val);
        }
    }
}
