import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int n = Integer.parseInt(br.readLine());
		BigInteger[][] dp = new BigInteger[3][n+1];
//		long[][] dp = new long[3][n+1];
		dp[0][0] = BigInteger.valueOf(1);
		dp[0][1] = BigInteger.valueOf(1);
		dp[1][1] = BigInteger.valueOf(1);
		dp[2][1] = BigInteger.valueOf(1);
		dp[2][0] = BigInteger.valueOf(0);
		// 0 : a, 1 : b, 2 : c
		for ( int i = 2 ; i <= n ; i++ ) {
			dp[0][i] = dp[0][i-1].add(dp[1][i-1].multiply(BigInteger.valueOf(2)).add(dp[0][i-2]).add(dp[2][i-1])); 
			dp[1][i] = dp[0][i-1].add(dp[1][i-1]);
			dp[2][i] = dp[0][i-1].add(dp[2][i-2]);
		}
		sb.append(dp[0][n].mod(BigInteger.valueOf(1000)));
		System.out.print(sb);
	}
}