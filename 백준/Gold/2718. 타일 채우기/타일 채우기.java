import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int T = Integer.parseInt(br.readLine());
		// 
		for ( int t = 1 ; t <= T ; t++ ) {
			int n = Integer.parseInt(br.readLine());
			int[][] dp = new int[3][n+1];
			dp[0][0] = 1;
			dp[0][1] = 1;
			dp[1][1] = 1;
			dp[2][1] = 1;
			// 0 : a, 1 : b, 2 : c
			for ( int i = 2 ; i <= n ; i++ ) {
				dp[0][i] = dp[0][i-1] + 2*dp[1][i-1] + dp[0][i-2] + dp[2][i-1];
				dp[1][i] = dp[0][i-1] + dp[1][i-1];
				dp[2][i] = dp[0][i-1] + dp[2][i-2];
			}
			sb.append(dp[0][n]).append("\n");
		}	
		System.out.print(sb);
	}
}