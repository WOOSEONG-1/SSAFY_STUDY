import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * 1. 아이디어
 *  정상 : R, G, B 각각 따로 BFS count 
 *  색약 : ( R+G), B 로 BFS count
 * 2. 시간복잡도	( N, M 1 ~ 8 ) 
 * 	O(V+E)
 * 3. 자료 구조
 * 	큐
 */

public class Main {
	static class Point {
		int x, y;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static Queue<Point> red, green, blue;
	static boolean[][] brr;
	static int r_cnt, b_cnt, g_cnt, rg_cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		red = new LinkedList<>();
		green = new LinkedList<>();
		blue = new LinkedList<>();
		int n = Integer.parseInt(stk.nextToken());
		String[][] arr = new String[n][n];
		brr = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < n; j++)
				arr[i][j] = Character.toString(ch[j]);
		}
		r_cnt = 0;
		b_cnt = 0;
		g_cnt = 0;
		rg_cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (brr[i][j] == false) {
					if (arr[i][j].equals("R")) {
						red.add(new Point(i, j));
						r_cnt++;
					} else if (arr[i][j].equals("G")) {
						green.add(new Point(i, j));
						g_cnt++;
					} else if (arr[i][j].equals("B")) {
						blue.add(new Point(i, j));
						b_cnt++;
					}
					bfs(arr, n);
				}
			}
		}
		sb.append(r_cnt + b_cnt + g_cnt);
		r_cnt = 0;
		b_cnt = 0;		
		for ( boolean[] a : brr)
			Arrays.fill(a, false);
		for ( int i = 0 ; i < n ; i++ ) {
			for ( int j = 0 ; j < n ; j++) {
				if (arr[i][j].equals("G"))
					arr[i][j] = "R";				
			}				
		}
			
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {								
				if (brr[i][j] == false) {
					if (arr[i][j].equals("R") ) {
						red.add(new Point(i, j));
						r_cnt++;
					} else if (arr[i][j].equals("B")) {
						blue.add(new Point(i, j));
						b_cnt++;
					}
					bfs(arr, n);
				}
			}
		}
		
		sb.append(" ").append(r_cnt+b_cnt);
		System.out.print(sb);
	}

	static void bfs(String[][] arr, int n) {
		// red queue
		int[] dy = { 1, 0, -1, 0 };
		int[] dx = { 0, 1, 0, -1 };

		while (!red.isEmpty()) {
			Point temp = red.poll();
			if (brr[temp.y][temp.x] == false) {
				brr[temp.y][temp.x] = true;

				for (int k = 0; k < 4; k++) {
					int ny = temp.y + dy[k];
					int nx = temp.x + dx[k];
					if (ny >= 0 && ny < n && nx >= 0 && nx < n && arr[ny][nx].equals("R")) {
						red.add(new Point(ny, nx));
					}
				}
			}
		}
		while (!blue.isEmpty()) {
			Point temp = blue.poll();
			if (brr[temp.y][temp.x] == false) {
				brr[temp.y][temp.x] = true;
				for (int k = 0; k < 4; k++) {
					int ny = temp.y + dy[k];
					int nx = temp.x + dx[k];
					if (ny >= 0 && ny < n && nx >= 0 && nx < n && arr[ny][nx].equals("B")) {
						blue.add(new Point(ny, nx));
					}
				}
			}
		}
		while (!green.isEmpty()) {
			Point temp = green.poll();
			if (brr[temp.y][temp.x] == false) {
				brr[temp.y][temp.x] = true;
				for (int k = 0; k < 4; k++) {
					int ny = temp.y + dy[k];
					int nx = temp.x + dx[k];
					if (ny >= 0 && ny < n && nx >= 0 && nx < n && arr[ny][nx].equals("G")) {
						green.add(new Point(ny, nx));
					}
				}
			}
		}
	}

}