import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

/*	08:50
 * 1. 아이디어
 * 	- 각 칸에서 BFS 순회, 열린 영역 담기 -> 담긴 영역 꺼내서 값 변경 -> 끝날때까지 재귀
 * 2. 시간 복잡도
 *	- L <= n1 - n2 <= R
 * 3. 자료구조
 * 	- 큐, 배열
 * */
public class Main {
	static class Point {
		int y, x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int n, l, r;
	static int[][] map;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		l = Integer.parseInt(stk.nextToken());
		r = Integer.parseInt(stk.nextToken());
		int ans = 0;
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		sb.append(ans = dfs(0));
		System.out.print(sb);
	}

	static int dfs(int cnt) {
		boolean[][] brr = new boolean[n][n];
		boolean flag = false;
		Deque<Point> q = new ArrayDeque<>();
		ArrayList<Point> lst = new ArrayList<>();
		// 배열 순회
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 방문 판별
				if (!brr[i][j]) {
					brr[i][j] = true;
					lst.add(new Point(i, j));
					q.add(new Point(i, j));
					int sumv = map[i][j];
					// 큐 시작
					while (!q.isEmpty()) {
						Point temp = q.poll();
						for (int k = 0; k < 4; k++) {
							int ny = temp.y + dy[k];
							int nx = temp.x + dx[k];
							if (0 <= ny && 0 <= nx && ny < n && nx < n && !brr[ny][nx]) {
								int diff = Math.abs(map[temp.y][temp.x] - map[ny][nx]);
								if (diff >= l && diff <= r) {
									brr[ny][nx] = true;
									lst.add(new Point(ny, nx));
									q.add(new Point(ny, nx));
									sumv += map[ny][nx];
									flag = true;
								}
							}
						}
					}
					// 연합 값 배분하기
					if (lst.size() > 0) {
						int div = sumv / lst.size();
						for (Point p : lst) {
							map[p.y][p.x] = div;
						}
					}
				}
				q.clear();
				lst.clear();
			}
		}
		if (flag) {
			cnt = dfs(cnt + 1);
		}
		return cnt;
	}

}