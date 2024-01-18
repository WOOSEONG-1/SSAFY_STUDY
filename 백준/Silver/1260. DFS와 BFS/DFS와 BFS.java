import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, v;
	static Queue<Integer> q = new LinkedList<>();
	static int[] rt;
	static boolean[] brr;
	static int[][] arr;
	static StringBuffer sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		v = Integer.parseInt(stk.nextToken());
		arr = new int[n+1][n+1];
		brr = new boolean[n+1];
		for ( int i = 0 ; i < m ; i++) {
			stk = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(stk.nextToken())][Integer.parseInt(stk.nextToken())] = 1; 			
		}
		rt = new int[n];
		// DFS start
		rt[0] = v;
		brr[v] = true;
		sb.append(v).append(" ");
		dfs(1);
		sb.append("\n");
		// BFS start
		Arrays.fill(brr, false);
		q.add(v);
		brr[v] = true;
		sb.append(v).append(" ");
		bfs();
		
		System.out.print(sb);
	}
	
	static void dfs(int depth) {
		
		if ( depth == n ) {			
			return;
		}
		
		for ( int i = 1 ; i <= n ; i++) {
			if( (arr[rt[depth-1]][i] == 1 || (arr[i][rt[depth-1]] == 1 ))
					&& brr[i] == false ) {
				brr[i] = true;
				sb.append(i).append(" ");
				rt[depth] = i;				
				dfs(depth+1);
			}
		}
	}
	
	static void bfs() {
		while(!q.isEmpty()) {
			int vertex = q.poll();
			for ( int i = 1 ; i <= n ; i++) {
				if( ( arr[vertex][i] == 1 || arr[i][vertex] == 1 ) && brr[i] == false ) {
					brr[i] = true;
					sb.append(i).append(" ");
					q.add(i);				
				}				
			}
			bfs();
		}	
	}
	
}