import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 *   - DP
 * 2. 시간 복잡도
 *   - N 1 ~ 1,500,000
 * 3. 자료구조
 *   - 배열
 */
public class Main {
    static class Work {
        int date, pay;

        Work(int date, int pay) {
            this.date = date;
            this.pay = pay;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 2];
        Work[] arr = new Work[n + 2];
        for (int i = 1; i <= n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            arr[i] = new Work(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
        }
        arr[n+1] = new Work(0, 0);
        int maxv = -1;
        for (int i = 1; i <= n+1; i++) {
            maxv = Math.max(maxv, dp[i]);
            if (i - 1 + arr[i].date <= n)
                dp[i + arr[i].date] = Math.max(dp[i + arr[i].date], arr[i].pay + maxv);
        }
        sb.append(dp[n+1]);
        System.out.print(sb);
    }
}