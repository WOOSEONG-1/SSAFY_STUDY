import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;

/* 
 * BOJ20056: 마법사 상어와 파이어볼 
 * 1. 아이디어
 * 	- 
 * 2. 시간 복잡도
 *	- 
 * 3. 자료구조
 * 	- 배열 
*/

public class Main {
	// 좌표
	static class Point {
		int y, x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public int hashCode() {
			return y + x * 1000;
		}

		@Override
		public boolean equals(Object obj) {
			Point p = (Point) obj;
			return this.y == p.y && this.x == p.x;
		}
	}

	// 파이어볼
	static class Fireball {
		int y, x, mi, s, d;

		Fireball(int y, int x, int mi, int s, int d) {
			this.y = y;
			this.x = x;
			this.mi = mi;
			this.s = s;
			this.d = d;
		}
	}

	// 12시부터 시계방향
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static List<Fireball> lst = new LinkedList<>();
	static int[][] map;
	static int n, k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		// n*n 격자, m개 파이어볼, k번 명령
		n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		// mi : 질량, s : 속력, d = 방향
		for (int i = 0; i < m; i++) {
			stk = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			int mi = Integer.parseInt(stk.nextToken());
			int s = Integer.parseInt(stk.nextToken());
			int d = Integer.parseInt(stk.nextToken());
			lst.add(new Fireball(r - 1, c - 1, mi, s, d));
		}
		game();
		int sumv = 0;
		for (Fireball f : lst)
			sumv += f.mi;
		sb.append(sumv);
		System.out.print(sb);
	}

	// Integer -> 겹친 n개
	static HashMap<Point, Fireball> st = new HashMap<>();

	// Fireball move
	static void game() {
		// 명령 1회시 수행
		for (int i = 0; i < k; i++) {
			map = new int[n][n];

//			for (Fireball f : lst)
//				map[f.y][f.x] = 1;
//			for (int[] ai : map) {
//				for (int j : ai) {
//					System.out.print(j + " ");
//				}
//				System.out.println();
//			}
//			for (Fireball f : lst)
//				System.out.println("y : " + f.y + ", x : " + f.x + " / mi : " + f.mi + ", s : " + f.s + ", d : " + f.d);
//			System.out.println();

			map = new int[n][n];
			// 파이어볼 각각 개체마다 실행
			for (Fireball f : lst) {
				// 기존 좌표 + 속력*방향만큼 이동
				int ny = f.y + dy[f.d] * f.s%n;
				int nx = f.x + dx[f.d] * f.s%n;
				if (ny < 0 || ny >= n || nx < 0 || nx >= n) {
					ny = (n + ny) % n;
					nx = (n + nx) % n;
				}

				f.y = ny;
				f.x = nx;
				// 이동한 좌표에 없으면
				if (map[ny][nx] == 0) {
					st.put(new Point(ny, nx), f);
					map[ny][nx] = 1;
				}
				// 이미 있으면
				else {
					Fireball prev = st.get(new Point(ny, nx));
					int dir = prev.d;
					if (dir != -1) {
						if (prev.d % 2 == 0 && prev.d % 2 == f.d % 2)
							dir = 0;
						else if (prev.d % 2 == 1 && prev.d % 2 == f.d % 2)
							dir = 1;
						else
							dir = -1;
					} 
					Fireball input = new Fireball(ny, nx, f.mi + prev.mi, f.s + prev.s, dir);

					st.put(new Point(ny, nx), input);
					map[ny][nx] += 1;
				}
			}
			// 다음 반복을 위한 초기화
			lst.clear();

			// 이동을 끝낸 후, 파이어볼 분배
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					if (map[r][c] > 1) {
						Fireball now = st.get(new Point(r, c));
						int div_mi = now.mi / 5;
						int div_s = now.s / map[r][c];
						// 질량이 0이 아닌 경우
						if (div_mi != 0) {
							// 방향이 모두 홀수 or 아닌 경우 방향 설정
							if (now.d == -1) {
								for (int a = 1; a < 8; a += 2)
									lst.add(new Fireball(r, c, div_mi, div_s, a));
							} else {
								for (int a = 0; a < 8; a += 2)
									lst.add(new Fireball(r, c, div_mi, div_s, a));
							}
						}
						// 분배한 파이어볼은 hashmap에서 삭제
						st.remove(new Point(r, c));
					}
				}
			}

//			for (int[] ai : map) {
//				for (int j : ai) {
//					System.out.print(j + " ");
//				}
//				System.out.println();
//			}

			// 다음 반복을 위한 초기화
			lst.addAll(st.values());

//			for (Fireball f : lst)
//				System.out.println("y : " + f.y + ", x : " + f.x + " / mi : " + f.mi + ", s : " + f.s + ", d : " + f.d);
//			System.out.println();

			st.clear();
			// 1회 명령 종료
		}

	}
}