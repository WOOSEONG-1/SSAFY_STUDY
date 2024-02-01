import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*  1. 아이디어
        - HashMap 구조로 노드 관리 :  Key값 : 단말노드 / Value : 부모노드, 가중치
        - 각 단말노드에서 목표까지의 거리를 구하고, 최장거리 2개만 골라와서 출력
    2. 시간복잡도
        - O(n) = N + a
    3. 자료 구조
        - HashMap -> 탐색 : O(1) /
 */
public class Main {
    static class Node {
        int w;
        int path;
        Node(int path, int w){
            this.path = path;
            this.w = w;
        }
        Node(){
            this.path = 0;
            this.w = 0;
        }
    }
    static ArrayList<Node> tree[];
    static int maxv = 0;
    static int max_node = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        tree = new ArrayList[n+1];
        boolean[] visited = new boolean[n+1];
        for ( int i = 1 ; i <= n ; i++ ){
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < n ; i++) {
            stk = new StringTokenizer(br.readLine());
            int root = Integer.parseInt(stk.nextToken());
            while ( true ){
                int path = Integer.parseInt(stk.nextToken());
                if ( path == -1 )
                    break;
                else{
                int w = Integer.parseInt(stk.nextToken());
                tree[root].add(new Node(path, w));
                tree[path].add(new Node(root, w));
                }
            }
        }
        int ans;
        go_tree(visited, 1, 0);
        maxv = 0;
        Arrays.fill(visited, false);
        go_tree(visited, max_node, 0);
        ans = maxv;
        sb.append(ans);
        System.out.print(sb);
    }

    static void go_tree(boolean[] visited, int start, int sum) {
        visited[start] = true;
        for (Node n : tree[start]) {
            if (!visited[n.path]) {
                go_tree(visited, n.path, sum + n.w);
            }
        }
        if (sum > maxv) {
            max_node = start;
            maxv = sum;
        }
    }
}