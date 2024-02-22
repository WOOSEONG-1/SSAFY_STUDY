import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

/*
 * 1. 아이디어
 * 	- 궁수 공격 -> 적 이동( 아래 한칸, 성에 도착시 제외 ) -> 적 없어지면 게임 끝
 *  - 궁수의 위치 -> 조합
 * 2. 시간 복잡도
 *	- 조합 : 455
 *  - 조합 값 -> defense(i, j ,k) -> while time ( shoot -> move )
 * 3. 자료구조 :
 * 	- 배열
 * */
public class Main {
    static class Point implements Comparable<Point> {
        int y, x, cnt;

        Point(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            if (this.cnt > o.cnt)
                return 1;
            else if (this.cnt == o.cnt)
                if (this.x > o.x)
                    return 1;
                else
                    return -1;
            else
                return -1;
        }
    }

    static int[] dy = {0, -1, 0};
    static int[] dx = {-1, 0, 1};
    static int n, c, d;
    static int[][] map;
    static int maxv, mob_cnt, mob;
    static boolean[] brr;
    static ArrayList<Point> p_lst = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        // r : row, c : col, d : 범위
        n = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());
        d = Integer.parseInt(stk.nextToken());
        map = new int[n + 1][c];
        Arrays.fill(map[n], 7);
        // brr : 조합 제어용 배열
        brr = new boolean[c];
        // 초기화 0: 빈 땅, 1: 적, 5: 궁수, 7: 성벽
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
                if (map[i][j] == 1) mob_cnt++;
            }
        }
        comb(0, 3);
        sb.append(maxv);
        System.out.print(sb);
    }

    // 조합
    static void comb(int k, int r) {
        // 조합 산출
        if (r == 0) {
            int[] ranger = new int[3];
            int idx = 0;
            for (int i = 0; i < c; i++) {
                if (brr[i]) {
                    ranger[idx++] = i;
                }
            }
            defense(ranger);
            return;
        }
        for (int i = k; i < c; i++) {
            if (!brr[i]) {
                brr[i] = true;
                comb(i + 1, r - 1);
                brr[i] = false;
            }
        }
    }

    // 게임 시작
    static void defense(int[] ranger) {
        Deque<Point> q = new ArrayDeque<>();
        ArrayList<Point> dead = new ArrayList<>();
        int kill = 0;
        mob = mob_cnt;
        // 맵 복사
        int[][] temp = new int[n + 1][c];
        for (int i = 0; i <= n; i++) {
            temp[i] = map[i].clone();
        }
        // 큐에 넣어서 돌리기
        while (kill != mob) {
            for (int i : ranger) {
            	if ( check(temp, i) ) {
            		Collections.sort(p_lst);
            		Point mob_pos = p_lst.get(0);
            		temp[mob_pos.y][mob_pos.x] = 4;
            		kill += 1;
            		dead.add(mob_pos);
            		p_lst.clear();
            	}
            	else
            		continue;  	
            }
            // 몬스터 전진
            for ( Point p : dead )
            	temp[p.y][p.x] = 0; 
            dead.clear();
            
            temp = move(temp);
        }
        maxv = Math.max(maxv, kill);
    }
    // 몬스터 이동
    static int[][] move(int[][] M) {
        for (int j = 0; j < c; j++) {
            if (M[n - 1][j] == 1) {
                mob--;
                M[n - 1][j] = 0;
            }
        }
        for (int i = 0; i < c; i++) {
            for (int j = n - 1; j >= 1; j--) {
                M[j][i] = M[j - 1][i];
            }
        }
        Arrays.fill(M[0], 0);
        return M;
    }
    
    // 몬스터 처치
    static boolean check(int[][] temp, int pos) {
    	Deque<Point> q = new ArrayDeque<>();
    	ArrayList<Point> lst = new ArrayList<>();
    	q.add(new Point(n, pos, 0));
    	boolean[][] visit = new boolean[n+1][c];
    	visit[n][pos] = true;
    	while(!q.isEmpty()) {
    		Point now = q.poll();
    		int ey = now.y;
    		int ex = now.x;
    		int ec = now.cnt;
    		if ( ec >= d ) continue;
    		for ( int k = 0 ; k < 3 ; k++ ) {
    			int ny = ey + dy[k];
    			int nx = ex + dx[k];
    			if ( ny >= 0 && nx >= 0 && ny < n && nx < c && !visit[ny][nx] ) {
    				if ( temp[ny][nx] == 4 && ec + 1 <= d ) {
    					return false;
    				}
    				else if (temp[ny][nx] == 1 && ec + 1 <= d) {
    					p_lst.add(new Point(ny, nx, ec + 1 ));    					
    					return true;
    				}
    				else { 
    					q.add(new Point(ny, nx, ec + 1));
    					visit[ny][nx] = true;
    				}
    			}
    		}
    	}
    	return false;
    }
    
}