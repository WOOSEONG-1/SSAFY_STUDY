import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
// 하노이의 탑 ( 분할 정복, 재귀 )
// 
public class Main {
    static int cnt = 0;
    static StringBuffer sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuffer();
        int n = Integer.parseInt(br.readLine());
        hanoi (n, 1, 3, 2);
        System.out.println(cnt);
        System.out.print(sb);
    }
    static void hanoi(int n, int start, int end, int via){
        if ( n == 1 )
            move(n, start, end);
        else{
            hanoi(n-1, start, via, end);
            move(n, start, end);
            hanoi(n-1, via, end, start);
        }
    }
    static void move(int n, int start, int to){
        cnt ++;
        sb.append(start).append(" ").append(to).append("\n");
    }
}