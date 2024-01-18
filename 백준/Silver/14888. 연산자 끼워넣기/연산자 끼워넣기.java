import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int N, maxv, minv;
	static int[] calc_arr, arr, temp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		calc_arr = new int[4];	
		arr = new int[N];
		temp = new int[N-1];
		stk = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < N ; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		stk = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < 4 ; i++) {
			calc_arr[i] = Integer.parseInt(stk.nextToken());
		}
		maxv = Integer.MIN_VALUE;
		minv = Integer.MAX_VALUE;
		back(0,0,0,0,0);
		
		sb.append(maxv).append("\n").append(minv);
		System.out.print(sb);
	}
	
	static void back(int depth, int p, int m, int mx, int div) {
				
		if ( depth == N-1 ) {
			maxv = Math.max(temp[N-2], maxv);
			minv = Math.min(temp[N-2], minv);
			
			return;
		}
		
		for ( int i = 0 ; i < 4 ; i++ ) {
			if ( p != calc_arr[0] && i == 0) {
				if ( depth == 0 )
					temp[depth] = arr[depth] + arr[depth+1];
				else
					temp[depth] = temp[depth-1] + arr[depth+1];
				back(depth+1,p+1,m,mx,div);
			}
			else if ( m != calc_arr[1] && i == 1 ) {
				if ( depth == 0 )
					temp[depth] = arr[depth] - arr[depth+1];
				else
					temp[depth] = temp[depth-1] - arr[depth+1];
				back(depth+1,p,m+1,mx,div);
			}
			else if ( mx != calc_arr[2] && i == 2 ) {
				if ( depth == 0 )
					temp[depth] = arr[depth] * arr[depth+1];
				else
					temp[depth] = temp[depth-1] * arr[depth+1];
				back(depth+1,p,m,mx+1,div);
			}
			else if ( div != calc_arr[3] && i == 3 ) {
				if ( depth == 0 )
					temp[depth] = arr[depth] / arr[depth+1];
				else
					temp[depth] = temp[depth-1] / arr[depth+1];
				back(depth+1,p,m,mx,div+1);
			}
		}
		
		return;
	}
}