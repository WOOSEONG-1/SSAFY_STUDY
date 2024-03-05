import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		n = Integer.parseInt(br.readLine());
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());

		}
		Arrays.sort(arr);
		arr = two_point(arr);
		Arrays.sort(arr);
		for ( int i : arr )
			sb.append(i).append(" ");
		
		System.out.print(sb);
	}

	static int[] two_point(int[] arr) {
		
//		for ( int i : arr )
//			System.out.print(i + " ");
//		System.out.println();
		
		int[] ans = new int[3];
		long minv = (long)Integer.MAX_VALUE + Integer.MAX_VALUE;
		
		for (int i = n - 1; i >= 0; i--) {
			
			int left = 0;
			int right = n-1;
			
			while (left < right) {
				
				long sumv = (long)arr[left] + arr[right] + arr[i];
				
				if ( minv > Math.abs(sumv) && left != i && right != i ) {
					minv = Math.abs(sumv);
					ans[0] = arr[left];
					ans[1] = arr[right];
					ans[2] = arr[i];
//					System.out.println(arr[i]+ ", sumv : " +sumv );
				}
				
				if ( sumv >= 0 )
					right--;
				
				else
					left++;
			}
			
			
		}
		
		return ans;
		
	}
}