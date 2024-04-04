import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 * 	- for문으로 매 초 move(direction) 함수로 이동, 
 *  
 * 2. 시간 복잡도
 * 
 * 3. 자료구조
 * 
 * 
 * 
 * */
public class Main {
	
	static class Point{
		int y, x;
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	static class Command implements Comparable<Command> {
		String s;
		int sec;

		Command(String s, int sec) {
			this.s = s;
			this.sec = sec;
		}

		@Override
		public int compareTo(Command o) {
			if (this.sec < o.sec)
				return -1;
			else if (this.sec == o.sec)
				return 0;
			else
				return 1;
		}

	}

	static int[] dy = { 1, -1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };
	static int[][] map;
	static ArrayList<Command> c_lst = new ArrayList<>();
	static int longth = 1;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		// n : N x N 크기, k : 사과 개수
		n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		map = new int[n + 1][n + 1];
		map[0][0] = 1;
		// 사과 분배
		for (int i = 0; i < k; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(stk.nextToken())-1;
			int x = Integer.parseInt(stk.nextToken())-1;
			map[y][x] = 7;
		}
		// 방향 전환
		int l = Integer.parseInt(br.readLine());
		for (int i = 0; i < l; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(stk.nextToken());
			String cmd = stk.nextToken();
			c_lst.add(new Command(cmd, sec));
		}
		Collections.sort(c_lst);
		// 알고리즘
		sb.append(snake());
		System.out.println(sb);
	}

	static int snake() {
		// c_lst 제어용
		int idx = 0;
		int time = 0;
		int direction = 2;
		Deque<Point> q = new ArrayDeque<>();
		q.add(new Point(0, 0));
		// 뱀 이동 -> 꼬리를 poll하고 나머지 좌표들로 이동 -> 사과먹으면 꼬리 추가
		while (true) {
			time += 1;
			// 꼬리로 길이 판별
			Point head = q.peekFirst();
			Point tail = q.peekLast();
			int ny = head.y + dy[direction];
			int nx = head.x + dx[direction];
			
			if (ny >= 0 && ny < n && nx >= 0 && nx < n && map[ny][nx] != 1) {
				if (map[ny][nx] != 7) {
					map[tail.y][tail.x] = 0;
					q.pollLast();
				}
				map[ny][nx] = 1;
				q.addFirst(new Point(ny, nx));
			} else
				break;
			// 몸통 그리기
			for ( int i = 0 ; i < q.size() ; i++ ) {
				Point now = q.pollFirst();
				map[now.y][now.x] = 1;
				q.addLast(now);
			}
			// 방향 전환
			if (idx < c_lst.size() && time == c_lst.get(idx).sec) {
				direction = change_d(direction, c_lst.get(idx).s);
				idx += 1;
			}
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < n; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("time : " + time);
			
		}
		
		return time;
	}

	// L 왼쪽 90도 , D 우 90도
	// 아래/위/우/좌
	static int change_d(int now, String s) {
		if (now == 0) {
			if (s.equals("L"))
				return 2;
			else
				return 3;
		} else if (now == 1) {
			if (s.equals("L"))
				return 3;
			else
				return 2;
		} else if (now == 2) {
			if (s.equals("L"))
				return 1;
			else
				return 0;
		} else {
			if (s.equals("L"))
				return 0;
			else
				return 1;
		}
	}

}