import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	/*
	 * 1. 조건
	 * 	 - 임의로 주어진 두 정점을 반드시 통과해야함
	 *   - 1번 정점에서 N번 정점으로 최단거리로 이동 
	 *   - 정점의 개수 N ( 2 ~ 800 ), 간선의 개수 E ( 0 ~ 2,000,000 )
	 * 2. 아이디어
	 *   - 1번 기준 다익스트라 -> 정점 a까지의 최단 거리 + 정점 a ~ 정점 b 최단 거리 + 정점 b ~ N 최단 거리
	 * 3. 시간 복잡도 
	 *   - O(VlogE) * 3
	 * */

	static class Node implements Comparable<Node>{
		int idx, cost;
		Node ( int idx, int cost ) {
			this.idx = idx;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node other) {
			return this.cost - other.cost;
		}
	}
	static ArrayList<Node> node_lst[];
	static int INF = 1000000000;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		node_lst = new ArrayList[N+1];
		dist = new int[N+1];
		for ( int i = 0 ; i <= N ; i++ ) {
			node_lst[i] = new ArrayList<>();
		}
		for ( int i = 1 ; i <= M ; i++ ) {
			stk = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(stk.nextToken());
			int to = Integer.parseInt(stk.nextToken());
			int cost = Integer.parseInt(stk.nextToken());
			node_lst[from].add(new Node(to, cost));
			node_lst[to].add(new Node(from, cost));
		}
		int[] must_visit = new int[2];
		stk = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < 2 ; i++ ) {			
			must_visit[i] = Integer.parseInt(stk.nextToken());
		}
		
		dijkstra(N, must_visit[0]);
		int v1_1 = dist[1];
		int v1_N = dist[N];
		int v1_v2 = dist[must_visit[1]];
		dijkstra(N, must_visit[1]);
		int v2_1 = dist[1];
		int v2_N = dist[N];
		
		long path_1 = (long)v1_1 + v1_v2 + v2_N;
		long path_2 = (long)v2_1 + v1_v2 + v1_N;
		long min_path = ( path_1 >= path_2 ) ? path_2 : path_1 ;
		
		if ( min_path >= INF )
			min_path = -1;
		
		sb.append(min_path);
		System.out.println(sb);
	}
	
	static void dijkstra (int n, int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean visit[] = new boolean[n+1];
		Arrays.fill(dist, INF);

		dist[start] = 0;		
		pq.offer(new Node(start, 0));
		while(!pq.isEmpty()) {
			Node now = pq.poll();			
			
			if ( visit[now.idx] )
				continue;
			visit[now.idx] = true;
			
			for ( Node next : node_lst[now.idx] ) {
				int next_c = dist[now.idx] + next.cost;
				if ( dist[next.idx] > next_c ) {
					dist[next.idx] = next_c;
					pq.offer(new Node(next.idx, next_c));
				}
			}
			
		}
	}
	
}
