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
        Deque<Integer> st = new ArrayDeque<>();
        for ( int i = 0 ; i < n ; i++ ){
            stk = new StringTokenizer(br.readLine());
            String cmd = stk.nextToken();
            if ( cmd.equals("push") )
                st.add(Integer.parseInt(stk.nextToken()));
            else if ( cmd.equals("front") )
                if (st.isEmpty())
                    sb.append(-1).append("\n");
                else
                    sb.append(st.peek()).append("\n");
            else if ( cmd.equals("back") )
                if (st.isEmpty())
                    sb.append(-1).append("\n");
                else
                    sb.append(st.peekLast()).append("\n");
            else if ( cmd.equals("size") )
                sb.append(st.size()).append("\n");
            else if ( cmd.equals("empty") ){
                if (st.isEmpty())
                    sb.append(1).append("\n");
                else
                    sb.append(0).append("\n");
            }
            else if ( cmd.equals("pop")){
                if (st.isEmpty())
                    sb.append(-1).append("\n");
                else
                    sb.append(st.poll()).append("\n");
            }
        }
        System.out.print(sb);
    }
}
