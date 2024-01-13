import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Solution {
	
	static StringBuffer sb = new StringBuffer();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(stk.nextToken());
		for ( int i = 1 ; i <= T ; i++) {
			sb.append("#"+i+"\n");
			stk = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(stk.nextToken());
			int[][] arr = new int[N][N];
			
			for ( int y = 0 ; y < N ; y++) {
				stk = new StringTokenizer(br.readLine());
				for (int x = 0 ; x < N; x++) {
					arr[y][x] = Integer.parseInt(stk.nextToken());
				}
			}
			rotate(N, arr);
		}
		System.out.print(sb);
	}
	
	static void rotate(int N,int[][] arr) {
 
		for ( int i = 0 ; i < N ; i++) {
			// 90 rotate	[n-1][0] 부터 [0][0]까지
			for ( int x = N-1 ; x >= 0 ; x--) {
				sb.append(arr[x][i]);
			}
			sb.append(" ");
			// 180 rotate	[n-1][0] 부터 [0][0]까지
			for ( int x = N-1 ; x >= 0 ; x--) {
				sb.append(arr[N-1-i][x]);
			}
			sb.append(" ");
			// 270 rotate [0][n-1] 부터 [n-1][n-1]까지
			for ( int x = 0 ; x <= N-1 ; x++) {
				sb.append(arr[x][N-1-i]);
			}
			sb.append("\n");
		}
		
	}
}