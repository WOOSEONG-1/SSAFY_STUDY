import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static class SegTree {
		
		long[] tree;
		int N;
		
		// 생성자
		// tree[0] -> 최소값
		SegTree(int[] arr){
			this.N = arr.length;
			this.tree = new long[this.N * 4];
			init_min(0, N-1, 1, arr);
		}
		
		// 최솟값 트리 초기화
		long init_min(int start, int end, int node, int[] arr) {
			if ( start == end ) {
				return this.tree[node] = arr[start];				
				}
			int mid = (start + end)/2;
			return tree[node] = init_min(start, mid, node*2, arr) + 
					init_min(mid+1, end, node*2+1, arr);
		}
		
		// 최소값 구하기
		long sum(int start, int end, int node, int left, int right) {
			if ( left > end || right < start )
				return 0;
			if ( left <= start && right >= end )
				return this.tree[node];
			int mid = (start + end) / 2;
			return sum(start, mid, node*2, left, right) + 
					sum(mid+1, end, node*2+1, left, right);
		}
		
		// 갱신
		// index : 갱신이 이뤄진 인덱스
		// value : 갱신이 이루어지는 값
		// node : 현재 작업 중인 노드 인덱스
		long update(int start, int end, int node, int index, int value) {
			// 벗어난 범위
			if ( index < start || index > end )
				return this.tree[node];	
			int mid = ( start + end ) / 2;
			// 목표 지점 도착
			if ( start == end )
				return this.tree[node] = value;
			return this.tree[node] = update(start, mid, node*2, index, value) +
			update(mid+1, end, node*2+1, index, value);
		}
		
	}
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();
		// n : 수의 개수, m : 변경 횟수
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		int[] arr = new int[n];
		
		SegTree sgTree = new SegTree(arr);
		
		for ( int i = 0 ; i < m ; i++ ) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			if ( a == 0) {
				if ( b < c )
					sb.append(sgTree.sum(0, n-1, 1, b-1, c-1)).append("\n");
				else
					sb.append(sgTree.sum(0, n-1, 1, c-1, b-1)).append("\n");
				}
			else {
				sgTree.update(0,  n-1,  1, b-1, c);
				arr[b-1] = c;
			}
		}
		
		System.out.print(sb);
	}
}