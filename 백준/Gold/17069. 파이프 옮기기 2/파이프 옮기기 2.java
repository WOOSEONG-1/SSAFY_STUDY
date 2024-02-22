import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		for ( int i = 0 ; i < n ; i++ ) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			for ( int j = 0 ; j < n; j++ ) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		long[][][] dp = new long[3][n][n];
		dp[0][0][1] = 1;
		// 가로 초기화
		for ( int i = 2 ; i < n ; i++ )
			if ( map[0][i] == 0)
				dp[0][0][i] = dp[0][0][i-1];
		
		for ( int i = 1 ; i < n ; i++ ) {
			for ( int j = 2 ; j < n ; j++ ) {
				// 0 : 가로, 1 : 세로, 2 : 대각선
				// 대각선
				if ( map[i][j] == 0 && map[i][j-1] != 1 && map[i-1][j] != 1 ) 
					dp[2][i][j] = dp[0][i-1][j-1] + dp[1][i-1][j-1] + dp[2][i-1][j-1];					
				// 가로, 세로 
				if ( map[i][j] == 0 ) {
					dp[0][i][j] = dp[0][i][j-1] + dp[2][i][j-1];
					dp[1][i][j] = dp[1][i-1][j] + dp[2][i-1][j];
				}
				
			}
		}
		
		sb.append(dp[0][n-1][n-1] + dp[1][n-1][n-1] + dp[2][n-1][n-1]);
		System.out.print(sb);
	}
}