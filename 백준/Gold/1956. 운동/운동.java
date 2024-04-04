import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int INF = 2100000000;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int[][] dist = new int[n + 1][n + 1];
        for ( int i = 0 ; i <= n ; i++ )
            Arrays.fill(dist[i], INF);
        for (int i = 1; i <= m; i++) {
            stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int end = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());
            dist[start][end] = cost;
        }
        for (int v = 1; v <= n; v++) {
            for (int s = 1; s <= n; s++) {
                if ( v==s )
                    continue;
                for (int e = 1; e <= n; e++) {
                    if( v != e && s != e && dist[s][v] != INF &&dist[v][e] != INF )
                        dist[s][e] = Math.min(dist[s][e], dist[s][v] + dist[v][e]);
                }
            }
        }
        int mindist = find_min(dist);
        if (mindist == INF)
            sb.append(-1);
        else
            sb.append(mindist);
        System.out.print(sb);
    }

    static int find_min(int[][] arr) {
        int ans = INF;
        for ( int i = 1 ; i <= n ; i++ ){
            for ( int j = 1 ; j <= n ;j++ ){
                if ( i != j && arr[i][j] != INF && arr[j][i] != INF )
                    ans = Math.min(ans, arr[i][j]+arr[j][i]);
            }
        }
        return ans;
    }
}