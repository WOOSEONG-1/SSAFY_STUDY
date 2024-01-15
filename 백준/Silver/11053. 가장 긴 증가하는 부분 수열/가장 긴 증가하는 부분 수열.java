import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 * 
 * 2. 시간복잡도
 * nlogn
 * 3. 자료 구조
 * 
 */
public class Main {
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(stk.nextToken());
		int[] arr = new int[N];
		int[] dp = new int[N];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());			
		}		
		for ( int i = 0 ; i < N ; i++) {
			for (int j = 0 ; j < i ; j++) {
				if( arr[i] > arr[j] )
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
		}
		Arrays.sort(dp);
		sb.append(dp[N-1]+1);
		System.out.print(sb);
	}
	
}
