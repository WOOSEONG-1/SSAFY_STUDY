import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* DP  
 * 1. 아이디어
 *    상향식 DP
 * 2. 시간복잡도	
 * 
 * 3. 자료 구조
 * 
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(br.readLine());				
		for ( int i = 0 ; i < T ; i++ ) {			
			StringTokenizer stk = new StringTokenizer(br.readLine());			
			int k = Integer.parseInt(stk.nextToken()); 	// k층 
			stk = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(stk.nextToken());	// n호
			int[][] arr = new int[k+1][n+1];
			for ( int j = 1 ; j <= n ; j++) {
				arr[0][j] = arr[0][j-1]+j;
			}			
			for ( int y = 1 ; y <= k ; y++ ) {
				for ( int a = 1; a <= n ; a++ ) {
					arr[y][a] = arr[y-1][a] + arr[y][a-1];					
				}				
			}			
			sb.append(arr[k][n]-arr[k][n-1]).append("\n");
		}
		System.out.println(sb);
	}	
}