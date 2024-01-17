import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		arr = new int[m + 1];
		back(0);
	}

	static void back(int depth) {

		for (int i = 1; i <= n; i++) {

			if (depth == m) {
				for (int j = 0; j < m; j++) {
					System.out.printf(arr[j] + " ");
				}
				System.out.println();
				break;
			}
			if(depth > 0) {
				if (arr[depth-1] <= i) {
					arr[depth] = i;
					back(depth + 1);
				}
			}
			else {
				arr[depth] = i;
				back(depth + 1);
			}
		}
	}

}