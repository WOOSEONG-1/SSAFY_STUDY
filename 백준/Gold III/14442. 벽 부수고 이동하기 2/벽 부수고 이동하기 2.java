import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*  1. 아이디어
        - i,j 에 대한 bfs , 1을 만나면 i,j,k+1 을 큐에 대입
    2. 시간복잡도
        - O(V+E) = 5,000,000
    3. 자료 구조
        - 배열 : 탐색 -> O(n) / 접근 -> O(1) / 삭제 : O(1) / 삽입 : O(1)
 */
public class Main {
    static class Point {
        int y, x, d, c;
        Point(int y, int x, int d, int c) {
            this.y = y;
            this.x = x;
            this.d = d;
            this.c = c;
        }
    }
    static Queue<Point> q = new LinkedList<>();
    static boolean[][][] visited;
    static int[][] map;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int n, m, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken()) + 1;
        map = new int[n][m];
        visited = new boolean[n][m][k];
        for (int i = 0; i < n; i++) {
            String s1 = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s1.charAt(j) - '0'; 
            }
        }
        q.add(new Point(0, 0, 0, 1));
        visited[0][0][0] = true;
        sb.append(bfs());        
        System.out.print(sb);
    }

    static int bfs() {
        while (!q.isEmpty()) {        	
            Point temp = q.poll();
            int ey = temp.y;
            int ex = temp.x;
            int ek = temp.d;
            int ec = temp.c;
            if( ey == n-1 && ex == m-1 ){
                q.clear();
                return ec;
            }
            for (int w = 0; w < 4; w++) {
                int ny = ey + dy[w];
                int nx = ex + dx[w];
                if ( nx < 0 || nx >= m || ny < 0 || ny >= n )
                    continue;
                else {
                    if (map[ny][nx] == 0 && !visited[ny][nx][ek] ) {
                        visited[ny][nx][ek] = true;
                        q.add(new Point(ny, nx, ek, ec + 1));
                    } else if (map[ny][nx] == 1 && ek != k - 1 && !visited[ny][nx][ek+1] ) {
                        visited[ny][nx][ek+1] = true;
                        q.add(new Point(ny, nx, ek + 1, ec + 1));
                    }
                }
            }
        }
        return -1;
    }
}