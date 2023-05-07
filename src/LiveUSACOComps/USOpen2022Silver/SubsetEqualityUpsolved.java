package LiveUSACOComps.USOpen2022Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * <a href="http://www.usaco.org/index.php?page=viewproblem2&cpid=1231">...</a>
 * Ironically a day or two later in the middle of school, I took like 40 minutes to write this solution
 * which worked. IDK what my competition self was doing, but he struggled to do it for 2+ hours and the solution
 * is at least 3x the length of this one.
 */

public class SubsetEqualityUpsolved {

    static boolean[][] pairs = new boolean[20][20];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        pairs[0][0] = true;
        String s = br.readLine();
        String t = br.readLine();
        // precompute whether 'a', 'b', ..., 'ab', 'ac', 'ad', .... works
        for (char i = 'a'; i <= 'r'; i++)
        {
            StringBuilder s1 = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            for (int k = 0; k < s.length(); k++)
            {
                if (s.charAt(k) == i)
                    s1.append(s.charAt(k));
            }
            for (int k = 0; k < t.length(); k++)
            {
                if (t.charAt(k) == i)
                    s2.append(t.charAt(k));
            }
            if ((s1.toString()).equals(s2.toString())) {
                pairs[i - 'a' + 1][0] = true;
                pairs[0][i - 'a' + 1] = true;
            }
        }

        for (char i = 'a'; i < 'r'; i++)
        {
            for (char j = (char) (i+1); j <= 'r'; j++)
            {
                StringBuilder s1 = new StringBuilder();
                StringBuilder s2 = new StringBuilder();
                for (int k = 0; k < s.length(); k++)
                {
                    if (s.charAt(k) == i || s.charAt(k) == j)
                        s1.append(s.charAt(k));
                }
                for (int k = 0; k < t.length(); k++)
                {
                    if (t.charAt(k) == i || t.charAt(k) == j)
                        s2.append(t.charAt(k));
                }
                if ((s1.toString()).equals(s2.toString()))
                {
                    pairs[i - 'a'+1][j - 'a'+1] = true;
                    pairs[j - 'a'+1][i - 'a'+1] = true;
                }
            }
        }

        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++)
        {
            String str = br.readLine();
            boolean works = true;
            for (int j = 0; j < str.length(); j++)
            {
                if (str.length() > 1) {
                    for (int k = j + 1; k < str.length(); k++) {
                        works = pairs[str.charAt(j) - 'a' + 1][str.charAt(k) - 'a' + 1];
                        if (!works) {
                            j = str.length();
                            break;
                        }
                    }
                } else {
                    works = pairs[str.charAt(j) - 'a' + 1][0];
                }
            }
            if (i == q - 1)
            {
                pw.println(works ? "Y" : "N");
            } else pw.print(works ? "Y" : "N");
        }

        pw.close();
    }
}