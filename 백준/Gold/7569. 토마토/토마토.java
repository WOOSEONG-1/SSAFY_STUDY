import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, h;
	static int[][][] arr;
	static boolean[][][] brr;
	
	static class Point {
		int h;
		int x;
		int y;		
		Point(int h, int y, int x) {
			this.h = h;
			this.y = y;
			this.x = x;
		}
	}
	static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(stk.nextToken());
		n = Integer.parseInt(stk.nextToken());
		h = Integer.parseInt(stk.nextToken());
		arr = new int[h][n][m];
		brr = new boolean[h][n][m];
		for ( int a = 0 ; a < h ; a++) {
			for (int i = 0; i < n; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++)
					arr[a][i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		for ( int a = 0 ; a < h ; a++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (arr[a][i][j] == 1 && brr[a][i][j] == false) {
						q.add(new Point(a, i, j));
						brr[a][i][j] = true;
					}
				}
			}
		}
		
		bfs();
		sb.append(chk_arr());		
		System.out.print(sb);
	}

	static void bfs() {
		int[] dy = { 1, 0, -1, 0, 0, 0 };
		int[] dx = { 0, 1, 0, -1, 0, 0 };
		int[] dz = { 0, 0, 0, 0, 1, -1};
		while (!q.isEmpty()) {
			Point temp = q.poll();
			for (int a = 0; a < 6; a++) {
				int ny = temp.y - dy[a];
				int nx = temp.x - dx[a];
				int nz = temp.h - dz[a];
				if ( nx >= 0 && nx < m && ny >= 0 && ny < n && nz >= 0 && nz < h) {
					if (arr[nz][ny][nx] == 0 && brr[nz][ny][nx] == false) {
						arr[nz][ny][nx] = arr[temp.h][temp.y][temp.x] + 1; 
						brr[nz][ny][nx] = true;
						q.add(new Point(nz, ny, nx));
					}
				}
			}
		}
		return;
	}
	
	static int chk_arr() {
		int maxv = -1;
		for ( int a = 0 ; a < h ; a++) {
			for ( int y = 0 ; y < n ; y++) {
				for ( int x = 0 ; x < m ; x++) {
					if (arr[a][y][x] == 0)
						return -1;
					else if (arr[a][y][x] != -1 )
						maxv = Math.max(maxv, arr[a][y][x]);					
				}
			}
		}		
		return maxv-1;		
	}
	
}