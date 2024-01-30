import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/* 
 * 1. 아이디어
 *  바이러스의 위치를 큐에 대입 -> 조합 -> 퍼트리기 -> 최소 시간 갱신
 *  
 * 2. 시간복잡도	( N, M 1 ~ 8 ) 
 * 	N 5 ~ 50 , M 1 ~ 10
 * 	64 C 3 * 64 * 5 = 13,000k   
 * 3. 자료 구조
 * 	
 */

public class Main {
	static class Point{
		int y,x;
		Point(int y, int x){
			this.y = y;
			this.x = x;			
		}		
	}
	static ArrayList<Point[]> lst = new ArrayList<>();
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		arr = new int [n+1][n+1];
		// lst ( temp1, temp2, temp3 = ( Point1, Point2 ) )
		for ( int i = 1 ; i <= n ; i++ ) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 1 ; j <= n ; j++) {
				arr[i][j] = Integer.parseInt(stk.nextToken()) - arr[i-1][j-1] + arr[i][j-1] + arr[i-1][j];
			}
		}
		int y1 = 0;
		int x1 = 0;
		int y2 = 0;
		int x2 = 0;
		for ( int i = 0 ; i < m ; i++ ) {
			stk = new StringTokenizer(br.readLine());
			y1 = Integer.parseInt(stk.nextToken());
			x1 = Integer.parseInt(stk.nextToken());
			y2 = Integer.parseInt(stk.nextToken());
			x2 = Integer.parseInt(stk.nextToken());
			Point[] temp = { new Point(y1,x1), new Point(y2,x2) };
			lst.add(temp);
		}
		for ( Point[] p : lst ) {
			sb.append(find_sum(p)).append("\n");
		}		
		System.out.print(sb);
	}
	static int find_sum(Point[] p) {
		int ans = 0;
		ans = arr[p[1].y][p[1].x]-arr[p[1].y][p[0].x-1]-arr[p[0].y-1][p[1].x]+arr[p[0].y-1][p[0].x-1];		
		return ans;
	}
	// 
}