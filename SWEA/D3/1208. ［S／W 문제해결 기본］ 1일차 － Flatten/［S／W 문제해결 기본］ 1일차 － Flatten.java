import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;


public class Solution {

	static int[] arr = new int[100];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		StringBuffer sb = new StringBuffer();

		for (int i = 1; i <= 10; i++) {
			sb.append("#").append(i).append(" ");
			
			stk = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(stk.nextToken());
			
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < 100; j++) {
				arr[j] = Integer.parseInt(stk.nextToken());
			}
			sb.append(solved(T)).append("\n");
		}
		br.close();
		System.out.print(sb);
	}

	static int solved(int t) {
		int res;
		Arrays.sort(arr);
		for (int i = 0; i < t; i++) {			
			arr[0]++;
			arr[99]--;
			Arrays.sort(arr);
		}
		res = arr[99] - arr[0]; 
		return res;
	}

	static void swap(int a, int b) {
		int temp = a;
		a = b;
		b = temp;
	}

	/*static void sorting(int arr[]) {
		for (int i = 0; i < 100; i++) {
			if (arr[i] < arr[i + 1]) {
				swap(arr[i], arr[i + 1]);
				break;
			}
		}
		for (int i = 99; i > 0; i--) {
			if (arr[i] > arr[i - 1]) {
				swap(arr[i], arr[i - 1]);
				break;
			}
		}
	}*/
}