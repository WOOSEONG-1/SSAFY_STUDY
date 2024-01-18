import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Point{
		int x;
		int y;
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}	
	}
	static int n,m;
	static int[][] arr;
	static boolean[][] brr;
	static Queue<Point> q = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		arr = new int [n+1][m+1];
		brr = new boolean [n+1][m+1];
		for ( int i = 1 ; i <= n ; i++) {			
			char[] crr = br.readLine().toCharArray();
			for ( int j = 1 ; j <= m ; j++)
				arr[i][j] = Integer.parseInt(Character.toString(crr[j-1]));
		}
				
		q.add(new Point(1,1));
		bfs(0);
		sb.append(arr[n][m]);
		
		System.out.print(sb);
	}
	static int bfs(int cnt) {
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		while(!q.isEmpty()) {
			Point temp = q.poll();
			for ( int i = 0 ; i < 4 ; i++) {
				int ny = temp.y - dy[i];
				int nx = temp.x - dx[i];
				if( ny >= 0 && ny <= n && nx >= 0 && nx <= m ) {
					if ( arr[ny][nx] == 1 && brr[ny][nx] == false ) {
						arr[ny][nx] = arr[temp.y][temp.x]+ 1;
						brr[ny][nx] = true;
						q.add(new Point(ny,nx));
					}
					else
						continue;
				}
			}		
		}
		
		return cnt;
	}
}