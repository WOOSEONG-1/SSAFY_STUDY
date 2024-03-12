import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static class SegTree {
        long[] tree;
        int N;

        public SegTree(int[] arr){
            this.N = arr.length;
            tree = new long[4*N];
            init(0, N-1, 1, arr);
        }

        long init(int start,int end, int node, int[] arr){

            if ( start == end )
                return this.tree[node] = arr[start];
            int mid = ( start + end ) / 2;
            return this.tree[node] = init(start, mid, node*2, arr) + init(mid+1, end, node*2+1, arr);
        }

        long sum(int start, int end, int node, int left, int right) {
            if ( start > right || end < left )
                return 0;
            if ( start >= left && end <= right )
                return this.tree[node];
            int mid = ( start + end ) / 2;
            return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
        }

        long update(int start, int end, int node, int index, int value) {
            if ( index > end || index < start )
                return this.tree[node];
            if ( start == end )
                return this.tree[node] = value;
            int mid = ( start + end ) / 2;
            return this.tree[node] = update(start, mid, node*2, index, value) + update(mid+1, end, node*2+1, index, value);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        // n : 수의 개수, m : 변경 횟수
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());

        int[] arr = new int[n];
        stk = new StringTokenizer(br.readLine());
        for ( int i = 0 ; i < n ; i++ )
            arr[i] = Integer.parseInt(stk.nextToken());

        SegTree sgTree = new SegTree(arr);

        for ( int i = 0 ; i < m ; i++ ) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
                if( y > x )
                    sb.append(sgTree.sum(0, n-1, 1, x-1, y-1)).append("\n");
                else
                    sb.append(sgTree.sum(0, n-1, 1, y-1, x-1)).append("\n");
                sgTree.update(0, n-1, 1, a-1, b);
                arr[a-1] = b;
        }

        System.out.print(sb);
    }
}