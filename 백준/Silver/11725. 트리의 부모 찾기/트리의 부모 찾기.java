import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * 1. 아이디어
 *  바이러스의 위치를 큐에 대입 -> 조합 -> 퍼트리기 -> 최소 시간 갱신
 *  
 * 2. 시간복잡도	( N, M 1 ~ 8 ) 
 * 	N 5 ~ 50 , M 1 ~ 10
 * 	64 C 3 * 64 * 5 = 13,000k   
 * 3. 자료 구조
 * 	
 */

public class Main {
	static Queue<Integer> q = new LinkedList<>();
	static boolean[] visited;
	static List<Integer> lst[];
	static int[] res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stk.nextToken());
		lst = new ArrayList[N + 1];
		for (int i = 1; i < lst.length; i++) {
			lst[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < N - 1; i++) {
			stk = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			lst[p].add(c);
			lst[c].add(p);
		}
		visited = new boolean[N + 1];
		res = new int[N + 1];
		visited[1] = true;
		q.add(1);
		bfs();
		for (int ans : res) {
			if( ans != 0 )
				sb.append(ans).append("\n");
		}
		System.out.print(sb);
	}

	static void bfs() {
		while (!q.isEmpty()) {
			int n = q.poll();
			for (int a : lst[n]) {
				if (!visited[a]) {
					visited[a] = true;
					res[a] = n;
					q.add(a);
				}
			}

		}
	}
}