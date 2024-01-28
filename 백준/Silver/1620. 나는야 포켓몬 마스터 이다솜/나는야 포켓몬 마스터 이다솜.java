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
        int arr[] = new int [n+1];
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<Integer, String> map_2 = new HashMap<>();
        for ( int i = 1 ; i <= n ; i++ ){
            stk = new StringTokenizer(br.readLine());
            String name = stk.nextToken();
            map.put(name, i);
            map_2.put(i, name);
        }
        for ( int i = 0; i < m ; i++ ){
            stk = new StringTokenizer(br.readLine());
            String cmd = stk.nextToken();
            if ( map.containsKey(cmd) )
                sb.append( map.get(cmd) ).append("\n");
            else
                sb.append(map_2.get(Integer.parseInt(cmd))).append("\n");
        }
        System.out.print(sb);
    }
}
