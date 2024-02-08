import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 *   - DP
 * 2. 시간 복잡도
 *   - N 1 ~ 1,500,000
 * 3. 자료구조
 *   - 배열
 */
public class Main {
    static Deque<Character> dq = new ArrayDeque<>(30);
    static Boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        char[] line = br.readLine().toCharArray();
        int ans = 0;
        int temp = 1;
        for (int i = 0 ; i < line.length ; i++ ) {
            char c = line[i];
            if (c == '(') {
                temp *= 2;
                dq.addLast(c);
            } else if ( c == '[') {
                temp *= 3;
                dq.addLast(c);
            } else if ( !dq.isEmpty() && c == ']' && dq.peekLast() == '[' ){
                dq.pollLast();
                if ( line[i-1] == '[')
                    ans += temp;
                temp /= 3;
            } else if ( !dq.isEmpty() && c == ')' && dq.peekLast() == '(' ){
                dq.pollLast();
                if ( line[i-1] == '(')
                    ans += temp;
                temp /= 2;
            }
            else {
                flag = true;
                break;
            }
        }
        if (!dq.isEmpty())
            flag = true;
        sb.append(flag ? 0 : ans);
        System.out.print(sb);
    }
}