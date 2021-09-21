package USACOBronzeProblems;

//import java.util.Scanner;

import java.io.*;
import java.util.StringTokenizer;

public class Test {

    static BufferedReader r;
    static PrintWriter pw;
    static {
        try {
            r = new BufferedReader(new FileReader("word.in"));
            pw = new PrintWriter(new FileWriter("word.out"));
        } catch (IOException e) {}
    }
    public static void main(String[] args) throws IOException
    {
        StringTokenizer st = new StringTokenizer(r.readLine());
        int numOfWords = Integer.parseInt(st.nextToken());
        int max = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(r.readLine());
        int currentChars = 0;
        for (int i = 0;i < numOfWords; i++)
        {
            String currentWord = st.nextToken();
            if ((currentChars + currentWord.length()) > max || currentChars == 0)
            {
                currentChars = 0;
                currentChars += currentWord.length();
                if ((currentChars + currentWord.length()) > max)
                    System.out.println();
                System.out.print(currentWord);
            } else {
                currentChars += currentWord.length();
                System.out.print(" "+currentWord);
            }
        }
    }

}
