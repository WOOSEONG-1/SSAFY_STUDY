import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		n = Integer.parseInt(br.readLine());
//		n = 100000;
		StringTokenizer stk = new StringTokenizer(br.readLine());		
		int[] arr = new int[n];
		for ( int i = 0 ; i < n ; i++ ) {
			arr[i] = Integer.parseInt(stk.nextToken());
//			arr[i-1] = i;
		}
		sb.append(two_point(arr, n));
		System.out.print(sb);
	}
	
	static long two_point(int[] arr, int target) {
		
		int left = 0;
		int right = 0;
		long sumv = 0;
		
		boolean[] check = new boolean[100001];
		
		while ( true ) {
			
			if ( right == n ) {
				long l = right - left;
				long r = right - left + 1;
				sumv += l * r / 2;
				break;
			}
			
			else if ( !check[arr[right]] ) {
				check[arr[right++]] = true;
				
			}
			
			else {
				sumv += ( right - left );
				check[arr[left++]] = false;
				
			}
			
		}
		
		return sumv;
	}
}