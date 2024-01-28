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
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for ( int i = 0 ; i < n ; i++ ){
            int cmd = Integer.parseInt(br.readLine());
            if ( cmd == 0){
                if( pq.isEmpty() )
                    sb.append(0).append("\n");
                else
                    sb.append(pq.poll()).append("\n");
            }
            else
                pq.add(cmd);
        }
        System.out.print(sb);
    }
}
