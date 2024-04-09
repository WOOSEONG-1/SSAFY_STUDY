import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Arrays;
/*
 * 1. 아이디어
 *   - DFS + 백트래킹 + DP
 *   - (0, 0) -> (n-1, n-1) 진행하며 끝에 도달할 경우 return 1
 *   - dp (0, 0)에서 합산
 * 2. 시간 복잡도
 *   - 2sec
 * */

public class Main {

    static int[][] map, dp;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // 초기값 input
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        map = new int[n][m];
        dp = new int[n][m];
        // dp 초기화
        for ( int i = 0 ; i < n ; i++ )
            Arrays.fill(dp[i], -1);

        dp[n-1][m-1] = 1;

        // 배열 input
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        br.close();

        // 풀이
        dfs(0, 0);

        // 출력
        sb.append(dp[0][0]).append("\n");
        System.out.print(sb);
    }


    static int[] dy = {1, 0, 0, -1};
    static int[] dx = {0, 1, -1, 0};

    static void dfs(int y, int x) {

        if ( y == n-1 && x == m-1 )
            return;

        if ( dp[y][x] != -1 )
            return;

        dp[y][x] = 0;

        for ( int k = 0 ; k < 4 ; k++ ){
            int ny = y + dy[k];
            int nx = x + dx[k];

            if ( ny < 0 || nx < 0 || ny >= n || nx >= m )
                continue;

            if ( map[ny][nx] < map[y][x] ){
                if ( dp[ny][nx] != -1 )
                    dp[y][x] += dp[ny][nx];
                else {
                    dfs(ny, nx);
                    dp[y][x] += dp[ny][nx];
                }
            }
        }
    }
}