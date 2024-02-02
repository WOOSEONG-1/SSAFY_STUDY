import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 * 	- LCS 알고리즘 
 * 	- 3개 문자열의 LCS를 각각 비교 ( a, b , c ) 가장 최소값 리턴
 * 	- DP
 * 2. 시간 복잡도
 * 	- O ( N * M )
 * 3. 자료 구조
 * 	- 배열 ( 탐색 : O(N), 삽입:O(1), 삭제 : O(N), 검색 : O(1) ) 
 */

public class Main {
	static StringBuffer sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuffer();
//		StringTokenizer stk = new StringTokenizer(br.readLine());
		String s1 = new String(br.readLine());
		String s2 = new String(br.readLine());
		String s3 = new String(br.readLine());
		sb.append(lcs(s1, s2, s3));
		System.out.print(sb);
	}

	static int lcs(String a, String b, String c) {
		int[][][] dp = new int[a.length() + 1][b.length() + 1][c.length() + 1];
		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				for (int k = 1; k <= c.length(); k++) {
					if (a.charAt(i - 1) == b.charAt(j - 1) && c.charAt(k - 1) == a.charAt(i - 1)
							&& c.charAt(k - 1) == b.charAt(j - 1)) {
						dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
					} else {
						dp[i][j][k] = Math.max(Math.max(dp[i - 1][j][k], dp[i][j - 1][k]), dp[i][j][k - 1]);
					}
				}
			}
		}
		return dp[a.length()][b.length()][c.length()];
	}
}