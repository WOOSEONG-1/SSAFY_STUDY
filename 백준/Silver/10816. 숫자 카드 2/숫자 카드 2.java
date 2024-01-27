import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(br.readLine());
        HashMap<String,Integer> map = new HashMap<>();
        // 가진 카드
        for ( int i = 0 ; i < n ; i++){
            String num = stk.nextToken();
            if (map.containsKey(num))
                map.put(num, map.get(num)+1);
            else
                map.put(num, 1);
        }
        // 구해야할 카드
        stk = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(br.readLine());
        for ( int i = 0 ; i < m ; i++){
            String num = stk.nextToken();
            if ( map.containsKey(num) )
                sb.append(map.get(num)).append(" ");
            else
                sb.append(0).append(" ");
        }
        System.out.print(sb);
    }
}
