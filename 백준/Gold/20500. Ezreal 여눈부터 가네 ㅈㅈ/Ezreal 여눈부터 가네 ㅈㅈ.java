import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
* 1. 아이디어
*   - DP
*   - dp[i] = dp[i-1] + dp[i-2]*2 로 증가
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
        long[] dp_1 = new long[1516];
        dp_1[0] = 0;
        dp_1[1] = 0;
        dp_1[2] = 1;
        dp_1[3] = 1;
        for ( int i = 4 ; i < 1516 ; i++ ) {
            dp_1[i] = dp_1[i-1]%div+dp_1[i-2]%div*2;
        }
        sb.append(dp_1[n]%div);
        System.out.print(sb);
    }
}