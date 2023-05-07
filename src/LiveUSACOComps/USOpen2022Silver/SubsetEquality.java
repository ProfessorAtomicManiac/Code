package LiveUSACOComps.USOpen2022Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * <a href="http://www.usaco.org/index.php?page=viewproblem2&cpid=1231">...</a>
 * This is the shittiest solution I've ever seen, and obviously it doesn't work.
 * How did I mess up implementation this badly? IDK but this solution can be way shorter
 * and easier to code.
 */

public class SubsetEquality {

    static ArrayList<Element> sComp = new ArrayList<>();
    static ArrayList<Element> tComp = new ArrayList<>();
    static HashMap<String, Boolean> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String s = br.readLine();
        String t = br.readLine();
        // precompute whether 'a', 'b', ..., 'ab', 'ac', 'ad', .... works

        // store how many of the same letters are at one location
        char prev = '-';
        long prevNum = 0;
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) != prev)
            {
                if (i != 0)
                {
                    sComp.add(new Element(prev, prevNum, i));
                }
                prev = s.charAt(i);
                prevNum = 1;
            } else {
                ++prevNum;
            }

            if (i == s.length() - 1)
                sComp.add(new Element(prev, prevNum, i));
        }

/*
        for (Element e : sComp)
        {
            System.err.println(e.letter + " " + e.number);
        }
        System.err.println();
 */
        prev = '-';
        prevNum = 0;
        for (int i = 0; i < t.length(); i++)
        {
            if (t.charAt(i) != prev)
            {
                if (i != 0)
                {
                    tComp.add(new Element(prev, prevNum, i));
                }
                prev = t.charAt(i);
                prevNum = 1;
            } else {
                ++prevNum;
            }

            if (i == t.length() - 1)
                tComp.add(new Element(prev, prevNum, i));
        }

/*
        for (Element e : tComp)
        {
            System.err.println(e.letter + " " + e.number);
        }
        System.err.println();


 */

        for (char letter = 'a'; letter <= 'r'; letter++)
        {
            map.put(String.valueOf(letter), check(letter, '-'));
            //System.err.println(letter + "  " + map.get(String.valueOf(letter)));
        }
        for (char letter = 'a'; letter <= 'r'-1; letter++)
        {
            for (char letter2 = (char) (letter+1); letter2 <= 'r'; letter2++) {
                String str = String.valueOf(letter) + letter2;
                //System.err.println(letter2);
                map.put(str, check(letter, letter2));
                //System.err.println(str + "  " + map.get(str));
            }
        }

        // then check every combination possible for the following queries
        int q = Integer.parseInt(br.readLine());
        for (int i = 0; i < q; i++)
        {
            String str = br.readLine();
            boolean isValid = true;
            if (str.length() <= 2)
            {
                if (map.get(str))
                    pw.print('Y');
                else pw.print('N');
            } else {
                for (int l = 0; l < str.length()-1; l++)
                {
                    for (int k = l+1; k < str.length(); k++)
                    {
                        String st = String.valueOf(str.charAt(l)) + str.charAt(k);
                        //System.err.println(st);
                        if (!map.get(st))
                        {
                            isValid = false;
                            l = str.length()-1;
                            break;
                        }
                    }
                }
                if (isValid)
                    pw.print('Y');
                else pw.print('N');
            }
        }
        pw.close();
    }

    static boolean check(char a, char b)
    {
        if (b == '-')
        {
            long numS = 0;
            for (Element ele : sComp)
            {
                if (ele.letter == a)
                {
                    numS += ele.number;
                }
            }
            long numT = 0;
            for (Element ele : tComp)
            {
                if (ele.letter == a)
                {
                    numT += ele.number;
                }
            }
            //System.err.println(numS + " " + numT);
            return numS == numT;
        } else {
            if (!map.get(String.valueOf(a)) || !map.get(String.valueOf(b)))
                return false;
            // add everything to priority queue
            PriorityQueue<Element> pqS = new PriorityQueue<>();
            PriorityQueue<Element> pqT = new PriorityQueue<>();
            for (Element ele : sComp)
            {
                //System.err.println(ele.letter);
                if (ele.letter == a || ele.letter == b)
                {
                    //System.err.println("BRUH");
                    pqS.add(ele);
                }
            }
            for (Element ele : tComp)
            {
                if (ele.letter == a || ele.letter == b)
                {
                    //System.err.println("BRUH");
                    pqT.add(ele);
                }
            }

            // determine whether the configuration works
            boolean stopS = false, stopT = false;

            char prevChar = '-';
            if (!pqS.isEmpty())
                prevChar = pqS.peek().letter;
            if (!pqT.isEmpty())
                prevChar = pqT.peek().letter;

            long sumS = 0, sumT = 0;

            while (!pqS.isEmpty() && !pqT.isEmpty())
            {
                //assert pqS.peek() != null;
                if (pqS.peek().letter != prevChar)
                {
                    stopS = true;
                } else {
                    sumS += pqS.poll().number;
                }

                //assert pqT.peek() != null;
                if (pqT.peek().letter != prevChar)
                {
                    stopT = true;
                } else {
                    sumT += pqT.poll().number;
                }

                if (stopS && stopT)
                {
                    if (sumS != sumT)
                        return false;
                    sumS = 0;
                    sumT = 0;
                    if (!pqS.isEmpty())
                        prevChar = pqS.peek().letter;
                    if (!pqT.isEmpty())
                        prevChar = pqT.peek().letter;
                }
            }
            return pqS.isEmpty() && pqT.isEmpty();
        }
    }

    static class Element implements Comparable<Element> {
        char letter;
        long number;
        long index;
        Element(char l, long n, long i)
        {
            letter = l; number = n; index = i;
        }

        @Override
        public int compareTo(Element o) {
            return Long.compare(index, o.index);
        }
    }
}