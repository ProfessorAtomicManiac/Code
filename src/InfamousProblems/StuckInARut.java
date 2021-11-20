package InfamousProblems;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

/*  USACO 2020 December Contest, Bronze
 *  Problem 3. Stuck in a Rut
 *  This took so lonoooooong (3+ hours)
 *  Pretty sure this solution is pretty inefficient and bad anyways
 */

public class StuckInARut {

    static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter pw = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        // Input
        StringTokenizer st = new StringTokenizer(r.readLine());
        int cows = Integer.parseInt(st.nextToken());

        ArrayList<Tuple> east = new ArrayList<>();
        ArrayList<Tuple> north = new ArrayList<>();

        for (int i = 0; i < cows; i++)
        {
            st = new StringTokenizer(r.readLine());
            if (st.nextToken().toCharArray()[0] == 'E')
                east.add(new Tuple(i, new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))));
            else
                north.add(new Tuple(i, new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))));
        }

        // find all intersection pt (How many moves till the next intersection)
        ArrayList<Duple> inter = new ArrayList<>();

        // compare East pts with North pts
        for (Tuple e : east)
        {
            for (Tuple n : north) {
                // if east.y >= north.y && north.x >= east.x
                if (e.point.y >= n.point.y && n.point.x >= e.point.x) {
                    // record min(east.y-north.y, north.x-east.x)

                    // The rightmost point does not change in that direction
                    inter.add(new Duple(n.cow,e.point.y - n.point.y, n.point, e.point));
                    inter.add(new Duple(e.cow, n.point.x - e.point.x, e.point, n.point));
                }
            }
        }

        // consider possibility that east pt can intersect with other east pts and same for north
        for (int i = 0; i < east.size(); i++)
        {
            for (int j = i+1; j < east.size(); j++)
            {
                if (east.get(i).point.y == east.get(j).point.y) {
                    if (east.get(i).point.x > east.get(j).point.x)
                        inter.add(new Duple(east.get(j).cow, east.get(i).point.x - east.get(j).point.x, east.get(j).point, east.get(i).point));
                    else
                        inter.add(new Duple(east.get(i).cow, east.get(j).point.x - east.get(i).point.x, east.get(i).point, east.get(j).point));
                }
            }
        }

        for (int i = 0; i < north.size(); i++)
        {
            for (int j = i+1; j < north.size(); j++)
            {
                if (north.get(i).point.x == north.get(j).point.x)
                    if (north.get(i).point.y > north.get(j).point.y)
                        inter.add(new Duple(north.get(j).cow, Math.abs(north.get(i).point.y-north.get(j).point.y), north.get(j).point, north.get(i).point));
                    else
                        inter.add(new Duple(north.get(i).cow, Math.abs(north.get(i).point.y-north.get(j).point.y), north.get(i).point, north.get(j).point));

            }
        }
        inter.sort(Comparator.comparingInt((Duple x) -> x.moves));
        //System.err.println(inter);
        // iterate thru intersection pts based on number of steps to get there

        int[] ans = new int[cows];
        ArrayList<Duple> lines = new ArrayList<>();
        Stack<Point> s = new Stack<>();
        for (Duple duple : inter) {
            if (s.search(duple.pt1) != -1)
            {
                continue;
            }
            int moves = duple.moves;
            // see if intersection exists
            //System.err.println(duple);
            // if east hits north
            if (duple.pt1.x + moves == duple.pt2.x && duple.pt1.y < duple.pt2.y+moves && duple.pt1.y >= duple.pt2.y) {
                if (s.search(duple.pt2) == -1) {

                    if (ans[duple.cow] == 0) {
                        ans[duple.cow] = moves;
                        System.err.println("Working: ");
                        System.err.println(duple);
                    }
                    s.add(duple.pt1);
                    //System.err.println(new Duple(-1, moves, duple.pt1, new Point(duple.pt1.x + moves, duple.pt1.y)));
                    lines.add(new Duple(-1, moves, duple.pt1, new Point(duple.pt1.x + moves, duple.pt1.y)));

                } else if (duple.pt1.y >= duple.pt2.y && duple.pt1.y < lines.get(s.size()-s.search(duple.pt2)).pt2.y) {

                    if (ans[duple.cow] == 0) {
                        ans[duple.cow] = moves;
                        System.err.println("Working: ");

                        System.err.println(duple);
                    }
                    //System.err.println(new Duple(-1, moves, duple.pt1, new Point(duple.pt1.x + moves, duple.pt1.y)));
                    s.add(duple.pt1);
                    lines.add(new Duple(-1, moves, duple.pt1, new Point(duple.pt1.x + moves, duple.pt1.y)));
                }
            }
            else if (duple.pt1.y + moves == duple.pt2.y && duple.pt1.x < duple.pt2.x + moves && duple.pt1.x >= duple.pt2.x) {

                if (s.search(duple.pt2) == -1) {
                    if (ans[duple.cow] == 0) {
                        System.err.println("Working: ");
                        System.err.println(duple);
                        ans[duple.cow] = moves;
                    }
                    s.add(duple.pt1);
                    lines.add(new Duple(-1, moves, duple.pt1, new Point(duple.pt1.x, duple.pt1.y + moves)));
                } else if (duple.pt1.x >= duple.pt2.x && duple.pt1.x < lines.get(s.size()-s.search(duple.pt2)).pt2.x) {
                    if (ans[duple.cow] == 0) {
                        ans[duple.cow] = moves;
                        System.err.println("Working: ");

                        System.err.println(duple);
                    }
                    s.add(duple.pt1);
                    lines.add(new Duple(-1, moves, duple.pt1, new Point(duple.pt1.x, duple.pt1.y + moves)));
                }
            }


        }
        for (int an : ans) {
            if (an == 0)
                pw.println("Infinity");
            else pw.println(an);
        }
        pw.close();
    }

    static class Duple {
        int cow;
        int moves;
        Point pt1;
        Point pt2;

        Duple(int cow, int moves, Point pt1, Point pt2)
        {
            this.cow = cow;
            this.moves = moves;
            this.pt1 = pt1;
            this.pt2 = pt2;
        }

        @Override
        public String toString()
        {
            return "Moves: " + moves + " Point 1: (" + pt1.x + "," + pt1.y + ") "+"Point 2: (" + pt2.x + "," + pt2.y + ")\n";
        }
    }

    static class Tuple {
        int cow;
        Point point;

        Tuple(int cow, Point point)
        {
            this.cow = cow; this.point = point;
        }
    }
}
