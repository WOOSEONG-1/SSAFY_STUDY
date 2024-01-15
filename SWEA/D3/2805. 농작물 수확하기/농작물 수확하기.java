import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine()); // Test case number
		
		for (int i = 1; i <= T; i++) {
			sb.append("#").append(i).append(" ");
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(stk.nextToken());			
			int[][] arr =  new int [N][N];
			
			for ( int y = 0 ; y < N ; y++) {
				stk = new StringTokenizer(br.readLine());
				char[] ch = stk.nextToken().toCharArray();
				for ( int x = 0 ; x < N ; x++) {
					arr[y][x] = Integer.parseInt(Character.toString(ch[x]));
				}
			}
			
			sb.append(solved(arr, N)).append("\n");			
		}
		br.close();
		System.out.print(sb);
	}
	
	static int solved(int[][] arr, int N) {
		int res = 0;
		int mid = N/2;
		int cnt = 0;
		for ( int i = 0 ; i < N ; i++) {
			for ( int j = mid-cnt ; j <= mid + cnt; j++) {		
				res += arr[i][j];				
			}
			if ( i < mid )
				cnt++;
			else if ( i >= mid )
				cnt--;
		}			
		return res;		
	}
}