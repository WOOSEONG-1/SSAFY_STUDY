import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 *   - 세그먼트 트리
 * 2. 시간 복잡도
 *   - O(NlogN)
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
            init_odd(0, N - 1, 1, arr);
            init_even(0, N - 1, 1, arr);
        }

        // 초기화
        int init_odd(int start, int end, int node, int[] arr) {
            if (start == end) {
                if (arr[start] % 2 == 1)
                    return this.tree[0][node] = 1;
                else
                    return 0;
            }
            int mid = (start + end) / 2;
            return this.tree[0][node] = init_odd(start, mid, node * 2, arr) + init_odd(mid + 1, end, node * 2 + 1, arr);
        }

        int init_even(int start, int end, int node, int[] arr) {
            if (start == end) {
                if (arr[start] % 2 == 0)
                    return this.tree[1][node] = 1;
                else
                    return 0;
            }
            int mid = (start + end) / 2;
            return this.tree[1][node] = init_even(start, mid, node * 2, arr)
                    + init_even(mid + 1, end, node * 2 + 1, arr);
        }

        // 쿼리
        int find_odd(int start, int end, int node, int left, int right) {
            if (start > right || left > end)
                return 0;
            if (start >= left && end <= right)
                return this.tree[0][node];
            int mid = (start + end) / 2;
            return find_odd(start, mid, node * 2, left, right) +
                    find_odd(mid + 1, end, node * 2 + 1, left, right);
        }

        int find_even(int start, int end, int node, int left, int right) {
            if (start > right || left > end)
                return 0;
            if (start >= left && end <= right)
                return this.tree[1][node];
            int mid = (start + end) / 2;
            return find_even(start, mid, node * 2, left, right) +
                    find_even(mid + 1, end, node * 2 + 1, left, right);
        }

        // 갱신
        int update_odd(int start, int end, int node, int index, int value) {
            if (index < start || index > end)
                return this.tree[0][node];
            if (start == end) {
                if (arr[start] % 2 == 1)
                    return this.tree[0][node] = 1;
                else
                    return this.tree[0][node] = 0;
            }
            int mid = (start + end) / 2;
            return this.tree[0][node] = update_odd(start, mid, node * 2, index, value) +
                    update_odd(mid + 1, end, node * 2 + 1, index, value);
        }

        int update_even(int start, int end, int node, int index, int value) {
            if (index < start || index > end)
                return this.tree[1][node];
            if (start == end) {
                if (arr[start] % 2 == 0) {
                    return this.tree[1][node] = 1;
                } else
                    return this.tree[1][node] = 0;

            }

            int mid = (start + end) / 2;
            return this.tree[1][node] = update_even(start, mid, node * 2, index, value) +
                    update_even(mid + 1, end, node * 2 + 1, index, value);
        }
    }

    // input data 저장 배열
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        SegTree sg = new SegTree(arr);
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            if (a == 1) {
                arr[b - 1] = c;
                sg.update_odd(0, n - 1, 1, b - 1, c);
                sg.update_even(0, n - 1, 1, b - 1, c);
            } else if (a == 2) {
                sb.append(sg.find_even(0, n - 1, 1, b - 1, c - 1)).append("\n");
            } else {
                sb.append(sg.find_odd(0, n - 1, 1, b - 1, c - 1)).append("\n");
            }

        }
        System.out.print(sb);
    }

}