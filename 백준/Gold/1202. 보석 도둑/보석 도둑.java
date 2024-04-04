import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1. 아이디어 
 * 	- 우선 순위 큐 사용
 *  - 보석 무게, 가방 무게 오름차순 -> 가방 무게 이하의 보석들 최대힙에 넣고 top만 빼서 가산
 * 2. 시간 복잡도
 *  - O(nlogn) -> 우선 순위 큐의 모든 원소 삭제 시간
 * */

public class Main {

	static class Jewel implements Comparable<Jewel>{
		int w, v;

		Jewel(int w, int v) {
			this.w = w;
			this.v = v;
		}
		
		public int compareTo(Jewel o) {
			return (this.w - o.w) ;
		}
	}

	static int n, m;
	static long res = 0;
	static ArrayList<Jewel> gems = new ArrayList<>();
	static ArrayList<Integer> packs = new ArrayList<>();
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		// n : 보석 개수, m : 가방 개수
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		
		for (int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(stk.nextToken());
			int v = Integer.parseInt(stk.nextToken());
			gems.add(new Jewel(w, v));
		}
		
		for (int i = 0; i < m; i++) {
			packs.add(Integer.parseInt(br.readLine()));
		}
		
		Collections.sort(gems);
		Collections.sort(packs);
		solve();
		sb.append(res);
		System.out.print(sb);
	}

	static long solve() {
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		int last = 0;
		for ( int p : packs ) {
			for ( ; last < n ; last++ ) {
				if ( gems.get(last).w <= p )
					pq.add(gems.get(last).v);
				else
					break;
			}
			if ( !pq.isEmpty() )
				res += pq.poll();
		}
		return res;
	}

}