package USACOBronzeProblems;

import java.util.Scanner;

public class AcodemiaI {
    public static void main(String[] args) {
        // reverse counting sort
        Scanner sc = new Scanner(System.in);
        int n, l;

        n = sc.nextInt(); l = sc.nextInt();
        int[] arr = new int[n];
        int[] count = new int[100000];
        for (int i = 0; i < n; i++)
        {
            arr[i] = sc.nextInt();
            count[arr[i]]++;
        }
        for (int i = 100000-1; i > 0; i--)
        {
            count[i-1] = count[i]+count[i-1];
        }

        //4 0
        //1 100 2 3


        // Check difference between each citation value
        int h = 0;
        for (int i = 1; i < arr.length; i++)
        {

            if (count[arr[i]]-count[arr[i-1]] <= l
                    && Math.abs(arr[i]-arr[i-1]) <= 1)
            {
                // check if there are at least k papers with at least k citations
                int citationNumber = Math.min(arr[i], arr[i-1]);
                int papers = count[arr[citationNumber]];
                while (papers >= citationNumber)
                {
                    h = citationNumber;
                    ++citationNumber;
                    papers = count[arr[citationNumber]];
                }


            }
        }
        System.out.println(h);
        // if it is <= L and |citation a- citation b| = 1
        // then check if h value works and iterate until it doesn't work or value increases
        // aka while there are at least k paper with at least k citations
    }
}
