import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 *   - 세그먼트 트리에 자료형 저장 ( 최소값 세그먼트 )
 *   - 최소값 기준 left, right 범위의 직사각형 넓이 구할때마다 갱신
 * 2. 시간 복잡도
 *   - O(NlogN) -> 세그먼트 n번 구하기
 * */

public class Main {

	static class SegTree {
		// N : 트리의 크기, tree : 저장 배열
		int N;
		long[] tree;

		// 생성자
		SegTree(long[] arr) {
			this.N = arr.length;
			this.tree = new long[N * 4];
			init(0, N - 1, 1, arr);
		}

		// 초기화
		long init(int start, int end, int node, long[] arr) {
			if (start == end)
				return this.tree[node] = arr[start];
			int mid = (start + end) / 2;
			return this.tree[node] = init(start, mid, node * 2, arr) 
					+ init(mid + 1, end, node * 2 + 1, arr);
		}

		// 쿼리
		long sum(int start, int end, int node, int left, int right) {
			if (start > right || left > end)
				return 0;
			if (start >= left && end <= right)
				return this.tree[node];
			int mid = (start + end) / 2;
			return sum(start, mid, node * 2, left, right) +
					sum(mid + 1, end, node * 2 + 1, left, right);
		}
		
		long update(int start, int end, int node, int index, long value) {
			if ( index < start || index > end )
				return this.tree[node];
			if ( start == end )
				return this.tree[node] = value;
			int mid = (start+end)/2;
			return this.tree[node] = update(start, mid, node*2, index, value) +
					update(mid+1, end, node*2+1, index, value);
		}
	}

	// input data 저장 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();			
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		long[] arr = new long[n];
		SegTree sg = new SegTree(arr);
		for ( int i = 0 ; i < m ; i++ ) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			if ( a == 1 ) {
				arr[b-1] += c;
				sg.update(0, n-1, 1, b-1, arr[b-1]);
				}
			else
				sb.append(sg.sum(0, n-1, 1, b-1, c-1)).append("\n");
		}
		System.out.print(sb);
	}

}