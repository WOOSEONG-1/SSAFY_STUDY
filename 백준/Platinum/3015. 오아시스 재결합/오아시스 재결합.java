import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*  1. 아이디어 
 * 		- top보다 큰 수가 들어오면 스택의 첫 부분과 사이의 수를 2^(n-1)로 계산
    2. 시간 복잡도(1초, 256MB)
        - N 1 ~ 500,000, data 1 ~ 2^31
        - O(N)
    3. 자료 구조
        - 배열 : 탐색 -> O(n) / 접근 -> O(1) / 삭제 : O(1) / 삽입 : O(1)
        - 스택
 */
public class Main {
	static class St{
		int idx, cnt;
		St(int idx, int cnt){
			this.idx = idx;
			this.cnt = cnt;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		Deque<St> dq = new ArrayDeque<>();
		int n = Integer.parseInt(stk.nextToken());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		long sumv = 0;
		for ( int i = 0 ; i < n ; i++ ) {
			St temp = new St(arr[i], 1);
			while( !dq.isEmpty() && dq.peekLast().idx <= arr[i] ) {
				St pop = dq.pollLast();
				sumv += pop.cnt;
				if ( temp.idx == pop.idx )
					temp.cnt += pop.cnt;
			}
			if( !dq.isEmpty() ) 
				sumv += 1;
			dq.addLast(temp);
		}
		sb.append(sumv);
		System.out.print(sb);
	}
}