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
        int m = Integer.parseInt(stk.nextToken());
        Deque<Integer> dq = new ArrayDeque<>();
        for ( int i = 1 ; i <= n ; i++){
            dq.addLast(i);
        }
        sb.append("<");
        while (!dq.isEmpty()){
            for ( int i = 0 ; i < m-1 ; i++){
                dq.addLast(dq.pop());
            }
            if (dq.size() != 1)
                sb.append(dq.pop()).append(", ");
            else
                sb.append(dq.pop()).append(">");
        }
        System.out.print(sb);
    }
}
