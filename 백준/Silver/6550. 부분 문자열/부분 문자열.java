import java.io.*;
import java.util.*;
//
//
public class Main {
    static char[] s1, s2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
//        StringTokenizer stk = new StringTokenizer(br.readLine());
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer stk = new StringTokenizer(line);
            s1 = stk.nextToken().toCharArray();
            s2 = stk.nextToken().toCharArray();
            int cnt = 0;
            for (int i = 0; i < s2.length ; i++) {
                if (s2[i] == s1[cnt]) {
                    cnt++;
                    if ( cnt == s1.length )
                        break;
                }
            }
            if ( cnt == s1.length ){
                sb.append("Yes").append("\n");
            }
            else
                sb.append("No").append("\n");
        }
        System.out.print(sb);
    }
}
