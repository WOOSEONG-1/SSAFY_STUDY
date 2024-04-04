import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 * 	- 유니온 파인드, BFS
 *  - while  (조건: 백조 간의 parent 확인) -> ( 다르면 빙판 녹이기 )
 *  - 첫 BFS는 모든 '.' 기준 BFS, 다음부턴 추가된 '.' 기준 BFS 
 *  - 2,250,000 * 5
 * 2. 조건
 *  - R, C 1 ~ 1500
 *  - 백조의 이동 -> 상하좌우 ( 한 쪽에서만 BFS )
 *  - 
 * */
public class Main {

	static class Point {
		int y, x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int n, m;
	static int[] parent;
	static char[][] map;
	static Deque<Point> q = new LinkedList<>();
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		map = new char[n][m];
		parent = new int[n * m];
		for (int i = 0; i < n * m; i++) {
			parent[i] = i;
		}

		ArrayList<Point> target = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			char[] crr = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				map[i][j] = crr[j];
				if (map[i][j] == '.') {
					q.add(new Point(i, j));
				} else if (map[i][j] == 'L') {
					target.add(new Point(i, j));
					q.add(new Point(i, j));
				}
			}
		}

		init();

		int cnt = 0;
		while (!check(target)) {
			Deque<Point> temp = new LinkedList<>();
			
			while (!q.isEmpty()) {
				Point now = q.poll();
				int ey = now.y;
				int ex = now.x;
				for (int k = 0; k < 4; k++) {
					int ny = ey + dy[k];
					int nx = ex + dx[k];
					if (ny >= 0 && nx >= 0 && ny < n && nx < m && map[ny][nx] != '.') {
						union(ny * m + nx, ey * m + ex);
						if ( map[ny][nx] == 'X') {
							for ( int t = 0 ; t < 4 ; t++ ) {		
								int qy = ny+dy[t];
								int qx = nx+dx[t];
								if (qy >= 0 && qx >= 0 && qy < n && qx < m && map[qy][qx] == '.') {
									union(ny*m + nx , qy * m + qx);
								}
							}
						}
						temp.add(new Point(ny, nx));
						map[ny][nx] = '.';
					}
				}

			}
			
			q.addAll(temp);
			temp.clear();
			cnt++;
		}

		sb.append(cnt);
		System.out.print(sb);
	}

	static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b)
			return;
		else if (a > b)
			parent[a] = b;
		else
			parent[b] = a;
	}

	static boolean check(ArrayList<Point> t) {
		int node_1 = t.get(0).y * m + t.get(0).x;
		int node_2 = t.get(1).y * m + t.get(1).x;
		if (find(node_1) == find(node_2))
			return true;
		else
			return false;
	}

	static void init() {
		boolean[][] visit = new boolean[n][m];
		visit[0][0] = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if ( map[i][j] == 'X')
					continue;
				for (int k = 0; k < 4; k++) {
					int ny = i + dy[k];
					int nx = j + dx[k];
					if (ny >= 0 && nx >= 0 && ny < n && nx < m && !visit[ny][nx]) {
						if (map[ny][nx] != 'X')
							union(i * m + j, ny * m + nx);
					}
				}

			}
		}
	}

}