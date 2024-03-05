import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class Order {
		int s, e;
		Order(int s, int e){
			this.s = s;
			this.e = e;
		}
	}
	
	static int n;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		n = Integer.parseInt(br.readLine());
		StringTokenizer stk = new StringTokenizer(br.readLine());
		arr = new int[2001];
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		
		int m = Integer.parseInt(br.readLine());
		Order[] cmd = new Order[m];
		for ( int i = 0; i < m ; i++ ) {
			stk = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(stk.nextToken());
			int e = Integer.parseInt(stk.nextToken());
			cmd[i] = new Order(s, e);
		}
		
		boolean[][] dp = new boolean[2001][2001];
		for ( int i = 1 ; i <= 2000 ; i++ ) {
			for ( int j = i ; j <= 2000 ; j++ ) {
				if ( check(i, j) )
					dp[i][j] = true;
				else
					dp[i][j] = false;
			}
				 
		}
		
		for ( Order o : cmd ) {
			int res = 0;
			if ( dp[o.s][o.e] )
				res = 1;
			sb.append(res).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static boolean check(int s, int e) {
		for ( int i = 0 ; i < e-s ; i++ ) {
			if ( arr[i+s] != arr[(e-i)] )
				return false;
		}
		return true;
	}

}