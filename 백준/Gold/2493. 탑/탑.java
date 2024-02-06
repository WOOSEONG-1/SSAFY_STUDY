import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*  1. 아이디어
        - tail에 있는 것보다 크다면 pop 
    2. 시간복잡도
        - N 1 ~ 500,000	/ O(N) = N
    3. 자료 구조
        - 배열 : 탐색 -> O(n) / 접근 -> O(1) / 삭제 : O(1) / 삽입 : O(1)
 */
public class Main {
	static class St {
		int cnt;
		int num;

		St(int num, int cnt) {
			this.cnt = cnt;
			this.num = num;
		}
		public int getNum() {
			return num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		stk = new StringTokenizer(br.readLine());
		St[] arr = new St[n];
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = new St(Integer.parseInt(stk.nextToken()), i + 1);
		}
		Deque<St> dq = new ArrayDeque<>();
		for (int i = n - 1; i >= 0; i--) {
			while (!dq.isEmpty() && dq.peekLast().num < arr[i].getNum()) {
				ans[dq.peekLast().cnt-1] = arr[i].cnt;
				dq.pollLast();
			}
			dq.addLast(arr[i]);
		}
		for (int i : ans)
			sb.append(i).append(" ");
		System.out.print(sb);
	}
}