import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;
//
//
public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int[] arr = new int[n];
        int cnt = 0;
        for ( int i = 0 ; i < n ; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        for ( int i = n-1 ; i >= 0 ; i--){
            if ( m == 0 )
                break;
            if ( m >= arr[i] ){
                cnt += m / arr[i];
                m = m % arr[i];
            }
        }
        sb.append(cnt);
        System.out.print(sb);
    }

}
