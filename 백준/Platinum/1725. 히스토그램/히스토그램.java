import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		int[] tree;
		// 생성자
		SegTree(int[] arr){
			this.N = arr.length;
			this.tree = new int[N*4];
			init(0, N-1, 1, arr);
		}
		// 초기화
		int init(int start, int end, int node, int[] arr) {
			if ( start == end )
				return this.tree[node] = start;
			int mid = (start+end)/2;
			return this.tree[node] = get_idx(init(start, mid, node*2, arr), 
					init(mid+1, end, node*2+1, arr));
		}
		// 쿼리
		int find_min(int start, int end, int node, int left, int right) {
			if ( start > right || left > end )
				return -1;			
			if ( start >= left && end <= right )
				return this.tree[node];
			int mid = (start + end) / 2;
			return get_idx(find_min(start, mid, node*2, left, right), 
					find_min(mid+1, end, node*2+1, left, right));
		}
		// 최소값 인덱스 구하기
		int get_idx ( int a, int b ) {
			if ( a == -1 )
				return b;
			if ( b == -1 )
				return a;
			if ( arr[a] > arr[b] )
				return b;
			else if ( arr[a] == arr[b] ) {
				if ( a > b )
					return b;
				else
					return a;
			}
			else
				return a;
		}		
	}
	
	// input data 저장 배열
	static int[] arr;
	static int maxv;
	static SegTree sg;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int n = Integer.parseInt(br.readLine());
		arr = new int[n];
		for ( int i = 0 ; i < n ; i++ ) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		sg = new SegTree(arr);
		
		maxv = 0;		
		solve(n, 0, n-1);
		sb.append(maxv);
		System.out.print(sb);
	}
	
	static void solve (int n, int left, int right ) {
		if ( left > right )
			return;
		int sumv = 0;
		int min_idx = sg.find_min(0, n-1, 1, left, right);
		int minv = arr[min_idx];
		sumv = ( right - left + 1 ) * minv;
		maxv = Math.max(maxv, sumv);
		solve(n, left, min_idx-1);
		solve(n, min_idx+1, right);
	}	
	
}