import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.HashMap;

/*
 * 1. 아이디어
 *  - 분할 정복
 *  -
 * 2. 시간 복잡도
 *  - n 1 ~ 1,000,000,000,000,000,000
 *  -
 * 3. 자료구조
 *   - 배열, 우선순위 큐
 */
//1000000000000000000
public class Main {
    static Deque<String> num_stack;
    static Deque<oper> oper_stack;

    static class oper {
        String s;
        int pri;

        oper(String s, int pri) {
            this.s = s;
            this.pri = pri;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        oper_stack = new ArrayDeque<>();
        num_stack = new ArrayDeque<>();
        String line = br.readLine();
        HashMap<String, Integer> prioirty = new HashMap<>();
        prioirty.put("(", 3);
        prioirty.put(")", 3);
        prioirty.put("+", 1);
        prioirty.put("-", 1);
        prioirty.put("*", 2);
        prioirty.put("/", 2);
        prioirty.put("%", 2);
        int pri_now = 0;
        for (int i = 0; i < line.length(); i++) {
            String op = String.valueOf(line.charAt(i));
            char c = op.charAt(0);
            if ( (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z'))
                num_stack.addLast(op);
            else {
                int pri_op = pri_now + prioirty.get(op);
                if (op.equals("(")){
                    pri_now += 3;
                    continue;
                }
                else if (op.equals(")")){
                    pri_now -= 3;
                    continue;
                }
                else {
                    while (!oper_stack.isEmpty() && oper_stack.peekLast().pri >= pri_op) {
                        num_stack.addLast(oper_stack.pollLast().s);
                    }
                }
                oper_stack.addLast(new oper(op, pri_op));
            }
        }
        while (!oper_stack.isEmpty()) {
            num_stack.addLast(oper_stack.pollLast().s);
        }
        while (!num_stack.isEmpty()) {
            sb.append(num_stack.pollFirst());
        }
        System.out.print(sb);
    }
}