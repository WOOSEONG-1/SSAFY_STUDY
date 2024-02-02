import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 * 	- 0 ~ n 까지 큐에 넣고, x+1,-1이면 현재 좌표 + 1 , 2x면 현재좌표값 대입
 * 2. 시간 복잡도
 * 	- O(V+E) = 500K
 * 3. 자료 구조
 * 	- 배열 	( 탐색 : O(N), 삽입:O(1), 삭제 : O(N), 검색 : O(1) 	)
 *  - 큐	( 탐색 : X	 , 삽입:O(1), 삭제 : O(1), 검색 : X		)
 */

public class Main {
	static Queue<Integer> q1 = new LinkedList<>();
	static Queue<Integer> q2 = new LinkedList<>();
	static boolean[] visited;
	static int[] map;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		// 수빈 위치
		int a = Integer.parseInt(stk.nextToken());
		// 동생 위치
		int b = Integer.parseInt(stk.nextToken());
		map = new int[100001];
		visited = new boolean[100001];
		Arrays.fill(map, 100000);
//		map = new int[101];
//		visited = new boolean[101];
		map[a] = 0;
		q1.add(a);
		q2.add(a);
		visited[a] = true;
		if (a > b)
			sb.append(a - b);
		else {
			bfs(b);
			sb.append(ans);
		}
//		for ( int f : map )
//			System.out.print(f+" ");
		
		System.out.print(sb);
	}

	static void bfs(int b) {
		while (!q1.isEmpty()) {
			int now = q1.poll();
			if (now == b) {
				ans = map[now];
				break;
			}
			while (!q2.isEmpty()) {
				int now_2 = q2.poll();
				int nx = now_2 * 2;
				if (nx <= 100000 && !visited[nx]) {
					visited[nx] = true;
					map[nx] = map[now_2];
					q1.add(nx);
					q2.add(nx);
				}
			}
			int[] dx = { -1, 1 };
			for (int k = 0; k < 2; k++) {
				int nx = dx[k] + now;
				if (nx >= 0 && nx <= 100000 && !visited[nx]) {
					visited[nx] = true;
					map[nx] = map[now] + 1;
					q1.add(nx);
					q2.add(nx);
				}
			}

		}
	}
}
