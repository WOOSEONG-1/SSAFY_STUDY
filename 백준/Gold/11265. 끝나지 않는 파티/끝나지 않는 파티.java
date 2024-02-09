import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 아이디어
 *   - 다익스트라
 * 2. 시간 복잡도
 *   - O(ElogE)     / N 1 ~ 1000 / E 1000 ~ 10000
 * 3. 자료구조
 *   - 배열, 우선순위 큐
 */

public class Main {
    static int INF = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int[][] map = new int [N+1][N+1];
        for ( int i = 1 ; i <= N; i++ ){
            stk = new StringTokenizer(br.readLine());
            for ( int j = 1 ; j <= N ; j++ ){
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        for ( int v = 1 ; v <= N; v++ ){
            for ( int s = 1 ; s <= N ; s++ ){
                for ( int t = 1 ; t <= N ; t++ ){
                    map[s][t] = Math.min(map[s][t], map[s][v] + map[v][t]);
                }
            }
        }
        for ( int i = 1 ; i <= M; i++ ){
            stk = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(stk.nextToken());
            int t = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            if ( map[s][t] <= c )
                sb.append("Enjoy other party").append("\n");
            else
                sb.append("Stay here").append("\n");
        }
        System.out.print(sb);
    }
}