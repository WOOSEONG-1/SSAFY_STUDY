import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.IOException;

/* 11:25
 * 1. 아이디어
 * 	- 조건) 주사위의 모든 면은 0, 이동한 칸의 수가 0이면 주사위의 바닥면 수를 복사
 *  - 0이 아닌 경우에는 칸에 쓰여진 수가 주사위 바닥으로 가고, 칸은 0
 *  - 바깥으로 가는 명령은 무시
 *  - for문으로 명령 수행
 * 2. 시간 복잡도
 *	- 
 * 3. 자료구조
 * 	- HashMap으로 주사위 칸의 상하좌우 이동시 만나는 값 입력
*/
public class Main {
	
	static class Point {
		int y, x;
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	static HashMap<Integer, ArrayList<Integer>> rule = new HashMap<>();
	static int[][] map;
	static int[] dice;
	// 좌표이동
	static int[] dy = {0, 0, -1, 1};
	static int[] dx = {1, -1, 0, 0};
	static int n, m, ey, ex, d;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		// n,m : 세로, 가로 , k : 이동횟
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		ey = 0;
		ex = 0;
		int k = Integer.parseInt(stk.nextToken());
		d = 1;
		int sumv = 0;
		map = new int[n][m];
		dice = new int[7];
		for ( int i = 1; i <= 6 ; i++ )
			dice[i] = i;
		// 1234: 우좌상하 // 1 윗면 ~ 6 아랫면 // 기본 123456
		rule.put(2,  new ArrayList<>(Arrays.asList(3, 2, 6, 1, 5, 4)));
		rule.put(1,  new ArrayList<>(Arrays.asList(4, 2, 1, 6, 5, 3)));
		rule.put(3,  new ArrayList<>(Arrays.asList(5, 1, 3, 4, 6, 2)));
		rule.put(4,  new ArrayList<>(Arrays.asList(2, 6, 3, 4, 1, 5)));
		
		for ( int i = 0 ; i < n ; i++ ) {
			stk = new StringTokenizer(br.readLine());
			for ( int j = 0 ; j < m ; j++ ) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}		
		for ( int i = 0 ; i < k ; i++ ) {
			sumv += roll();
		}
		sb.append(sumv);
		System.out.print(sb);
	}

	// 주사위 굴리기
	static int roll() {
		Deque<Point> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[n][m];
		int[] new_dice = new int[7];
		// 다음 위치 지정
		int ny = ey + dy[d-1];
		int nx = ex + dx[d-1];
		// 주사위 이동이 가능한지
		if ( 0 <= ny && 0 <= nx && ny < n && nx < m ) {
			ey = ny;
			ex = nx;
			// 주사위 복사
			for ( int i = 1 ; i <= 6 ; i++ ) {
				new_dice[i] = dice[rule.get(d).get(i-1)];
			}						
		}
		// 이동 방향 칸이 없으면 반대로 구르기
		else {
			ny = ey - dy[d-1];
			nx = ex - dx[d-1];
			ey = ny;
			ex = nx;
			// 반대 방향으로
			if ( d == 1 )
				d = 2;
			else if ( d == 2)
				d = 1;
			else if ( d == 3)
				d = 4;
			else
				d = 3;
			// 주사위 굴리기
			for ( int i = 1 ; i <= 6 ; i++ ) {
				new_dice[i] = dice[rule.get(d).get(i-1)];
			}
		}
		// 점수 합산하기
		q.add(new Point(ny, nx));
		visit[ny][nx] = true;
		int cnt = 1;
		while ( !q.isEmpty() ) {
			Point now = q.poll();
			for ( int k = 0 ; k < 4 ; k++ ) {
				int by = now.y + dy[k];
				int bx = now.x + dx[k];
				if ( by >= 0 && bx >= 0 && by < n && bx < m && !visit[by][bx] ) {
					if ( map[by][bx] == map[ey][ex] ) {
						cnt++;
						visit[by][bx] = true;
						q.add(new Point(by,bx));
					}						
				}
			}
		}
		// 다음 방향 설정
		// A > map[ny][nx] : 시계 90
		if ( new_dice[6] > map[ny][nx]) {
			d = rotate(d, 0);
		}
		// A < B : 반시계 90
		else if ( new_dice[6] < map[ny][nx] ) {
			d = rotate(d, 1);
		}
		dice = new_dice;
		return cnt*map[ny][nx];
	}
	
	
	// 회전하기
	static int rotate(int dir, int c) {
		// 시계방향
		if ( c == 0 ) {
			if ( dir == 1) 
				dir = 4;
			else if ( dir == 2)
				dir = 3;
			else if ( dir == 3)
				dir = 1;
			else
				dir = 2;
		}
		// 반시계 방향
		else {
			if ( dir == 1 )
				dir = 3;
			else if ( dir == 2)
				dir = 4;
			else if ( dir == 3)
				dir = 2;
			else
				dir = 1;
		}
		return dir;
	}
	
}