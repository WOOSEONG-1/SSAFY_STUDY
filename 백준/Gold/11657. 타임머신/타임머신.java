import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 아이디어
 *   - 다익스트라
 * 2. 시간 복잡도
 *   - 시간복잡도 (VE)
 * 3. 자료구조
 *   - 배열, 우선순위 큐
 */

public class Main {
    static long INF = 2100000000;
    static class Node{
        int s, t, cost;
        Node(int s, int t, int cost){
            this.s = s;
            this.t = t;
            this.cost = cost;
        }
    }
    static int V, E;
    static long[] dist;
    static Node e[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
//        int N = Integer.parseInt(br.readLine());

//        for ( int t = 1 ; t <= N ; t++ ) {
        StringTokenizer stk = new StringTokenizer(br.readLine());
        V = Integer.parseInt(stk.nextToken());
        E = Integer.parseInt(stk.nextToken());
//        int W = Integer.parseInt(stk.nextToken());
        e = new Node[E];
        for ( int i = 0 ; i < E ; i++ ){
            stk = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(stk.nextToken());
            int t = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            e[i] = new Node(s, t, c);
//            if ( i <= V ){
//                map.get(s).add(new Node(e, c));
//            }
//            else{
//                map.get(s).add(new Node(e, -c));
//            }
        }
        dist = new long[V + 1];
        Arrays.fill(dist, INF);
        boolean flag = bf(1);

//        }
        if (flag)
            sb.append(-1);
        else{
            for ( int i = 2 ; i <= V ; i++ ){
                if ( dist[i] != INF )
                    sb.append(dist[i]).append("\n");
                else
                    sb.append(-1).append("\n");
            }
        }
        System.out.print(sb);
    }
    static boolean bf(int start){
        dist[start] = 0;
        // V번 반복
        for ( int i = 1; i <= V ; i++ ){
            // 정점마다 간선 업데이트
            for ( int j = 0; j < E; j++){
                if (dist[e[j].s] == INF )
                    continue;
                int s = e[j].s;
                int t = e[j].t;
                int c = e[j].cost;
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