package LiveUSACOComps.December2022Silver;

import java.io.*;
import java.util.*;

// 13/20 test cases correct

public class CircularBarn {

    static char[] whoWins = new char[5000001];
    static int[] numMoves = new int[5000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        whoWins[0] = 'N';
        numMoves[0] = 1;
        whoWins[1] = 'J';
        numMoves[1] = 1;
        TreeSet<Integer> modOne = new TreeSet<>();
        modOne.add(1);
        TreeSet<Integer> modThree = new TreeSet<>();
        for (int i = 2; i < 5000001; i++)
        {
            if (numMoves[i] == 0)
            {
                if ((i - 1) % 4 == 0)
                    modOne.add(i);
                else if ((i - 3) % 4 == 0)
                    modThree.add(i);
                // this num is prime
                numMoves[i] = 1;
                whoWins[i] = 'J';
                int j = i;
                while (j + i < 5000001) {
                    j += i;
                    numMoves[j] = 2;
                }
            } else {
                int m = i % 4;
                switch (m)
                {
                    case 0:
                        numMoves[i] = numMoves[i - 2] + 1;
                        whoWins[i] = 'N';
                        break;
                    case 1:
                        assert modOne.lower(i) != null;
                        numMoves[i] = numMoves[i - modOne.lower(i)] + 1;
                        whoWins[i] = 'J';
                        break;

                    case 2:
                        numMoves[i] = numMoves[i - 2] + 1;
                        whoWins[i] = 'J';
                        break;

                    case 3:
                        assert modOne.lower(i) != null;
                        numMoves[i] = numMoves[i - modThree.lower(i)] + 1;
                        whoWins[i] = 'J';
                        break;

                }
            }
        }
        //print(13);


        int t = Integer.parseInt(br.readLine());
        while (t-- > 0)
        {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int minMoves = Integer.MAX_VALUE;
            char winner = 'J';
            for (int i = 0; i < n; i++)
            {
                int cows = Integer.parseInt(st.nextToken());
                if (numMoves[cows] < minMoves)
                {
                    minMoves = numMoves[cows];
                    winner = whoWins[cows];
                }
            }
            if (winner == 'J')
                pw.println("Farmer John");
            else
                pw.println("Farmer Nhoj");
        }
        pw.close();
    }

    static void print(int n)
    {
        for (int i = 0; i < n; i++)
        {
            System.err.print(i + " ");
        }
        System.err.println();
        for (int i = 0; i < n; i++)
        {
            System.err.print(numMoves[i] + " ");
        }
        System.err.println();
        for (int i = 0; i < n; i++)
        {
            System.err.print(whoWins[i] + " ");
        }
    }
}