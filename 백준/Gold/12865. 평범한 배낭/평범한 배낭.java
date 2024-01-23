import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static class Item{
		int w;
		int v;
		Item(int w, int v){
			this.w = w;
			this.v = v;
		}		
	}
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(stk.nextToken());	// item
		int W = Integer.parseInt(stk.nextToken());	// bag limit
		Item[] arr = new Item[n];					// item info
		for ( int i = 0 ; i < n ; i++) {
			stk = new StringTokenizer(br.readLine());
			arr[i] = new Item(Integer.parseInt(stk.nextToken()),
					Integer.parseInt(stk.nextToken()));
		}
		
		dp = new int[n+1][W+1];
		bag_prob(arr, n, W);
		sb.append(dp[n][W]);
		System.out.print(sb);
	}
	
	static void bag_prob(Item[] arr, int n, int W ) {
		for ( int i = 1 ; i < n+1 ; i++) {
			for ( int w = 1 ; w < W+1 ; w++) {
				if( arr[i-1].w > w )
					dp[i][w] = dp[i-1][w];
				else {
					int with = dp[i-1][w - arr[i-1].w] + arr[i-1].v;
					int out = dp[i-1][w];
					dp[i][w] = Math.max(with, out);
				}		
			}			
		}
	}
}