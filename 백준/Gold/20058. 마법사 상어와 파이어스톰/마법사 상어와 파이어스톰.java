import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;

/* 
 * 
 * 1. 아이디어
 * 	- 2^L * 2^L 격자로 나눔 -> 나눈 배열을 90도 회전 -> 각 정점에서 인접 여부 검사
 *  - for문으로 Q번 반복 -> 배열 회전 및 얼음 감소
 * 2. 시간 복잡도
 *	- 
 * 3. 자료구조
 * 	- 배열
*/

public class Main {
	// n : 크기, q : 명령의 수, l : 단계
	static int n, q, n2, sumv, big_ice;
	static int[] l;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int[][] map;

	static class Point {
		int y, x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		// 필요한 값 대입
		n = Integer.parseInt(stk.nextToken());
		q = Integer.parseInt(stk.nextToken());
		n2 = (int) Math.pow(2, n);
		map = new int[(int) Math.pow(2, n)][(int) Math.pow(2, n)];

		for (int i = 0; i < n2; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < n2; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		l = new int[q];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < q; i++) {
			l[i] = Integer.parseInt(stk.nextToken());
		}
		// 알고리즘 실행
		firestorm(map);
		sb.append(sumv).append("\n").append(big_ice);
		System.out.print(sb);
	}

	// 파이어스톰 실행
	static void firestorm(int[][] map) {
		// 회전용 배열
		int[][] temp = new int[n2][n2];
		// 명령을 q번 반복
		for (int i = 0; i < q; i++) {	
			// 배열 회전하기
			rotate(temp, (int) Math.pow(2, l[i]), n2, 0, 0);
			// 회전된 배열 복사 
			for ( int j = 0 ; j < n2 ; j++ )
				map[j] = temp[j].clone();			
			// 얼음 녹이기
			for (int a = 0; a < n2 ; a++) {
				for (int b = 0; b < n2 ; b++) {
					int cnt = 0;
					// 현재 칸에 얼음이 있다면 실행
					if (map[a][b] > 0) {
						// 인접 검사
						for (int k = 0; k < 4; k++) {
							int ny = a + dy[k];
							int nx = b + dx[k];
							if (ny >= 0 && nx >= 0 && nx < n2 && ny < n2 && map[ny][nx] > 0)
								cnt += 1;
						}
						// 주변 얼음이 3 미만이면
						if (cnt < 3) {
							temp[a][b] -= 1;
						}
					}
				}
			}
			// 녹은 배열 복사 
			for ( int j = 0 ; j < n2 ; j++ )
				map[j] = temp[j].clone();	
//			test code
//			System.out.println("Phase : " + (i + 1)+", l : "+l[i]);
//			for (int a = 0; a < n2; a++) {
//				for (int b = 0; b < n2; b++)
//					System.out.print(map[a][b] + " ");
//				System.out.println();
//			}
//			System.out.println();

		}
		// 반복 종료
		// 남은 얼음 구하기
		sumv = 0;
		big_ice = 0;
		Deque<Point> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[n2][n2];
		for (int i = 0; i < n2; i++) {
			for (int j = 0; j < n2; j++) {
				if (map[i][j] != 0) {
					sumv += map[i][j];
					if (!visit[i][j]) {
						q.add(new Point(i, j));
						visit[i][j] = true;
					}
				}

				// 가장 큰 얼음덩이 구하기
				int big_ice_cnt = 1;
				while (!q.isEmpty()) {
					Point now = q.pollLast();
					visit[now.y][now.x] = true;
					for (int k = 0; k < 4; k++) {
						int ny = dy[k] + now.y;
						int nx = dx[k] + now.x;
						if (ny >= 0 && nx >= 0 && ny < n2 && nx < n2 && !visit[ny][nx] && map[ny][nx]>0) {
							visit[ny][nx] = true;
							q.add(new Point(ny, nx));
							big_ice_cnt += 1;
						}
					}
				}
				if ( big_ice_cnt == 1)
					big_ice_cnt = 0;
				big_ice = Math.max(big_ice_cnt, big_ice);
			}
		}
	}

	// 배열 회전 ( pow : 목표치, k : 현재 값, y,x : 기준 좌표 )
	static void rotate(int[][] temp, int pow, int k, int y, int x) {

		if (pow == k) {
			for (int i = 0; i < pow; i++) {
				for (int j = 0; j < pow; j++) {
					temp[i + y][j + x] = map[pow-1-j+y][x + i];
//					temp[i + y][j + x] = map[y + j][x + k - i - 1];
				}
			}
			return;
		}
		// 1,2,3,4분면 나누기
		else {
			rotate(temp, pow, k / 2, y + k / 2, x + k / 2);
			rotate(temp, pow, k / 2, y + k / 2, x);
			rotate(temp, pow, k / 2, y, x);
			rotate(temp, pow, k / 2, y, x + k / 2);
		}

	}
}
