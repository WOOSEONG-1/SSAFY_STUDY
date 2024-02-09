import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;

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
    static PriorityQueue<Node> pq;

    static class Node implements Comparable<Node> {
        int idx;
        int cost;
        Node(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other){
            if ( this.cost > other.cost )
                return 1;
            else if (this.cost == other.cost ){
                return 0;
            }
            else{
                return -1;
            }
        }

    }
    static int dist[], prev[];
    static ArrayList<ArrayList<Node>> nodeList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());

        // 노드의 개수
        int V = Integer.parseInt(stk.nextToken());

        // 간선의 개수
        int E = Integer.parseInt(stk.nextToken());

        // 최단거리 배열
        dist = new int[V+1];
        prev = new int[V+1];
        Arrays.fill(dist, INF);
        pq = new PriorityQueue<>();
        // 시작 지점
        int start = Integer.parseInt(br.readLine());
        dist[start] = 0;
        // 노드 정보 리스트 초기화 및 대입
        for ( int i = 0 ; i <= V ; i++ ){
            nodeList.add(new ArrayList<>());
        }
        pq.add(new Node(start, 0));

        for ( int i = 0 ; i < E ; i++ ){
            stk = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(stk.nextToken());
            int t = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            nodeList.get(s).add(new Node(t, c));
        }
        // 최단경로 찾기
        dijk();

        // 출력
        for ( int i = 1 ; i <= V ; i++ ){
            if (dist[i] != INF)
                sb.append(dist[i]).append("\n");
            else
                sb.append("INF").append("\n");
        }
        System.out.print(sb);
    }

    static void dijk (){

        while(!pq.isEmpty()){
            Node now = pq.poll();
            if ( dist[now.idx] < now.cost )
                continue;
            for ( Node next : nodeList.get(now.idx) ){
                if( dist[next.idx] > dist[now.idx] + next.cost ){
                    dist[next.idx] = dist[now.idx] + next.cost;
                    pq.add(new Node(next.idx, dist[next.idx]));
                }
            }
        }

    }
}