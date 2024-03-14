import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 *   - 중앙값 구하기 -> 이중 우선순위 큐 이용
 *   - size(minheap) == size(maxheap) 인 경우, minheap 우선
 * 2. 시간 복잡도
 *   - O(NlogN)
 * */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		PriorityQueue<Integer> min = new PriorityQueue<>();
		PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
		for (int i = 0; i < n; i++) {
			int input = Integer.parseInt(br.readLine());
			if (min.size() == max.size())
				max.add(input);
			else
				min.add(input);
			
			if ( !min.isEmpty() && max.peek() > min.peek() ) {
				int lo = max.poll();
				int hi = min.poll();
				min.add(lo);
				max.add(hi);
			} 
			
			if ( min.isEmpty() )
				sb.append(max.peek());
			else
				sb.append(max.peek());
			sb.append("\n");
		}

		System.out.print(sb);
	}

}