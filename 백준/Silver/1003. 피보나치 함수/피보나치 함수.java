import java.io.IOException;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();
		int n = Integer.parseInt(stk.nextToken());
		int fibo[][] = new int [41][2];
		fibo[0][0] = 1;
		fibo[0][1] = 0;
		fibo[1][0] = 0;
		fibo[1][1] = 1;
		for ( int k = 0 ; k < n ; k++) {
			stk = new StringTokenizer(br.readLine());
			int temp = Integer.parseInt(stk.nextToken());
			
			for ( int i = 2 ; i <= temp ; i++) {
				fibo[i][0] = fibo[i-2][0] + fibo[i-1][0];
				fibo[i][1] = fibo[i-2][1] + fibo[i-1][1];
			}
			sb.append(fibo[temp][0]+" "+fibo[temp][1]+"\n");
		}
		System.out.print(sb);
	}
}