import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	/*
	 * 1. 조건 - (1,1) ~ (N,M) 까지 이동 2. 아이디어 - 0-1 BFS or 단순 BFS 
     * 3. 시간 복잡도 - O(V+E) -> BFS
	 */

	static int INF = 1000000000;

	static class Point {
		int y, x;

		Point(int y, int x) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		int[][] map = new int[M][N];
		boolean[][] visit = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			char[] crr = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(Character.toString(crr[j]));
			}
		}

		int[][] dist = new int[M][N];
		for (int i = 0; i < M; i++) {
			Arrays.fill(dist[i], INF);
		}

		dist[0][0] = map[0][0];
		Deque<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));
		int[] dy = { 1, 0, -1, 0 };
		int[] dx = { 0, 1, 0, -1 };

		while (!q.isEmpty()) {
			Point now = q.poll();
			int ey = now.y;
			int ex = now.x;
			for (int k = 0; k < 4; k++) {
				int ny = ey + dy[k];
				int nx = ex + dx[k];
				if (ny >= 0 && ny < M && nx >= 0 && nx < N) {
					if (dist[ny][nx] > dist[ey][ex] + map[ny][nx]) {
						dist[ny][nx] = map[ny][nx] + dist[ey][ex];
						q.offer(new Point(ny, nx));
					}
				}
			}
		}

		sb.append(dist[M - 1][N - 1]);
		System.out.println(sb);
	}

}