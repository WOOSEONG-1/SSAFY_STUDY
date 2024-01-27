import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stk.nextToken());
//        int m = Integer.parseInt(stk.nextToken());
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        stk = new StringTokenizer(br.readLine());
        for( int i = 0; i< n;i++){
            int t = Integer.parseInt(stk.nextToken());
            if (Math.max(max, t)>max)
                max = t;
            if (Math.min(min, t)<min)
                min = t;
        }
        sb.append(min).append(" ").append(max);
//        sb.append(n+m).append("\n");
//        sb.append(n-m).append("\n");
        System.out.print(sb);
    }
}