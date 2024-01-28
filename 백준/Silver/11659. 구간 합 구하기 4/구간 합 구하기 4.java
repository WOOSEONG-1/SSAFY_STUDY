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
        Queue<Integer> q = new LinkedList<>();
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(br.readLine());
        int arr[] = new int [n+1];

        for ( int i = 1 ; i <= n ; i++ ){
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        for ( int i = 1 ; i <= n ; i++ ){
            arr[i] = arr[i-1]+arr[i];
        }
        // 5 4 3 2 1
        // 5 9 12 14 15 arr
        //
        for ( int i = 0 ; i < m ; i++ ){
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken())-1;
            int b = Integer.parseInt(stk.nextToken());
            sb.append(arr[b]-arr[a]).append("\n");
        }
        System.out.print(sb);
    }
}
