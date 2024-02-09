import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 *   - 분할 정복
 * 2. 시간 복잡도
 *   - O(N^2)
 * 3. 자료구조
 *   - 배열
 */

public class Main {
    static int INF = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        // 노드의 개수
        int n = Integer.parseInt(br.readLine());
        // 간선의 개수
        int m = Integer.parseInt(br.readLine());
        int[][] map = new int[n + 1][n + 1];
        for ( int i = 1 ; i <= n ; i++ )
            Arrays.fill(map[i], INF);

        for (int i = 1; i <= m; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stk.nextToken());
            int to = Integer.parseInt(stk.nextToken());
            int cost = Integer.parseInt(stk.nextToken());
            map[start][to] = Math.min(map[start][to], cost);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j)
                    map[i][j] = 0;
            }
        }
        // via = v , start = s , to = t
        for (int v = 1; v <= n; v++) {

            for (int s = 1; s <= n; s++) {
                // via == start -> pass
                if (s == v)
                    continue;

                for (int t = 1; t <= n; t++) {
                    // via == start, via == to -> pass
                    if ( t == s && t == v)
                        continue;
                    // Update
                    map[s][t] = Math.min(map[s][t], map[s][v] + map[v][t]);
                }
            }
        }

        for ( int i = 1 ; i <= n ; i++ ) {
            for (int j = 1; j <= n; j++){
                if ( map[i][j] == INF )
                    sb.append(0).append(" ");
                else
                    sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}