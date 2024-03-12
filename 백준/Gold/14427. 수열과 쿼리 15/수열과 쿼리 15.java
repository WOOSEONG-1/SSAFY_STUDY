import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class SegTree {
        int N;
        int[] tree;

        SegTree(int[] arr) {
            this.N = arr.length;
            this.tree = new int[N * 4];
            init(0, N - 1, 1, arr);
        }

        int init(int start, int end, int node, int[] arr) {
            if (start == end){
                return this.tree[node] = start;
            }
            int mid = (start + end) / 2;
            return this.tree[node] = get_idx(init(start, mid, node * 2, arr), init(mid + 1, end, node * 2 + 1, arr));
        }

        int get_idx(int a, int b) {
            if (a == -1)
                return b;
            if (b == -1)
                return a;

            if (arr[a] > arr[b])
                return b;
            else if (arr[a] == arr[b]) {
                if (a > b)
                    return b;
                else
                    return a;
            } else
                return a;
        }

        int sum(int start, int end, int node, int left, int right) {
            if (start > right || end < left)
                return -1;
            if (start >= left && end <= right)
                return this.tree[node];
            int mid = (start + end) / 2;
            return get_idx(sum(start, mid, node * 2, left, right), sum(mid + 1, end, node * 2 + 1, left, right));
        }

        int update(int start, int end, int node, int index, int value) {
            if (start > index || index > end)
                return this.tree[node];
            if (start == end)
                return this.tree[node] = start;
            int mid = (start + end) / 2;
            return this.tree[node] = get_idx(update(start, mid, node * 2, index, value), update(mid + 1, end, node * 2 + 1, index, value));
        }
    }

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        // n : 수의 개수, m : 변경 횟수
        int n = Integer.parseInt(stk.nextToken());
        arr = new int[n];
        stk = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(br.readLine());

        SegTree sgTree = new SegTree(arr);

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            if (a == 2) {
                sb.append(sgTree.tree[1]+1).append("\n");
            }
            else {
                int b = Integer.parseInt(stk.nextToken());
                int c = Integer.parseInt(stk.nextToken());
                arr[b - 1] = c;
                sgTree.update(0, n - 1, 1, b - 1, c);
            }
        }

        System.out.print(sb);
    }
}