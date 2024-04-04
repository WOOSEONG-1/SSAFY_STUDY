import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int parent[];

	static class Node {
		double y, x;

		Node(double x, double y) {
			this.y = y;
			this.x = x;
		}
	}

	static class Edge implements Comparable<Edge> {

		int s, e;
		double cost;

		Edge(int s, int e, double cost) {
			this.s = s;
			this.e = e;
			this.cost = cost;
		}

		public int compareTo(Edge o) {
			if ( this.cost > o.cost )
				return 1;
			else if ( this.cost == o.cost )
				return 0;
			else
				return -1;
		}

	}

	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static PriorityQueue<Edge> ex_pq = new PriorityQueue<>();
	static double ans;
	static double[][] map;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(stk.nextToken());
		map = new double[n+1][n+1];				
		parent = new int[n+1];
		for ( int i = 1 ; i <= n ; i++ )
			parent[i] = i;
		
		Node node_lst[] = new Node[n+1];
		
		for ( int i = 1 ; i <= n ; i++ ) {
			stk = new StringTokenizer(br.readLine());
			double ex = Double.parseDouble(stk.nextToken());
			double ey = Double.parseDouble(stk.nextToken());
			node_lst[i] = new Node(ex, ey);
		}
		// 인접 행렬
		for ( int i = 1 ; i <= n ; i++ ) {
			for ( int j = 1 ; j <= n ; j++ ) {
				if ( i == j )
					map[i][j] = 0;
				else {
					double dist = getDist(node_lst[i], node_lst[j]);
					map[i][j] = dist;					
					pq.offer(new Edge(i, j, dist));
				}
			}
		}
		
		ans = 0;
		solve(pq);
		sb.append(String.format("%.2f", ans));
		
		System.out.print(sb);
	}

	// 인접 행렬 이용해서 이미 연결된 간선 MST, 연결할 간선 MST

	static void solve(PriorityQueue<Edge> pq) {
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			int a = now.s;
			int b = now.e;
			a = find(a);
			b = find(b);
			if ( a==b )
				continue;
			union(a, b);
			ans += now.cost;			
		}
	}

	static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

	static void union(int a, int b) {

		a = find(a);
		b = find(b);
		if (a != b)
			if ( a > b )
				parent[a] = b;
			else
				parent[b] = a;
	}

	static double getDist(Node a, Node b) {
		double ans = Math.sqrt((Math.pow(a.y - b.y, 2) + Math.pow(a.x - b.x, 2)));
		return ans;
	}

}