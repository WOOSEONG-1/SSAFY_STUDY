import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Arrays;

/*
 * 1. 아이디어
 *   - 벨만 포드
 * 2. 시간 복잡도
 *   - 시간복잡도 (VE)
 * 3. 자료구조
 *   - 배열, 우선순위 큐
 */

public class Main {
    static long INF = 1000000000;
    static class Node{
        int s, t, cost;
        Node(int s, int t, int cost){
            this.s = s;
            this.t = t;
            this.cost = cost;
        }
    }
    static int V, E;
    static long[] dist, dist_2;
    static ArrayList<Node> e;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int N = Integer.parseInt(br.readLine());

        for ( int tc = 1 ; tc <= N ; tc++ ) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            V = Integer.parseInt(stk.nextToken());
            E = Integer.parseInt(stk.nextToken());
            int W = Integer.parseInt(stk.nextToken());
            e = new ArrayList<>();
            for ( int i = 0 ; i < E+W ; i++ ){
                stk = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(stk.nextToken());
                int t = Integer.parseInt(stk.nextToken());
                int c = Integer.parseInt(stk.nextToken());
                if ( i < E ){
                    e.add(new Node(s, t, c));
                    e.add(new Node(t, s, c));
                }
                else{
                    e.add(new Node(s, t, -c));
                }
            }
            dist = new long[V + 1];
            dist_2 = new long[V + 1];
            Arrays.fill(dist, INF);
            Arrays.fill(dist_2, INF);
            boolean flag = bf(1);
            if ( flag )
                sb.append("YES").append("\n");
            else
                sb.append("NO").append("\n");
        }
        System.out.print(sb);
    }
    static boolean bf(int start){
        dist[start] = 0;
        dist_2[start] = 0;
        // V번 반복
        for ( int i = 1; i <= V ; i++ ){
            // 정점마다 간선 업데이트
            for ( Node now : e ){
                int s = now.s;
                int t = now.t;
                int c = now.cost;
                if ( dist[t] > c + dist[s] ){
                    dist[t] = c + dist[s];
                    if ( i == V )
                        return true;
                    }
            }
        }
        return false;
    }
}