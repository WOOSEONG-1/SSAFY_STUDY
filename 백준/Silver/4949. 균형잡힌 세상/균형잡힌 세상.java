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
	static Deque<String> dq = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
//		StringTokenizer stk = new StringTokenizer(br.readLine());
		String line;
		while (!(line = br.readLine()).equals(".")) {
			char[] crr = line.toCharArray();
			for (int i = 0; i < line.length(); i++) {
				String s = Character.toString(crr[i]);
				if ( s.equals("(") || s.equals("[") )
					dq.addLast(s);
				if ( s.equals("]"))
					if ( !dq.isEmpty() && dq.peekLast().equals("["))
						dq.pollLast();
					else {
						dq.addLast("!");
						break;
					}
				else if ( s.equals(")"))
					if ( !dq.isEmpty() && dq.peekLast().equals("("))
						dq.pollLast();
					else {
						dq.addLast("!");
						break;
					}
			}
			if (!dq.isEmpty())
				sb.append("no").append("\n");
			else
				sb.append("yes").append("\n");
			dq.clear();
		}
		System.out.print(sb);
	}
}