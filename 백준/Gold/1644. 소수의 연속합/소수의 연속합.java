import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int n, N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

//		StringTokenizer stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(br.readLine());
		ArrayList<Integer> lst = new ArrayList<>(283200);
		N = 4000000;
		boolean[] dp = new boolean[N+1];		
		for (long i = 2; i <= N; i++) {
			if (!dp[(int)i]) {
				lst.add((int)i);
				for (long j = i * i; j <= N; j += i) {
					dp[(int)j] = true;
				}
			}
		}
		
		dp = new boolean [1];
		sb.append(two_point(lst, n));
		System.out.print(sb);
	}
	
	static int two_point(ArrayList<Integer> lst, int target) {
		
		int left = 0;
		int right = 0;
		int cnt = 0;
		int sumv = 0;
		while ( true ) {
			
			if ( sumv >= target ) 
				sumv -= lst.get(left++);
			
			else if ( right == lst.size() || lst.get(right) > n ) {
				break;
			}
			
			else if ( sumv < target ) 
				sumv += lst.get(right++);
			
			if ( right == lst.size() - 1 )
				cnt += 0;
			
			if ( sumv == target ) 
				cnt ++;
		}
		
		return cnt;
	}
}