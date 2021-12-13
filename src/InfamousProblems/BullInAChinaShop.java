package InfamousProblems;

import java.awt.*;
import java.io.*;
import java.util.*;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=640
// This is the longest solution I've had to date and the atrocious numbers of for loops required
// Surprisely this didn't take as long as I expected (under 2 hours ish).

public class BullInAChinaShop {
    static BufferedReader r;
    static PrintWriter pw;

    static {
        try {
            r = new BufferedReader(new FileReader("bcs.in"));
            pw = new PrintWriter(new FileWriter("bcs.out"));
        } catch (IOException ignored) {
        }
    }

    static int[][] grid;
    static Point[] leftTop;
    static ArrayList<int[][]> pieces;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        grid = new int[n+2*(n-1)][n+2*(n-1)];
        // Setting the "out-of-bounds" area
        for (int i = 0; i < n-1; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                grid[i][j] = -1;
            }
        }
        for (int i = n-1; i < grid.length - n + 1; i++)
        {
            for (int j = 0; j < n - 1; j++)
            {
                grid[i][j] = -1;
                grid[i][grid.length - j - 1] = -1;
            }
        }
        for (int i = grid.length - n + 1; i < grid.length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                grid[i][j] = -1;
            }
        }
        //----------------------------------------------------------------
        // the input
        for (int i = n-1; i < grid.length - n + 1; i++)
        {
            String str = r.readLine();
            for (int j = 0; j < str.length(); j++)
            {
                if (str.charAt(j) == '#')
                {
                    grid[i][n - 1 + j] = 1;
                }
            }
        }

        pieces = new ArrayList<>(k);

        // Note: col = x, row = y
        leftTop = new Point[k];
        for (int i = 0; i < k; i++)
        {
            int[][] piece = new int[n][n];
            boolean foundLeftTop = false;
            for (int row = 0; row < n; row++)
            {
                String str = r.readLine();
                for (int col = 0; col < str.length(); col++)
                {
                    if (str.charAt(col) == '#')
                    {
                        piece[row][col] = 1;
                        if (!foundLeftTop)
                        {
                            leftTop[i] = new Point(col, row);
                            foundLeftTop = true;
                        }
                    }
                }
            }
            pieces.add(piece);
        }

        //-------------------------------------------------

        // Iterate through every possible pair of the pieces
        for (int i = 0; i < k; i++)
        {
            for (int j = i+1; j < k; j++) {
                // Iterate thru every "1" in the grid
                for (int row = n - 1; row < grid.length - n + 1; row++) {
                    for (int col = n - 1; col < grid.length - n + 1; col++) {
                        // Place the "most up/left" point at that position "1"
                        if (grid[row][col] == 1) {
                            if (check(row, col, i, n)) {
                                printGrid(n);

                                // Iterate thru every "1" again since grid is changed
                                for (int r = n - 1; r < grid.length - n + 1; r++) {
                                    for (int c = n - 1; c < grid.length - n + 1; c++) {
                                        if (check(r, c, j, n))
                                        {
                                            System.err.println();
                                            boolean isValid = true;
                                            // Iterate thru the grid to see if there are any 1's left
                                            for (int a = n-1; a < grid.length-n+1; a++)
                                            {
                                                for (int b = n-1; b < grid.length-n+1; b++)
                                                {
                                                    // else keep iterating thru "1"
                                                    if (grid[a][b] == 1) {
                                                        isValid = false;
                                                        break;
                                                    }
                                                }
                                            }
                                            if (isValid) {
                                                printGrid(n);
                                                j++;
                                                i++;
                                                // If no 1's left, that is the answer
                                                if (i > j) pw.println(j + " " + i);
                                                else pw.println(i + " " + j);
                                                pw.close();
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                            resetGrid(n);

                        }
                    }
                }
            }
        }
        pw.close();
    }

    public static boolean check(int row, int col, int piece, int n)
    {
        // Iterate thru the rest of the piece
        for (int pieceRow = leftTop[piece].y; pieceRow < n; pieceRow++) {
            for (int pieceCol = 0; pieceCol < n; pieceCol++) {
                // Does the next piece correspond to a "1" on a grid?
                if (pieces.get(piece)[pieceRow][pieceCol] == 1 && grid[row + pieceRow - leftTop[piece].y][col + pieceCol - leftTop[piece].x] == 1) {
                    // Yes, replace the 1 with 2 then continue.
                    grid[row + pieceRow - leftTop[piece].y][col + pieceCol - leftTop[piece].x] = 2;

                    // The next piece is invalid since it goes out of bounds, lands on "0" or "2"
                } else if (pieces.get(piece)[pieceRow][pieceCol] == 1 && grid[row + pieceRow - leftTop[piece].y][col + pieceCol - leftTop[piece].x] != 1){
                    // No, then continue to search for the next "1" on the grid and reset graph by switching all the 2's back to 1's
                    return false;
                }
                // If it is the last one to check, then check the second piece via int isValid
                if (pieceCol + 1 == n && pieceRow + 1 == n)
                    return true;
            }
        }
        System.err.println("This shouldn't happen");
        return false;
    }

    public static void resetGrid(int n)
    {
        for (int i = n-1; i < grid.length-n+1; i++)
        {
            for (int j = n-1; j < grid.length-n+1; j++)
            {
                if (grid[i][j] == 2)
                    grid[i][j] = 1;
            }
        }
    }

    public static void printGrid(int n)
    {
        for (int i = n-1; i < grid.length-n+1; i++)
        {
            for (int j = n-1; j < grid.length-n+1; j++)
            {
                System.err.print(grid[i][j] + " ");
            }
            System.err.println();
        }
    }
    public static void printGrid(int[][] grid)
    {
        for (int[] ints : grid) {
            for (int j = 0; j < grid.length; j++) {
                System.err.print(ints[j] + " ");
            }
            System.err.println();
        }
    }
}