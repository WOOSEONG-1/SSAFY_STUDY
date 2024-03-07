import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static class Node {
        int idx, x, y, z;
        Node(int idx, int x, int y, int z){
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge implements Comparable<Edge>{
        int s, e, c;
        Edge(int s, int e, int c){
            this.s = s;
            this.e = e;
            this.c = c;
        }
        @Override
        public int compareTo(Edge o){
            if ( this.c > o.c ) return 1;
            else if ( this.c == o.c ) return 0;
            else return -1;
        }
    }

    static class NodeComparator implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2){
            return o1.x - o2.x;
        }
        public Comparator<Node> compareToY(){
            return (o1, o2) -> o1.y - o2.y;
        }
        public Comparator<Node> compareToZ(){
            return (o1, o2) -> o1.z - o2.z;
        }
    }
    static ArrayList<ArrayList<Node>> dist = new ArrayList<>();
    static int[] parent;
    static ArrayList<Node> node_lst;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        parent = new int[n+1];
        node_lst = new ArrayList<>(n+1);

        for ( int i = 0 ; i <= n ; i++ )
            parent[i] = i;

        for ( int i = 1 ; i <= n ; i++ ){
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int z = Integer.parseInt(stk.nextToken());
            node_lst.add(new Node(i, x, y, z));
        }

        for ( int i = 0 ; i < 3; i++ )
            dist.add(new ArrayList<>(n+1));

        for ( int i = 0 ; i < 3 ; i++ ){
            ArrayList<Node> temp = new ArrayList<>(n+1);
            temp.addAll(node_lst);
            if ( i==0 )
                temp.sort(new NodeComparator());
            else if ( i==1 )
                temp.sort(new NodeComparator().compareToY());
            else
                temp.sort(new NodeComparator().compareToZ());
            dist.get(i).addAll(temp);
        }
        
        sb.append(solve());
        System.out.print(sb);
    }

    static long solve(){
        long sumv = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for ( int i = 0 ; i < n-1 ; i++ ) {
            for ( int j = 0 ; j < 3 ; j++ ){
                Node a = dist.get(j).get(i);
                Node b = dist.get(j).get(i+1);
                int cost;
                if ( j==0 )
                    cost = Math.abs(a.x-b.x);
                else if ( j==1 )
                    cost = Math.abs(a.y-b.y);
                else
                    cost = Math.abs(a.z-b.z);

                pq.offer( new Edge(a.idx, b.idx, cost) );
            }
        }
        while ( !pq.isEmpty() ){
            Edge now = pq.poll();
            int a = find(now.s);
            int b = find(now.e);
            if ( a==b ) continue;
            union(a, b);
            sumv+= now.c;
        }
        return sumv;
    }

    static int find(int a){
        if ( parent[a] == a )
            return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b)
            return;
        if (a > b)
            parent[a] = b;
        else
            parent[b] = a;
    }

}