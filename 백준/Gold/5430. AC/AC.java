import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 아이디어
 *  - 분할 정복
 *  -
 * 2. 시간 복잡도
 *  - reverse -> O(n) , Drop -> O(1)
 *  - 테케 7 / 커맨드 10만 /배열 10만    |  테케 100 / 커맨드 3500 / 배열 3500
 * 3. 자료구조
 *   - 배열, 우선순위 큐
 */
//1000000000000000000
public class Main {
    static ArrayList<String> cmd = new ArrayList<>();
    static Deque<Integer> res = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            char[] line = br.readLine().toCharArray();
            for (char c : line)
                cmd.add(Character.toString(c));
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer stk = new StringTokenizer(br.readLine(), "[],");
            for (int j = 0; j < n; j++)
                res.addLast(Integer.parseInt(stk.nextToken()));
            boolean flag = false;
            boolean is_rev = false;
            for (String c : cmd) {
                if (c.equals("R"))
                    is_rev = !is_rev;
                else {
                    if (!res.isEmpty()) {
                        if (is_rev)
                            res.pollLast();
                        else
                            res.pollFirst();
                    } else {
                        flag = true;
                        break;
                    }
                }
            }
            int size = res.size();
            if (flag) {
                sb.append("error");
            }
            else {
                sb.append("[");
                for (int j = 0; j < size; j++) {
                    if (is_rev) {
                        if (j >= 0 && j < size - 1) {
                            sb.append(res.pollLast()).append(",");
                        } else {
                            sb.append(res.pollLast());
                        }
                    } else {
                        if (j >= 0 && j < size - 1) {
                            sb.append(res.pollFirst()).append(",");
                        } else {
                            sb.append(res.pollFirst());
                        }
                    }
                }
                sb.append("]");
            }
            cmd.clear();
            res.clear();
            sb.append("\n");
        }
        System.out.print(sb);
    }
}