#include <bits/stdc++.h>

using namespace std;
using ll = long long;
//#define debug
ll arr[100];
const ll INF = 1e15;
ll dp[100][100][100][7];
// first element of the barn
// current element of the barn
// last door opened
// value of barns - 1 used rn
// dp = min distance cows from first element to current element backwards have to walk

void print(int n, int m)
{
    for (int i = 0; i < n; i++) {
        cout << i << ":\n";
        for (int k = 0; k < m; k++)
        {
            for (int j = 0; j < n; j++)
            {
            
                cout << dp[i][j][0][k] << " ";
            }
            cout << "\n";
        }
        cout << "\n";

    }
}

ll dist(int lastDoor, int curr, int size)
{
    if (curr > lastDoor)
    {
        return curr - lastDoor;
    }
    return (size - lastDoor) + curr;
}

int main() {
    ifstream fin("cbarn2.in");
    ofstream fout("cbarn2.out");

    int n, m;
    fin >> n >> m;
    for (int i = 0; i < n; i++) {
        fin >> arr[i];
    }
    //WHY WON'T MEMESET WORKLDSJFLDSJOF
    //memset(dp, INF, n*n*m*sizeof (int));
    
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            for (int l = 0; l < n; l++) {
                for (int k = 0; k < m ;k++)
                {
                    dp[i][j][l][k] = INF;
                }
            }
        
        }
    }
    
    ll ans = INF;
    for (int i = 0; i < n; i++) {
        int last = i - 1;
        if (last < 0)
            last = n - 1;
        for (int k = 0; k < m; k++) {

            dp[i][i][i][k] = 0;
            // iterate forwards in the array
            if (k == 0) {
                //cout << i << ": \n";
                for (int j = (i + 1) % n; j != i; j++, j %= n) {
                    int pre = j - 1;
                    if (pre < 0)
                        pre = n - 1;
                    dp[i][j][i][0] = arr[j] * dist(i, j, n) + dp[i][pre][i][0];
                    #ifdef debug
                    cout << j << ": " << dp[i][j][i][0] << '\n';
                    #endif
                    if (j == last)
                        ans = min(ans, dp[i][j][i][0]); 
                }
                //cout << "\n";
                
            }
            else {
                for (int j = (i + 1) % n; j != i; j++, j %= n) {
                    
                    for (int l = i; l != j; l++, l %= n) {
                        int pre = j - 1;
                        if (pre < 0)
                            pre = n - 1;
                        dp[i][j][j][k] = min(dp[i][j][j][k], dp[i][pre][l][k - 1]);

                        dp[i][j][l][k] = min(dp[i][j][l][k], arr[j] * dist(l, j, n) + dp[i][pre][l][k]);
                        #ifdef debug
                        //cout << "Using k - 1: " << dp[i][pre][l][k - 1] << "\n";
                        //cout << "Conventional: " << arr[j] * dist(l, j, n) + dp[i][pre][l][k] << "\n";
                        #endif
                        if (j == last && k == m - 1)
                        {
                            ans = min(ans, dp[i][last][l][k]);
                            int befLast = last - 1;
                            if (befLast < 0)
                                befLast = n - 1;
                            if (l == befLast)
                            {
                                ans = min(ans, dp[i][j][j][k]);
                            }
                        }
                    }
                }
            }
        }
    }
    #ifdef debug
    //print(n, m);
    #endif
    fout << ans;
}