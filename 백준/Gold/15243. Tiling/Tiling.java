import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	static BigInteger div = BigInteger.valueOf(1000000007);
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int n = Integer.parseInt(br.readLine());				
		BigInteger[] dp = new BigInteger[1001];
		dp[2] = BigInteger.valueOf(3);
		dp[4] = BigInteger.valueOf(11);
		for ( int i = 6 ; i <= 1000 ; i += 2 ) {
			if (i%2 == 0)
				dp[i] = dp[i-2].multiply(BigInteger.valueOf(4)).subtract(dp[i-4]);
		}
		if (n%2 == 1)
			sb.append(0);
		else
			sb.append(dp[n].mod(div));
		System.out.println(sb);
		}
}