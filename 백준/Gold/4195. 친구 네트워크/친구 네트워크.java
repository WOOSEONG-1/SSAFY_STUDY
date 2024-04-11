import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 *   - 유니온 파인드, 문자열을 HashMap에 저장하여 인덱스를 관리
 *   - input "A" "B"-> HashMap { "A" : 0, "B" : 1 ... }
 * */

public class Main {

    static class Node {
        String name;
        int cnt;

        Node(String name, int cnt) {
            this.name = name;
            this.cnt = cnt;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.name);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return this.name.equals(node.name);
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int n = Integer.parseInt(br.readLine());
        for (int k = 0; k < n; k++) {
            int m = Integer.parseInt(br.readLine());
            parent = new int[m * 2 + 1];
            Node[] lst = new Node[m * 2 + 1];
            HashMap<String, Integer> map = new HashMap<>();
            // 부모 배열 초기화
            for (int i = 1; i < m*2+1; i++) {
                parent[i] = i;
            }
            int idx = 1;
            for (int i = 1; i <= m; i++) {
                StringTokenizer stk = new StringTokenizer(br.readLine());
                String s1 = stk.nextToken();
                String s2 = stk.nextToken();
                if (!map.containsKey(s1)) {
                    map.put(s1, idx);
                    lst[idx] = new Node(s1, 1);
                    idx++;
                }
                if (!map.containsKey(s2)) {
                    map.put(s2, idx);
                    lst[idx] = new Node(s2, 1);
                    idx++;
                }
                union(lst, map.get(s1), map.get(s2));
                if (lst[find(map.get(s1))].cnt == 0)
                    sb.append(lst[find(map.get(s2))].cnt).append("\n");
                else
                    sb.append(lst[find(map.get(s1))].cnt).append("\n");
            }

        }

        System.out.print(sb);
    }

    static int find(int a) {
        if (parent[a] == a)
            return a;
        return parent[a] = find(parent[a]);
    }

    static void union(Node[] lst, int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b)
            return;
        if (a > b) {
            lst[b].cnt += lst[a].cnt;
            lst[a].cnt = 0;
            parent[a] = b;
        } else {
            lst[a].cnt += lst[b].cnt;
            lst[b].cnt = 0;
            parent[b] = a;
        }
    }
}