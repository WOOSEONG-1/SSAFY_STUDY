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
        int m = Integer.parseInt(stk.nextToken());
        HashSet<String> set_1 = new HashSet<>();
        HashSet<String> set_2 = new HashSet<>();
        for ( int i = 0 ; i < n ; i++ ){
            set_1.add(br.readLine());
        }
        for ( int i = 0 ; i < m ; i++){
            set_2.add(br.readLine());
        }
        set_1.retainAll(set_2);

        String[] s1 = set_1.toArray(new String[0]);
        Arrays.sort(s1);
        sb.append(s1.length).append("\n");
        for ( String s : s1){
            sb.append(s).append("\n");
        }
        System.out.print(sb);
    }
}
