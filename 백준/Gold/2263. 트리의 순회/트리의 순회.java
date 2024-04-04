import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;;
import java.util.StringTokenizer;
/*
* 1. 아이디어
*   - 트리 순회 ( 분할정복 )
* 2. 시간 복잡도 -> n(1 ≤ n ≤ 100,000)
*   - 제한 : 5초
* */

public class Main {
    static int[] post_ord, in_ord;
    static int n;
    static ArrayList<Integer> pre_ord;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        n = Integer.parseInt(br.readLine());
        post_ord = new int[n];
        in_ord = new int[n];
        pre_ord = new ArrayList<>(n);
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for ( int i = 0 ; i < n ; i++ )
            in_ord[i] = Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(br.readLine());
        for ( int i = 0 ; i < n ; i++ )
            post_ord[i] = Integer.parseInt(stk.nextToken());
        pre(0, n-1, 0, n-1);

        for ( int i : pre_ord)
            sb.append(i).append(" ");

        System.out.print(sb);
    }

    static void pre(int in_left, int in_right, int post_left, int post_right){
        if ( in_left > in_right || post_left > post_right )
            return;
        pre_ord.add(post_ord[post_right]);
        if ( in_left == in_right || post_left == post_right )
            return;
        int find = -1;
        for ( int i = 0 ; i < n ; i++ ){
            if ( in_ord[i] == post_ord[post_right] ){
                find = i;
                break;
            }
        }
        // 왼쪽
        pre( in_left, find - 1, post_left, post_left + find - in_left - 1 );
        // 오른쪽
        pre( find + 1, in_right, post_left + find - in_left, post_right - 1);
    }
}