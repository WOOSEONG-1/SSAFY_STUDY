import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int parent[];

	static class Edge implements Comparable<Edge> {
		int s, e, cost;
		Edge(int s, int e, int cost) {
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		parent = new int[n+1];
		for ( int i = 0 ; i < m ; i++ ) {
			stk = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(stk.nextToken());
			int e = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			pq.add(new Edge(s, e, c));
			pq.add(new Edge(e, s, c));
		}
		
		sb.append(solve());
		
		
		System.out.print(sb);
	}
	
	static int solve() {
		
		int sumv = 0;
		
		while(!pq.isEmpty()) {
			
			Edge now = pq.poll();
			int a = find(now.s);
			int b = find(now.e);
			
			if ( a == b )				
				continue;
			
			sumv += now.cost;
			union(a, b);			
		}
		
		return sumv;
	}
	
	
	static int find(int tar) {
		
		if ( parent[tar] == 0 )
			return tar;
		else
			return parent[tar] = find(parent[tar]);
	}

	static void union(int a, int b) {
		
		a = find(a);
		b = find(b);
		
		if (a != b)
			parent[a] = b;
	}

}