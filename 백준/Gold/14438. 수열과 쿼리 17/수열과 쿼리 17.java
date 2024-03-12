import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static class SegTree {

        int[] tree;
        int N;

        // 생성자
        SegTree(int[] arr){
            this.N = arr.length;
            this.tree = new int[this.N * 4];
            init_min(0, N-1, 1, arr);
        }

        // 최솟값 트리 초기화
        int init_min(int start, int end, int node, int[] arr) {

            if ( start == end ) {
                return this.tree[node] = arr[start];
            }

            int mid = (start + end)/2;

            return tree[node] = Math.min(init_min(start, mid, node*2, arr),
                    init_min(mid+1, end, node*2+1, arr));
        }

        // 최소값 구하기
        int find_min(int start, int end, int node, int left, int right) {
            if ( left > end || right < start )
                return Integer.MAX_VALUE;
            if ( left <= start && right >= end )
                return this.tree[node];
            int mid = (start + end) / 2;
            
            return Math.min(find_min(start, mid, node*2, left, right),
                    find_min(mid+1, end, node*2+1, left, right));
        }

        // 갱신
        // index : 갱신이 이뤄진 인덱스
        // value : 갱신이 이루어지는 값
        // node : 현재 작업 중인 노드 인덱스
        int update(int start, int end, int node, int index, int value) {
            // 벗어난 범위
            if ( index < start || index > end )
                return this.tree[node];
            int mid = ( start + end ) / 2;
            // 목표 지점 도착
            if ( start == end )
                return this.tree[node] = value;
            return this.tree[node] = Math.min(update(start, mid, node*2, index, value),
                    update(mid+1, end, node*2+1, index, value));
        }

    }

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        // n : 수의 개수, m : 변경 횟수
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for ( int i = 0 ; i < n ; i++ )
            arr[i] = Integer.parseInt(stk.nextToken());

        SegTree sgTree = new SegTree(arr);
        int m = Integer.parseInt(br.readLine());
        for ( int i = 0 ; i < m ; i++ ) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            if ( a == 2 )
                sb.append(sgTree.find_min(0, n-1, 1, b-1, c-1)).append("\n");
            else {
                arr[b-1] = c;
                sgTree.update(0,  n-1,  1, b-1, c);

            }
        }

        System.out.print(sb);
    }
}