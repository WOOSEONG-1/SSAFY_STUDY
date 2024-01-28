import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk;
        int n = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        for ( int i = 0 ; i < n ; i++ ){
            stk = new StringTokenizer(br.readLine());
            String cmd = stk.nextToken();
            if ( cmd.equals("add") ){
                set.add(Integer.parseInt(stk.nextToken()));
            }
            else if ( cmd.equals("check") ){
                int num = Integer.parseInt(stk.nextToken());
                if ( set.contains(num) )
                    sb.append(1).append("\n");
                else
                    sb.append(0).append("\n");
            }
            else if ( cmd.equals("remove")){
                int num = Integer.parseInt(stk.nextToken());
                if ( set.contains(num)){
                    set.remove(num);
                }
                else {
                    continue;
                }
            }
            else if ( cmd.equals("toggle")){
                int num = Integer.parseInt(stk.nextToken());
                if ( set.contains(num)){
                    set.remove(num);
                }
                else {
                    set.add(num);
                }
            }
            else if ( cmd.equals("all") ){
                for ( int j = 1 ; j <= 20 ; j++){
                    set.add(j);
                }
            }
            else if ( cmd.equals("empty") ){
                set.clear();
            }
        }



        System.out.print(sb);
    }
}
