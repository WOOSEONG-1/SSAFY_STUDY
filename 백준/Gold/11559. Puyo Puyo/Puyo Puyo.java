import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*  1. 아이디어
        - 
    2. 시간복잡도
        - 
    3. 자료 구조
        - 
 */
public class Main {
	static class Point {
		int y, x;
		public ArrayList<Point> path = new ArrayList<Point>();

		Point(int y, int x, ArrayList<Point> path) {
			this.y = y;
			this.x = x;
			this.path = path;
		}

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int cnt;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static String[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		map = new String[12][6];
		for (int i = 0; i < 12; i++) {
			String line = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = Character.toString(line.charAt(j));
			}
		}
		dfs();
		sb.append(cnt);
		System.out.print(sb);
	}

	static void dfs() {
		Deque<Point> q = new ArrayDeque<>();
		boolean visit[][] = new boolean[12][6];
		// 스택 초기화
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (!map[i][j].equals(".")) {
					q.addLast(new Point(i, j, new ArrayList<Point>(Arrays.asList(new Point(i, j)))));
				}
			}
		}
		// 가장 큰 집합 찾기
		ArrayList<Point> puyo_lst = new ArrayList<>();
		// dfs
		while (!q.isEmpty()) {
			Point now = q.pollLast();
			visit[now.y][now.x] = true;
			int pc = now.path.size();
			if (pc >= 4)
				puyo_lst.add(now);
			for (int k = 0; k < 4; k++) {
				int ny = dy[k] + now.y;
				int nx = dx[k] + now.x;
				if (0 <= ny && ny < 12 && 0 <= nx && nx < 6 && !visit[ny][nx]
						&& map[ny][nx].equals(map[now.y][now.x])) {
					visit[ny][nx] = true;
					now.path.add(new Point(ny, nx));
					q.addLast(new Point(ny, nx, now.path));
				}
			}
		}
		// 찾은 경로 제거
		if (!puyo_lst.isEmpty()) {
			for (Point p : puyo_lst) {
				for (Point t : p.path)
					map[t.y][t.x] = ".";
			}
			cnt++;
			map = down(map);
			dfs();
		}

	}

	static String[][] down(String[][] M) {
		String[][] temp = new String[12][6];
		for (int i = 0; i < 12; i++) {
			Arrays.fill(temp[i], ".");
		}
		for (int i = 0; i < 6; i++) {
			int idx = 11;
			for (int j = 11; j >= 0; j--) {
				if (!M[j][i].equals(".")) {
					temp[idx--][i] = M[j][i];
				}
			}
		}
		return temp;
	}

}