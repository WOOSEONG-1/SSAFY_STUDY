import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
* 1. 아이디어
*   - 유니온 파인드, 그래프 탐색
* 2. 시간복잡도
*   - O(n)
* */


public class Main {

    static class Node {
        int idx, root;
        String cmd;
        Node(int idx, String cmd) {
            this.idx = idx;
            this.cmd = cmd;
        }
    }

    static int[] parent;
    static int n, m, cnt = 0;
    static Node[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        map = new Node[n*m+1];
        parent = new int[n*m+1];

        for ( int i = 1 ; i <= n*m ; i++ ){
            parent[i] = i;
        }

        if ( n == 1 && m == 1 )
            cnt = 1;

        else {
            for (int i = 1; i <= n * m; i += m) {
                String line = br.readLine();
                for (int j = 0; j < m; j++) {
                    String s = String.valueOf(line.charAt(j));
                    map[i+j] = new Node(parent[i+j], s);
                }
            }
        }

        for ( int i = 1 ; i <= n*m ; i++ )
            union(map[i]);

        HashSet<Integer> set = new HashSet<>();

        for ( int i = 1 ; i <= n*m ; i++ )
            set.add(parent[i]);

        cnt = set.size();

        sb.append(cnt);
        System.out.print(sb);
    }

    static int find(int a){
        if ( parent[a] == a )
            return a;
        return parent[a] = find(parent[a]);
    }

    static void union(Node node) {
        int b;
        Deque<Node> q = new LinkedList<>();
        q.add(node);

        while( !q.isEmpty() ) {

            Node now = q.poll();
            String cmd = now.cmd;

            if (cmd.equals("U"))
                b = now.idx - m;
            else if (cmd.equals("D"))
                b = now.idx + m;
            else if (cmd.equals("L"))
                b = now.idx - 1;
            else
                b = now.idx + 1;

            q.add(map[b]);
            int a = find(now.idx);
            b = find(b);
            if (a == b)
                return;
            if (a > b)
                parent[a] = b;
            else
                parent[b] = a;
        }
    }
}