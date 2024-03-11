import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    
    static boolean[] visited, chk;
    static Stack<Integer> stk;
    static ArrayList<ArrayList<Integer>> lst;
    static int ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.next());

        for(int i=0; i<T; i++) {
            int N = Integer.parseInt(sc.next());

            lst = new ArrayList<>();
            lst.add(new ArrayList<>());

            for(int j=1; j<=N; j++) {
                lst.add(new ArrayList<>());

                int target = Integer.parseInt(sc.next());
                lst.get(j).add(target);
            }
            chk = new boolean[N+1];
            visited = new boolean[N+1];
            ans = 0;
            stk = new Stack<>();
            
            for(int j=1; j<=N; j++) {
                if(chk[j]) {
                    continue;
                }
                stk.clear();
                dfs(j);
            }
            System.out.println(ans);
        }
    }

    private static void dfs(int now) {
        if(chk[now]) {
            ans += stk.size();
            for (Integer integer : stk) {
                chk[integer] = true;
            }
            return;
        }
        if(visited[now]) {
            for (Integer integer : stk) {
                chk[integer] = true;
            }
            for(int i = 0; i< stk.size(); i++) {
                if(stk.get(i) == now) {
                    ans += i;
                    return;
                }
            }
        }

        visited[now] = true;
        stk.push(now);
        dfs(lst.get(now).get(0));
        visited[now] = false;
    }
}