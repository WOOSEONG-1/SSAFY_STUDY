import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	
	static HashSet<String> set = new HashSet<>();
	static int n, m;
	static StringBuffer sb;
	static int temp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());

		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());

		boolean brr[] = new boolean[n];
		temp = new int[m];
		stk = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}

		Arrays.sort(arr);
		back(0, arr, brr);

		System.out.print(sb);
	}

	static void back(int depth, int[] arr, boolean[] brr) {
		
		if (depth == m) {
			StringBuilder cmpr = new StringBuilder();
			for ( int a : temp ) {
				cmpr.append(Integer.toString(a)).append(" ");
			}
			
			if( !set.contains(cmpr.toString()) ) {
				set.add(cmpr.toString());
				sb.append(cmpr.toString()).append("\n");
			}
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (brr[i] == false) {
				temp[depth] = arr[i];
				brr[i] = true;
				back(depth + 1, arr, brr);
				brr[i] = false;
			}

		}
	}
}