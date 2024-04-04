import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];
        int len = 1;
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            arr[i] = Integer.parseInt(stk.nextToken());

        for (int i = 1; i <= n; i++) {
            int idx = b_search(dp, arr[i], 0, len - 1);
            if (idx == -1) {
                dp[len] = arr[i];
                len += 1;
            } else
                dp[idx] = arr[i];
        }
        sb.append(len - 1);
        System.out.print(sb);
    }

    static int b_search(int[] arr, int find, int lo, int hi) {

        while (true) {
            int mid = (lo + hi) / 2;

            if ( lo > hi )
                return -1;

            if (arr[mid] < find && find <= arr[mid + 1])
                return mid + 1;

            if (find > arr[mid])
                lo = mid+1;

            else if (find <= arr[mid])
                hi = mid-1;
        }

    }

}