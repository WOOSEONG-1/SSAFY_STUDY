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
		
		sb.append(arr[0]).append(" ").append(arr[1]);
		System.out.print(sb);
	}

	static int[] two_point(int[] arr) {
		int left = 0;
		int right = n-1;
		int[] ans = new int[2];
		int minv = Integer.MAX_VALUE;
		// 갱신되면 right + 1, 갱신안되면 left + 1
		while (left < right) {
			
			if ( minv > Math.abs(arr[left] + arr[right] )) {
				minv = Math.abs(arr[left] + arr[right]);
				ans[0] = arr[left];
				ans[1] = arr[right];
			}
			if ( arr[right] + arr[left] >= 0 )
				right--;
			else
				left++;

		}

		return ans;
	}
}