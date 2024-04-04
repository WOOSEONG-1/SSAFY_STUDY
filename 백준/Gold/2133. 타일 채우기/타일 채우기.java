import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int n = Integer.parseInt(br.readLine());				
		int[] dp = new int[31];
		dp[2] = 3;
		dp[4] = 11;
		for ( int i = 6 ; i <= 30 ; i += 2 ) {
			if (i%2 == 0)
				dp[i] = dp[i-2]*4 - dp[i-4];
		}
		if (n%2 == 1)
			sb.append(0);
		else
			sb.append(dp[n]);
		System.out.println(sb);
		}		
}