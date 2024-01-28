import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;
//
//
public class Main{
    static class Point {
        int x, y;
        Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    static int n,m;
    static boolean[][] visited;
    static int[][] arr;
    static Queue<Point> q;
    static int[] dy = {1,0,-1,0};
    static int[] dx = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m];
        q = new LinkedList<>();
        for ( int i = 0 ; i < n ; i++ ){
            stk = new StringTokenizer(br.readLine());
            for ( int j = 0; j < m ; j++){
                arr[i][j] = Integer.parseInt(stk.nextToken());
                if (arr[i][j] == 2){
                    q.add(new Point(i,j));
                    arr[i][j] = 2;
                    visited[i][j] = true;
                }
            }
        }
        bfs();
        print(arr, sb);
        System.out.print(sb);
    }
    static void bfs(){
        while(!q.isEmpty()){
            Point temp = q.poll();
            for ( int k = 0 ; k < 4 ; k++){
                int ny = temp.y + dy[k];
                int nx = temp.x + dx[k];
                if( ny>=0 && ny < n && nx >=0 && nx < m && arr[ny][nx] != 0 ){
                    if(!visited[ny][nx]){
                        visited[ny][nx] = true;
                        arr[ny][nx] = arr[temp.y][temp.x] + 1;
                        q.add(new Point(ny,nx));
                    }
                }
            }
        }
    }

    static void print(int[][] arr, StringBuffer sb){
        for ( int[] ar : arr){
            for ( int a : ar ){
                if (a == 1)
                    sb.append(-1).append(" ");
                else if (a == 0)
                    sb.append(0).append(" ");
                else
                    sb.append(a-2).append(" ");
            }
            sb.append("\n");
        }
    }

}
