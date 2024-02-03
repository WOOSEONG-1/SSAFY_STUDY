import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*  1. 아이디어
        - 1,1 ~ N,N까지 이동해야함 / [n][n][k=2] 배열을 통해 저장 / k는 벽을 부술수 있는지 상태 체크
        - [i][j][0] 이면 다음 [ny][nx]가 1일 때, 통과 후 k값을 1로 변경
        - [i][j][k] == 0 이면 return -1
        - 방문 & 좌표 판정 후 -> [ny][nx][0~1] 각각 갱신 -> [1]인데 벽만나면 종료
            벽인지 판정 -> 벽이라면 [ny][nx][1]
    2. 시간복잡도
        - 5,000,000 * 2 * 2 = 20,000,000
    3. 자료 구조
        - 배열 : 탐색 -> O(n) / 접근 -> O(1) / 삭제 : O(1) / 삽입 : O(1)
 */
public class Main {
    static class Point{
        int y,x;
        Point(int y, int x){
            this.y = y;
            this.x =x;
        }
    }
    static Queue<Point> q = new LinkedList<>();
    static boolean[][][] visited;
    static int[][][] map;
    static int[] dy = {1,0,-1,0};
    static int[] dx = {0,1,0,-1};
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        map = new int [n][m][2];
        visited = new boolean[n][m][2];
        for ( int i = 0 ; i < n ; i++ ){
            char[] crr = br.readLine().toCharArray();
            for ( int j = 0 ; j < m ; j++ ){
                map[i][j][0] = Integer.parseInt(Character.toString(crr[j]));
                map[i][j][1] = Integer.parseInt(Character.toString(crr[j]));
            }
        }
        q.add(new Point(0,0));
        map[0][0][0] = 2;
        map[0][0][1] = 2;
        bfs();
        if (n==1 && m == 1)
            sb.append(1);
        else {
            for ( int i = 0 ; i < 2 ; i++ ) {
                if ( map[n-1][m-1][i] == 0 )
                    map[n-1][m-1][i] = Integer.MAX_VALUE;
            }
            if( map[n-1][m-1][0] == Integer.MAX_VALUE && map[n-1][m-1][1] == Integer.MAX_VALUE )
                sb.append(-1);
            else{
            sb.append(Math.min(map[n-1][m-1][0], map[n-1][m-1][1])-1);
            }
        }
        System.out.print(sb);
    }

    static void bfs(){
        while(!q.isEmpty()){
            Point temp = q.poll();
            int ey = temp.y;
            int ex = temp.x;
            for ( int k = 0 ; k < 4 ; k++ ){
                int ny = ey + dy[k];
                int nx = ex + dx[k];
                if ( 0 <= nx && nx < m && 0 <= ny && ny < n ){
                    if ( map[ey][ex][0] > 1 && !visited[ny][nx][0] && map[ny][nx][0] == 0 ){
                        map[ny][nx][0] = map[ey][ex][0] + 1;
                        visited[ny][nx][0] = true;
                        q.add(new Point(ny, nx));
                    }
                    else if ( map[ey][ex][0] > 1 && !visited[ny][nx][1] && map[ny][nx][1] == 1 ){
                        map[ny][nx][1] = map[ey][ex][0] + 1;
                        visited[ny][nx][1] = true;
                        q.add(new Point(ny, nx));
                    }
                    else if ( map[ey][ex][1] > 2 && !visited[ny][nx][1] && map[ny][nx][1] == 0 ){
                        map[ny][nx][1] = map[ey][ex][1] + 1;
                        visited[ny][nx][1] = true;
                        q.add(new Point(ny,nx));
                    }
                }
            }
        }
    }
}