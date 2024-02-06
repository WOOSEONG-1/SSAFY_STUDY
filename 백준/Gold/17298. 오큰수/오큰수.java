import com.sun.source.tree.NewArrayTree;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*  1. 아이디어
        - Stack 이용
    2. 시간복잡도
        - O(N)
    3. 자료 구조
        - 배열 : 탐색 -> O(n) / 접근 -> O(1) / 삭제 : O(1) / 삽입 : O(1)
        - 스택 : 접근(1) / 삭제 (1) / 삽입 (1)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        Deque<Integer> dq = new ArrayDeque<>();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for ( int i = 0 ; i < n ; i++){
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        for ( int i = 0 ; i < n ; i++ ){
            while ( !dq.isEmpty() && arr[dq.peekLast()] < arr[i] ){
                ans[dq.pollLast()] = arr[i];
            }
            dq.addLast(i);
        }
        for ( int i : ans )
            sb.append(i).append(" ");
        System.out.print(sb);
    }
}