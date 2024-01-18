import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* DP  
 * 1. 아이디어
 *    
 * 2. 시간복잡도	( N 2 ~ 11 ) 
 * 
 * 3. 자료 구조
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(stk.nextToken());
		int[][] arr =  new int [N][N];
		int [][] dp = new int [N][N];
		for ( int i = 0 ; i < N ; i++) {
			stk = new StringTokenizer(br.readLine());
			for ( int j = 0 ; j < N ; j++) {
				if(stk.hasMoreTokens())
					arr[i][j] = Integer.parseInt(stk.nextToken());
				else
					arr[i][j] = 0;
			}			
		}
		dp[0][0] = arr[0][0];

		for ( int i = 1 ; i < N ; i++) {
			for ( int j = 0 ; j < N ; j++) {
				if ( j == 0 ) {
					dp[i][j] = dp[i-1][j] + arr[i][j];
				}					 
				else {
					dp[i][j] = Math.max( dp[i-1][j] + arr[i][j], dp[i-1][j-1]+arr[i][j] );
				}
			}
		}
		
		Arrays.sort(dp[N-1]);
		sb.append(dp[N-1][N-1]);
		System.out.print(sb);
	}
}