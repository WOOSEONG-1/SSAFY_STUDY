import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 *   - 분할 정복
 * 2. 시간 복잡도
 *   - O(N^2)
 * 3. 자료구조
 *   - 배열
 */

public class Main {
    static List<ArrayList<Integer>> lst;
    static boolean[] visited;
    static int cnt = 0;
    static int rm;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        lst = new ArrayList();
        for ( int i = 0 ; i < n ; i++ )
            lst.add(new ArrayList<Integer>());
        int root = -1;
        for ( int i = 0 ; i < n ; i++ ){
            int node = Integer.parseInt(stk.nextToken());
            if (node == -1)
                root = i;
            else{
                lst.get(node).add(i);
            }
        }
        rm = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        visited[root] = true;
        visited[rm] = true;
        if ( root != rm )
            dfs(root);
        sb.append(cnt);
        System.out.print(sb);
    }
    static void dfs(int root){
        if ( lst.get(root).contains(rm) ){
            if ( lst.get(root).size() == 1){
                cnt++;
                return;
            }
        }
        if ( lst.get(root).isEmpty() ){
            cnt++;
            return;
        }
        for ( int a : lst.get(root) ){
            if ( !visited[a] ){
                visited[a] = true;
                dfs(a);
            }
        }
    }
}