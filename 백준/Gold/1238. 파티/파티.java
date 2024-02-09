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
    static PriorityQueue<Node> pq;
    static int end, V;

    static class Node implements Comparable<Node> {
        int idx;
        int cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            if (this.cost > other.cost)
                return 1;
            else if (this.cost == other.cost) {
                return 0;
            } else {
                return -1;
            }
        }

    }
    static int dist[],dist_2[];
    static ArrayList<ArrayList<Node>> nodeList = new ArrayList<>();
    static ArrayList<ArrayList<Node>> nodeList_2 = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        // 노드의 개수
        V = Integer.parseInt(stk.nextToken());
        // 간선의 개수
        int E = Integer.parseInt(stk.nextToken());
        // 출발지
        end = Integer.parseInt(stk.nextToken());
        // 그래프
        for ( int i = 0 ; i <= V ; i++ ){
            nodeList.add(new ArrayList<>());
            nodeList_2.add(new ArrayList<>());
        }
        for ( int i = 1 ; i <= E ; i++){
            stk = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(stk.nextToken());
            int t = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            nodeList.get(s).add(new Node(t, c));
            nodeList_2.get(t).add(new Node(s, c));
        }
        // 최단거리 배열
        dist = new int[V + 1];
        dist_2 = new int[V + 1];
        Arrays.fill(dist, INF);
        pq = new PriorityQueue<>();
        // 시작 지점
        dist[end] = 0;
        pq.add(new Node(end, 0));
        // 최단경로 찾기
        dijk();
        pq.add(new Node(end, 0));
        Arrays.fill(dist_2, INF);
        dist_2[end] = 0;
        rev_dijk();
        int maxv = 0;
        for ( int i = 1 ; i <= V; i++){
            maxv = Math.max(maxv, dist[i]+dist_2[i]);
        }
        // 출력
        sb.append(maxv);
        System.out.print(sb);
    }
    static void dijk() {
        while ( !pq.isEmpty() ){
            Node now = pq.poll();
            if( dist[now.idx] < now.cost )
                continue;
            for ( Node next : nodeList.get(now.idx) ){
                if ( dist[next.idx] > dist[now.idx] + next.cost ){
                    dist[next.idx] = dist[now.idx] + next.cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }
    static void rev_dijk() {
        while ( !pq.isEmpty() ){
            Node now = pq.poll();
            if( dist_2[now.idx] < now.cost )
                continue;
            for ( Node next : nodeList_2.get(now.idx) ){
                if ( dist_2[next.idx] > dist_2[now.idx] + next.cost ){
                    dist_2[next.idx] = dist_2[now.idx] + next.cost;
                    pq.add(new Node(next.idx, dist_2[next.idx]));
                }
            }
        }
    }
}