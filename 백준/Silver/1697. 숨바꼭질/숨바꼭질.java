import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(stk.nextToken());
        int y = Integer.parseInt(stk.nextToken());
        int[] arr = new int [y+1];
        int idx = 0;
        while ( idx != y+1 ){
            if ( idx < x )
                arr[idx] = x-idx;
            else if ( idx == x )
                arr[idx] = 0;
            else{
                if ( idx % 2 == 0 )
                    arr[idx] = Math.min(arr[idx-1]+1, arr[idx/2]+1);
                else
                    arr[idx] = Math.min(arr[idx-1]+1, arr[(idx+1)/2]+2);
            }
            idx += 1;
        }
        sb.append(arr[y]);
        System.out.print(sb);
    }
}
