import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(stk.nextToken());
		int[] dp = new int[1001];
		if ( n >= 1 )
			dp[1] = 1;
		if ( n >= 2 )
			dp[2] = 3;
		if ( n >= 3 ) {
			for ( int i = 3 ; i <= n ; i++ ) {
				dp[i] = ((dp[i-2]*2)%10007 + (dp[i-1]%10007))%10007;				
			}
		}
		sb.append(dp[n]);
		System.out.print(sb);
	}
	
}