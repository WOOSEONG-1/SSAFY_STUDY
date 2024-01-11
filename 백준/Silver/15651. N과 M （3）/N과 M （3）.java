import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static int N, M;
	static StringBuffer sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		sb = new StringBuffer();
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());		
		arr = new int[M];
        
		recur(0,-1);
		
		System.out.print(sb);
	}
	
	static void recur(int k,int prev) throws IOException {
		if ( k == M ) {
			for ( int a : arr )
				sb.append(a).append(" ");
			sb.append("\n");
			return;
		}
		
		for ( int i = 0 ; i < N ; i++) {
			if ( true ) {
				arr[k] = i+1;
				recur(k+1, i);
			}			
		}
		return;
	}
	
}
