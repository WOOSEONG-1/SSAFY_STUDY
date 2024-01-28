import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;
//
//
public class Main{
    static Queue<Integer> q;
    static int cnt, n;
    static int[][] arr;
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        q = new LinkedList<>();
        n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        arr = new int [n+1][n+1];
        visited = new boolean[n+1];
        cnt = 0;
        ArrayList<ArrayList<Integer>> lst = new ArrayList<>();
        for ( int i = 0 ; i < m ; i++){
            ArrayList<Integer> temp = new ArrayList<>();
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            arr[a][b] = 1;
            arr[b][a] = 1;
        }
        for ( int i = 1 ; i <= n ; i++ ){
            if (!visited[i]){
                q.add(i);
                bfs(i);
            }
        }
        sb.append(cnt);
        System.out.print(sb);
    }

    static void bfs(int node){
        while(!q.isEmpty()){
            int temp = q.poll();
            for ( int i = 1 ; i <= n ; i++ ){
                if(!visited[i] && arr[temp][i] == 1 ){
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
        cnt++;
    }
}
