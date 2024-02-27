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
	static HashMap<Integer, ArrayList<Integer>> rule = new HashMap<>();
	static int[][] map;
	static int[] dice;
	static int n, m, ey, ex;
	static StringBuffer sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		ey = Integer.parseInt(stk.nextToken());
		ex = Integer.parseInt(stk.nextToken());
		int k = Integer.parseInt(stk.nextToken());
		map = new int[n][m];
		dice = new int[7];
		int[] cmd_lst = new int[k];
		// 1234: 좌우상하 // 1 윗면 ~ 6 아랫면 // 기본 123456
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
		
		stk = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < k ; i++ ) {
			cmd_lst[i] = Integer.parseInt(stk.nextToken());
			roll(cmd_lst[i]);
		}
		
		System.out.print(sb);
	}
	// 좌표이동
	static int[] dy = {0, 0, -1, 1};
	static int[] dx = {1, -1, 0, 0};
	// 주사위 굴리기
	static void roll(int cmd) {

		int[] new_dice = new int[7];
		// 다음 위치 지정
		int ny = ey + dy[cmd-1];
		int nx = ex + dx[cmd-1];
		// 주사위 이동이 가능한지
		if ( 0 <= ny && 0 <= nx && ny < n && nx < m ) {
			ey = ny;
			ex = nx;
			// 주사위 복사
			for ( int i = 1 ; i <= 6 ; i++ ) {
				new_dice[i] = dice[rule.get(cmd).get(i-1)];
			}
			if ( map[ny][nx] != 0) {
				// 굴린 바닥면에 수 복사
				new_dice[6] = map[ny][nx];
				map[ny][nx] = 0;
			}
			else {
				map[ny][nx] = new_dice[6];
			}
			sb.append(new_dice[1]).append("\n");
			dice = new_dice;
		}		
		
	}
	
	
}