import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        char[] crr = br.readLine().toCharArray();
        int sum = 0;
        for ( int i = 0 ; i < n ; i++ ){
            sum += Integer.parseInt(Character.toString(crr[i]));
        }
        sb.append(sum);
        br.close();
        System.out.print(sb);
    }
}