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
    static int end, cnt;
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
                if ( this.idx < other.cost )
                    return 1;
                else
                    return 0;
            }
            else{
                return -1;
            }
        }

    }
    static int dist[], prev[];
    static ArrayList<ArrayList<Node>> nodeList = new ArrayList<>();
    static StringBuffer sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuffer();
        StringTokenizer stk;

        // 노드의 개수
        int V = Integer.parseInt(br.readLine());

        // 간선의 개수
        int E = Integer.parseInt(br.readLine());

        // 최단거리 배열
        dist = new int[V+1];
        prev = new int[V+1];
        Arrays.fill(dist, INF);
        Arrays.fill(prev,INF);
        pq = new PriorityQueue<>();
        // 노드 정보 리스트 초기화 및 대입
        for ( int i = 0 ; i <= V ; i++ ){
            nodeList.add(new ArrayList<>());
        }
        for ( int i = 0 ; i < E ; i++ ){
            stk = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(stk.nextToken());
            int t = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            nodeList.get(s).add(new Node(t, c));
        }
        // 시작 지점
        stk = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(stk.nextToken());
        dist[start] = 0;
        end = Integer.parseInt(stk.nextToken());
        pq.add(new Node(start, 0));
        // 최단경로 찾기
        dijk();

        // 출력
        System.out.println(dist[end]);
        print_path(prev, end);
        System.out.println(cnt);
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
                    prev[next.idx] = now.idx;
                }
            }
        }

    }
    static void print_path(int[] prev, int end){
        if ( end == INF )
            return;
        cnt += 1;
        print_path(prev, prev[end]);
        sb.append(end).append(" ");
    }
}