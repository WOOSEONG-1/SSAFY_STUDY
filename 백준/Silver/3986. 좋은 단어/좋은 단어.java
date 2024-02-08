import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Deque<Character> dq = new ArrayDeque<>();
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int n = Integer.parseInt(br.readLine());
		for ( int i = 0 ; i < n ; i++ ) {
			char[] crr = br.readLine().toCharArray();
			for ( int j = 0 ; j < crr.length ; j++ ) {
				if ( !dq.isEmpty() && dq.peekLast() == crr[j] ) {
					dq.pollLast();
					continue;
				}
				else
					dq.addLast(crr[j]);
			}
			if ( dq.isEmpty() )
				cnt++;
			dq.clear();
		}
		System.out.print(cnt);
	}
}