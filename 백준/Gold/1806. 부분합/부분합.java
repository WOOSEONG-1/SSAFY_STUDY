import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		int[] arr = new int[n];
		stk = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < n ; i++ ) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		int ans = two_point(arr,m, n);
		if ( ans == Integer.MAX_VALUE )
			ans = 0;
		sb.append(ans);
		System.out.print(sb);
	}
	
	// 길이 : l - r + 1
	static int two_point(int[] arr, int find, int n) {
		int left = 0;
		int right = 0;
		int sumv = 0;
		int minv = Integer.MAX_VALUE;
		while(true) {
			
			if ( sumv >= find ) {
				sumv -= arr[left++];
				minv = Math.min(right - left + 1, minv);
			}
				
			else if ( right == n ) 
				break;
			
			else if ( sumv < find )
				sumv += arr[right++];
	
		}
		
		return minv;		
		
	}
}
