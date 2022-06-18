package DynamicProgramming;

/** only tested once */

public class MatrixChain {

    private final static int INF = 1000000009;
    public static int matrixChain(Matrix[] matrices)
    {
        int length = matrices.length;
        Matrix[][] dp = new Matrix[length][length];
        for (int i = 0; i < matrices.length; i++)
        {
            for (int j = 0; j < matrices.length; j++)
            {
                dp[i][j] = new Matrix(0, 0, INF);
            }
        }
        for (int i = 0; i < matrices.length; i++)
        {
            //System.err.println(matrices[i].rows);
            dp[i][i] = new Matrix(matrices[i].rows, matrices[i].cols,0);
        }
        // The actual length is len + 1
        for (int len = 1; len < matrices.length; len++)
        {
            for (int i = 0; i < matrices.length - len; i++)
            {
                for (int k = i+1; k <= i+len; k++) {
                    int cost = dp[i][k-1].cost + dp[k][i+len].cost
                            + dp[i][k-1].cols * dp[i][k-1].rows * dp[k][i+len].cols;
                    //System.err.println(cost);
                    if (cost < dp[i][i+len].cost) {
                        dp[i][i+len] = new Matrix(dp[i][k-1].rows, dp[k][i+len].cols, cost);
                    }
                }

            }
        }
        print(dp);
        return dp[0][matrices.length-1].cost;
    }

    static void print(Matrix[][] dp)
    {
        for (Matrix[] m : dp)
        {
            for (Matrix matrix : m)
            {
                if (matrix.cost == INF) System.err.print(-1 + " ");
                else System.err.print(matrix.cost + " ");
            }
            System.err.println();
        }
    }

    public static void main(String[] args)
    {
        // Test case from https://www.javatpoint.com/matrix-chain-multiplication-example
        Matrix[] matrices = new Matrix[5];
        matrices[0] = new Matrix(4, 10, 0);
        matrices[1] = new Matrix(10, 3, 0);
        matrices[2] = new Matrix(3, 12, 0);
        matrices[3] = new Matrix(12, 20, 0);
        matrices[4] = new Matrix(20, 7, 0);
        System.out.println(matrixChain(matrices));
    }

    static class Matrix {
        int cols;
        int rows;
        int cost;
        Matrix(int w, int l, int c)
        {
            rows = w;
            cols = l;
            cost = c;
        }
    }
}