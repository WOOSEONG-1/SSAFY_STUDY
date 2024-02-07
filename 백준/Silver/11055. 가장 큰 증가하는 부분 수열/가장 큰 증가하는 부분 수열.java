import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
* 1. 아이디어
*   - DP
*   -
* 2. 시간 복잡도
*   - N 1 ~ 1515 / 1 000 000 007 로 나누기
* 3. 자료구조
*   -
*/

public class Main {
    static int div = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int [1001];
        int[] dp = new int [1001];
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int ans = 0;
        for ( int i = 1 ; i <= n ; i++ ){
            int maxv = 0;
            arr[i] = Integer.parseInt(stk.nextToken());
            for ( int j = 1 ; j < i ; j++ ){
                if( arr[i] > arr[j] && maxv < dp[j] )
                    maxv = dp[j];
            }
            dp[i] = maxv + arr[i];
            if ( ans < dp[i] )
                ans = dp[i];
        }
        sb.append(ans);
        System.out.print(sb);
    }
}