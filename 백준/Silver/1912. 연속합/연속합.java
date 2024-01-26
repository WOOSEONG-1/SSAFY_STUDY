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
		int[] arr = new int[n+1];
		stk = new StringTokenizer(br.readLine());
		for ( int i = 1 ; i <= n ; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());			
		}
		int dp[] = new int[n+1];
		int maxv = arr[1];
		for ( int i = 1 ; i <= n ; i++) {			
			if ( dp[i-1]+arr[i] >= 0 ) {
				if ( dp[i-1] < 0 ) 
					dp[i] = arr[i];
				else
					dp[i] = dp[i-1]+arr[i];
			}
			else
				dp[i] = arr[i];
			maxv = ( Math.max(maxv, dp[i]) > maxv ? dp[i] : maxv );
		}
		sb.append(maxv);
		System.out.print(sb);
	}
}