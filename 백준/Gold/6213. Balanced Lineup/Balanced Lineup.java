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
		int[][] tree;

		// 생성자
		SegTree(int[] arr) {
			this.N = arr.length;
			this.tree = new int[2][N * 4];
			init_min(0, N-1, 1, arr);
			init_max(0, N-1, 1, arr);
		}

		// 초기화
		int init_min(int start, int end, int node, int[] arr) {
			if (start == end)
				return this.tree[0][node] = arr[start];
			int mid = (start + end) / 2;
			return this.tree[0][node] = Math.min(init_min(start, mid, node * 2, arr) 
					, init_min(mid + 1, end, node * 2 + 1, arr));
		}

		int init_max(int start, int end, int node, int[] arr) {
			if (start == end)
				return this.tree[1][node] = arr[start];
			int mid = (start + end) / 2;
			return this.tree[1][node] = Math.max(init_max(start, mid, node * 2, arr) 
					, init_max(mid + 1, end, node * 2 + 1, arr));
		}
		
		// 쿼리
		int find_min(int start, int end, int node, int left, int right) {
			if (start > right || left > end)
				return Integer.MAX_VALUE;
			if (start >= left && end <= right)
				return this.tree[0][node];
			int mid = (start + end) / 2;
			return Math.min(find_min(start, mid, node * 2, left, right),
					find_min(mid + 1, end, node * 2 + 1, left, right));
		}
		
		int find_max(int start, int end, int node, int left, int right) {
			if (start > right || left > end)
				return Integer.MIN_VALUE;
			if (start >= left && end <= right)
				return this.tree[1][node];
			int mid = (start + end) / 2;
			return Math.max(find_max(start, mid, node * 2, left, right),
					find_max(mid + 1, end, node * 2 + 1, left, right));
		}
		
		// 갱신
		int update_min(int start, int end, int node, int index, int value) {
			if ( index < start || index > end )
				return this.tree[0][node];
			if ( start == end )
				return this.tree[0][node] = value;
			int mid = (start+end)/2;
			return this.tree[0][node] = Math.min(update_min(start, mid, node*2, index, value),
					update_min(mid+1, end, node*2+1, index, value));
		}
		
		int update_max(int start, int end, int node, int index, int value) {
			if ( index < start || index > end )
				return this.tree[1][node];
			if ( start == end )
				return this.tree[1][node] = value;
			int mid = (start+end)/2;
			return this.tree[1][node] = Math.max(update_max(start, mid, node*2, index, value),
					update_max(mid+1, end, node*2+1, index, value));
		}
	}

	// input data 저장 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();			
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		int[] arr = new int[n];
		for ( int i = 0 ; i < n ; i++ ) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		SegTree sg = new SegTree(arr);
		for ( int i = 0 ; i < m ; i++ ) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			
			int diff = sg.find_max(0, n-1, 1, a-1, b-1) - sg.find_min(0, n-1, 1, a-1, b-1);
			sb.append(diff).append("\n");
		}
		System.out.print(sb);
	}

}