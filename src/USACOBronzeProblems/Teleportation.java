package USACOBronzeProblems;

import java.io.*;
//import java.util.Scanner;
import java.util.StringTokenizer;

public class Teleportation {
    static BufferedReader r;
    static PrintWriter pw;
    static {
        try {
            r = new BufferedReader(new FileReader("teleport.in"));
            pw = new PrintWriter(new FileWriter("teleport.out"));
        } catch (IOException e) {}
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(r.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        // find the distance to travel if a to b
        int noTele = Math.abs(a-b);
        // find the distance to travel if a to x then y to b
        int teleX = Math.abs(a-x)+Math.abs(y-b);
        // find the distance to travel if a to y then x to b
        int teleY = Math.abs(a-y)+Math.abs(x-b);
        int result = Math.min(noTele, teleX);
        result = Math.min(result, teleY);
        pw.println(result);
        pw.close();
    }
}
