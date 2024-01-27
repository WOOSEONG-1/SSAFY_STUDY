import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stk.nextToken());

        for ( int a = 0 ; a < n ; a++){
            stk = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(stk.nextToken());
            char[] s1 = stk.nextToken().toCharArray();
            for ( int i = 0 ; i < s1.length ; i++){
                for ( int j = 0 ; j < r ; j++)
                    System.out.print(s1[i]);
            }
            System.out.println();
        }

    }
}