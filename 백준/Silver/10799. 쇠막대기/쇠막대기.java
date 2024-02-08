import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static Deque<Character> dq = new ArrayDeque<>();
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		char[] crr = br.readLine().toCharArray(); 
		for ( int i = 0 ; i < crr.length ; i++ ) {
			if ( crr[i] == ')' && crr[i-1] == '(' ) {
				dq.pollLast();
				cnt += dq.size();				
			}
			else if ( crr[i] == ')' ) {
				cnt++;
				dq.pollLast();
			}
			else
				dq.add(crr[i]);
		}
		System.out.print(cnt);
	}
}