import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int cnt, N= 0;
	static int[] brr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		brr = new int[N];
		solved(0);
		sb.append(cnt);
		cnt = 0;

		br.close();
		System.out.print(sb);
	}

	// 메인 알고리즘
	static void solved(int depth) {
		if (depth == N) {
			cnt++;
			return;
		}
		for (int i = 0; i < N; i++) {
			brr[depth] = i;
			if (poss(depth)) {				
				solved(depth + 1);
			}
		}
    }
    
	static boolean poss(int depth) {
		for (int i = 0 ; i < depth ; i++) {
			if ( brr[i] == brr[depth] )
				return false;
			
			if ( depth-i == Math.abs(brr[i]-brr[depth]) )
				return false;
		}
		return true;
	}
}