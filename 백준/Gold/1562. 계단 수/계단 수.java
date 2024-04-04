import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int n = Integer.parseInt(br.readLine());
		int[][][] dp = new int[n+1][10][1024];
		for ( int i = 1 ; i < 10 ; i++ )
			dp[1][i][(1<<i)] = 1;
		
		for ( int i = 2 ; i <= n ; i++ ) {
			for ( int j = 0 ; j < 10 ; j++ ) {
				for ( int k = 0 ; k < (1<<10) ; k++ ) {
					if ( j >= 1 && j <= 8 )
						dp[i][j][k | (1<<j)] += dp[i-1][j-1][k] + dp[i-1][j+1][k];
					else if ( j == 0 )
						dp[i][j][k | (1<<j)] += dp[i-1][j+1][k];
					else if ( j == 9 )
						dp[i][j][k | (1<<j)] += dp[i-1][j-1][k];
					dp[i][j][k | (1<<j)] %= 1000000000;
				}
			}
		}
		
		int ans = 0;
		for ( int i = 0 ; i < 10 ; i++ ) { 
				ans = (ans+dp[n][i][1023])%1000000000;
		}
			
		sb.append(ans);
		System.out.println(sb);
	}

}