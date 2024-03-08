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
		int[] arr = new int[n];
		stk = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		boolean[] num = new boolean[1000001];
		for ( int i : arr )
			num[i] = true;
		
		// 소수 판별기
		int[] score = new int[1000001];
		for (int i : arr) {
			for (int j = i * 2; j < score.length; j += i) {
				if ( num[j] ) {
					score[j] -= 1;
					score[i] += 1;					
				}
			}
		}
		
		for (int i : arr)
			sb.append(score[i]).append(" ");
		System.out.print(sb);
	}

}