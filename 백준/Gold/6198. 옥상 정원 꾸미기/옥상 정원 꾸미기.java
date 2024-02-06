import com.sun.source.tree.NewArrayTree;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*  1. 아이디어
        - tail peek한 후, 작은 값이면 push, 큰 값이면 pop한 다음 다시 peek
        -
    2. 시간복잡도
        - O(V+E) = 5,000,000
    3. 자료 구조
        - 배열 : 탐색 -> O(n) / 접근 -> O(1) / 삭제 : O(1) / 삽입 : O(1)
 */
public class Main {
    static class St{
        int num, idx, cnt;
        St(int num, int idx, int cnt){
            this.num = num;
            this.idx = idx;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        Deque<St> dq = new ArrayDeque<>();

        int n = Integer.parseInt(stk.nextToken());
        St[] arr = new St[n+1];

        for ( int i = 0 ; i < n ; i++ ){
            arr[i] = new St(Integer.parseInt(br.readLine()), i, 0);
        }
        arr[n] = new St(Integer.MAX_VALUE, n, 0);
        int[] ans = new int[n];
        for ( int i = 0 ; i <= n ; i++ ) {
            while (!dq.isEmpty() && dq.peekLast().num <= arr[i].num ) {
                dq.peekLast().cnt = i-dq.peekLast().idx-1;
                ans[dq.peekLast().idx] = dq.pollLast().cnt;
            }
            dq.addLast(arr[i]);
        }
        long cnt = 0;
        for ( int i : ans )
            if ( i != 0 )
                cnt += i;
        sb.append(cnt);
        System.out.print(sb);
    }
}