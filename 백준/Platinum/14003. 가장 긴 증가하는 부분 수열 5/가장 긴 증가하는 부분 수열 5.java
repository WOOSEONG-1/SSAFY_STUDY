import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] index = new int[n];
        ArrayList<Integer> dp = new ArrayList<>();

        StringTokenizer stk = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
            if ( dp.isEmpty()|| dp.get(dp.size()-1) < arr[i] ){
                dp.add(arr[i]);
                index[i] = dp.size()-1;
                continue;
            }
            int idx = Collections.binarySearch(dp, arr[i]);
            if ( idx < 0 )
                idx = -idx - 1;
            index[i] = idx;
            if ( idx == 0 && arr[i] == dp.get(0) ) continue;
            dp.set(idx, arr[i]);
        }
        sb.append(dp.size()).append("\n");
        ArrayList<Integer> res = new ArrayList<>();
        int len = dp.size()-1;
        for ( int i = n-1 ; i >= 0 ; i-- ){
            if ( index[i] == len ){
                res.add(arr[i]);
                len--;
            }
        }

        Collections.reverse(res);
        for ( int i : res )
            sb.append(i).append(" ");

        System.out.print(sb);
    }

}