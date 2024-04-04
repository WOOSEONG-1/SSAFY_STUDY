import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;
import java.io.IOException;

/* 09:07 ~ 
 * 1. 아이디어
 * 	- 확산 -> 맵에 좌표를 큐에 추가 후 확산 -> 작동 -> 하드코딩
 *  - while true -> while(q.isempty) [확산] -> for문 [ 작동 ]
 * 2. 시간 복잡도
 *	- 제한 : 1초
 * 3. 자료구조
 * 	- 배열, 큐
 * */
public class Main {
	static class Point {
		int y, x;

		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int r, c;
	static int[][] map;
	static Point[] filter;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		// r : row, c : col, t : time
		r = Integer.parseInt(stk.nextToken());
		c = Integer.parseInt(stk.nextToken());
		int t = Integer.parseInt(stk.nextToken());
		map = new int[r][c];
		filter = new Point[2];
		// Initialize
		for (int i = 0; i < r; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				// filter[0] = up , [1] = down
				if (map[i][j] == -1) {
					if (filter[0] == null)
						filter[0] = new Point(i, j);
					else
						filter[1] = new Point(i, j);
				}
			}
		}

		sb.append(air_filter(t));
		System.out.print(sb);

	}

	static int air_filter(int t) {
		int time = 0;
		// start
		while (true) {
			time += 1;
			map = spraying(map);
            
			// filter on
			// up filter
			int up_y = filter[0].y;
			int up_x = filter[0].x;
			// down 		
			for ( int row = up_y - 1 ; row > 0 ; row-- ) {
				map[row][up_x] = map[row-1][up_x];
			}
			map[0][0] = 0;
			// left
			for ( int col = 0 ; col < c-1 ; col++ ) {
				map[0][col] = map[0][col+1];
			}
			map[0][c-1] = 0;
			// up
			for ( int row = 0 ; row < up_y ; row++ ) {
				map[row][c-1] = map[row+1][c-1];
			}
			map[up_y][c-1] = 0;
			// right 
			for ( int col = c-1 ; col > 1 ; col-- ) {
				map[up_y][col] = map[up_y][col-1];
			}
			map[up_y][1] = 0;
			
			// down filter
			int d_y = filter[1].y;
			int d_x = filter[1].x;
			// up
			for ( int row = d_y+1 ; row < r-1 ; row++ ) {
				map[row][0] = map[row+1][0];
			}
			map[r-1][0] = 0;
			// left
			for ( int col = 0 ; col < c-1 ; col++ ) {
				map[r-1][col] = map[r-1][col+1];
			}
			map[r-1][c-1] = 0;
			// down 		 		
			for ( int row = r-1 ; row > d_y ; row-- ) {
				map[row][c-1] = map[row-1][c-1];
			}
			map[d_y][c-1] = 0;
			// right 
			for ( int col = c-1 ; col > 1 ; col-- ) {
				map[d_y][col] = map[d_y][col-1];
			}
			map[d_y][1] = 0;
			
			// end_option
			if (t == time)
				break;
		}
		
		return sum_arr(map);
	}

	// spray
	static int[][] spraying(int[][] M) {
		int[] dy = { 0, 1, 0, -1 };
		int[] dx = { 1, 0, -1, 0 };
		Deque<Point> q = new ArrayDeque<>();
		int[][] temp = new int[r][c];
		for ( int i = 0 ; i < r ; i++ )
			temp[i] = M[i].clone();
		// insert elements
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (M[i][j] > 0)
					q.add(new Point(i, j));
			}
		}
		// spray
		while (!q.isEmpty()) {
			Point now = q.poll();
			if ( now.y == 6 && now.x == 2 )
				now.y = 6; 
			int spray = M[now.y][now.x] / 5;
			for (int k = 0; k < 4; k++) {
				int ny = now.y + dy[k];
				int nx = now.x + dx[k];
				if (0 <= ny && 0 <= nx && ny < r && nx < c && M[ny][nx] != -1 ) {
					temp[ny][nx] += spray;
					temp[now.y][now.x] -= spray;
				}
			}
		}
		return temp;
	}
	// sum function
	static int sum_arr(int[][] M) {
		int sumv = 0;
		for ( int i = 0 ; i < r ; i++ ) {
			for ( int j = 0 ; j < c ; j++ ) {
				sumv += map[i][j]; 
			}
		}		
		return sumv + 2;
	}

}