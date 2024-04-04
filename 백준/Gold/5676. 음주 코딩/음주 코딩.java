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

            if (start == end) {
                int temp = arr[start];
                if ( temp > 0 )
                    temp = 1;
                else if ( temp < 0)
                    temp = -1;
                else
                    temp = 0;
                return this.tree[node] = temp;
            }
            int mid = (start + end) / 2;
            return this.tree[node] = init(start, mid, node * 2, arr) * init(mid + 1, end, node * 2 + 1, arr);
        }

        int mul(int start, int end, int node, int left, int right) {
            if (start > right || end < left)
                return 1;
            if (start >= left && end <= right)
                return this.tree[node];
            int mid = (start + end) / 2;
            return mul(start, mid, node * 2, left, right) * mul(mid + 1, end, node * 2 + 1, left, right);
        }

        int update(int start, int end, int node, int index, int value) {
            if (start > index || index > end)
                return this.tree[node];
            if (start == end)
                return this.tree[node] = value;
            int mid = (start + end) / 2;
            return this.tree[node] = update(start, mid, node * 2, index, value) * update(mid + 1, end, node * 2 + 1, index, value);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        String line;
        while ( (line=br.readLine()) != null ) {
            // n : 수의 개수, m : 변경 횟수
            StringTokenizer stk = new StringTokenizer(line);
            int n = Integer.parseInt(stk.nextToken());
            int m = Integer.parseInt(stk.nextToken());
            int[] arr = new int[n];
            stk = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++)
                arr[i] = Integer.parseInt(stk.nextToken());

            SegTree sgTree = new SegTree(arr);

            for (int i = 0; i < m; i++) {
                stk = new StringTokenizer(br.readLine());
                String a = stk.nextToken();
                int b = Integer.parseInt(stk.nextToken());
                int c = Integer.parseInt(stk.nextToken());
                if (a.equals("P")) {
                    int ans = sgTree.mul(0, n-1, 1, b-1, c-1);
                    if ( ans > 0 )
                        sb.append("+");
                    else if ( ans < 0)
                        sb.append("-");
                    else
                        sb.append("0");
                } else {
                    int temp = 0;
                    if ( c > 0 )
                        temp = 1;
                    else if ( c < 0 )
                        temp = -1;

                    sgTree.update(0, n - 1, 1, b - 1, temp);
                    arr[b - 1] = temp;
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}