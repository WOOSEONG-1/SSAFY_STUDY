import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(stk.nextToken());

			sb.append(solved(n)).append("\n");
		}
		br.close();
		System.out.print(sb);
	}
	
	static String solved ( int n ) {
		if ( n > 81)
			return "No";
		else
			for ( int i = 1 ; i < 10 ; i++) {
				if ( n%i == 0 && (double)n/i <= 9 )
					return "Yes";
			}
		return "No";
	}
}

