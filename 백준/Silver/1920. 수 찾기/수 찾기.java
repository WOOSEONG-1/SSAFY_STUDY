import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for ( int i = 0 ; i < n ; i++ ){
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine());

        for ( int i = 0 ; i < m ; i++ ){
            int a = Integer.parseInt(stk.nextToken());
            sb.append(b_search(arr, a, 0, n-1)).append("\n");
        }
        System.out.print(sb);
    }

    static int b_search(int[] arr, int find, int left, int right){

        if ( left > right )
            return 0;

        int mid = ( left + right) /2;

        if ( arr[mid] == find )
            return 1;
        else if ( arr[mid] < find )
            return b_search(arr, find, mid+1, right);
        else
            return b_search(arr, find, left, mid-1);
    }
}