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
    static int end, V;

    static class Point {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Node implements Comparable<Node> {
        Point p;
        int cost;

        Node(Point p, int cost) {
            this.p = p;
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

    static int dist[][];
    static int arr[][];

    //    static ArrayList<ArrayList<Node>> nodeList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk;
        int t = 1;
        while ( true ) {
            // 노드의 개수
            V = Integer.parseInt(br.readLine());
            if ( V == 0 )
                break;
            // 간선의 개수
            // int E = Integer.parseInt(br.readLine());

            // 최단거리 배열
            dist = new int[V + 1][V + 1];
            for (int i = 0; i <= V; i++)
                Arrays.fill(dist[i], INF);
            pq = new PriorityQueue<>();
            arr = new int[V + 1][V + 1];
            for (int i = 1; i <= V; i++) {
                stk = new StringTokenizer(br.readLine());
                for (int j = 1; j <= V; j++)
                    arr[i][j] = Integer.parseInt(stk.nextToken());
            }
            // 시작 지점
            dist[1][1] = arr[1][1];
            pq.add(new Node(new Point(1, 1), 0));
            // 최단경로 찾기
            dijk();
            // 출력
            sb.append("Problem").append(" ").append(t).append(":").append(" ").append(dist[V][V]).append("\n");
            t += 1;
        }
        System.out.print(sb);
    }

    static void dijk() {
        int dx[] = {1, 0, -1, 0};
        int dy[] = {0, 1, 0, -1};
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            for (int k = 0; k < 4; k++) {
                int ny = now.p.y + dy[k];
                int nx = now.p.x + dx[k];
                if (1 <= ny && ny <= V && 1 <= nx && nx <= V) {
                    if (dist[ny][nx] < now.cost)
                        continue;
                    if (dist[ny][nx] > arr[ny][nx] + dist[now.p.y][now.p.x]) {
                        dist[ny][nx] = arr[ny][nx] + dist[now.p.y][now.p.x];
                        pq.add(new Node(new Point(ny, nx), dist[ny][nx]));
                    }
                }
            }
        }

    }

}