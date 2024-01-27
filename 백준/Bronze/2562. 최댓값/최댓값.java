import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer stk = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();
        int cnt = 0;
        int maxv = 0;
        int max_cnt = 0;
        for ( int i = 0 ; i < 9 ; i++){
            int n = Integer.parseInt(br.readLine());
            cnt++;
            if(Math.max(maxv, n) > maxv ){
                maxv = n;
                max_cnt = cnt;
            }
        }
        System.out.println(maxv);
        System.out.print(max_cnt);


    }
}