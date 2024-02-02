import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 * 	- LCS 알고리즘 
 * 	- 최장 공통 부분 집합 찾기
 * 	- DP
 * 2. 시간 복잡도
 * 	- O ( N * M )
 * 3. 자료 구조
 * 	- 배열 ( 탐색 : O(N), 삽입:O(1), 삭제 : O(N), 검색 : O(1) ) 
 */

public class Main {	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
//		StringTokenizer stk = new StringTokenizer(br.readLine());
		String s1 = new String(br.readLine());
		String s2 = new String(br.readLine());
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		for ( int i = 1 ; i <= s1.length() ; i++ ) {
			for ( int j = 1 ; j <= s2.length() ; j++ ) {
				if( s1.charAt(i-1) == s2.charAt(j-1) ) {
					dp[i][j] = dp[i-1][j-1] + 1;					
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		sb.append(dp[s1.length()][s2.length()]);		
		System.out.print(sb);		
	}
}