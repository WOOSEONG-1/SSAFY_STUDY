import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* 포도주 문제 
 * 1. 아이디어
 * 배열 내 1을 완전 탐색 -> 탐색한 1을 큐에 삽입 -> BFS가 완료되면 count +1   
 * 
 * 2. 시간복잡도	( 배열 크기 n x n )
 * BFS -> O(n) = V+E = 5V =  2500 * 50 = 12.5만
 * 
 * 3. 자료 구조
 * 배열
 */

public class Main {
	
	static class Point {
		int x;
		int y;
		Point(int x, int y){
			this.y = y;
			this.x = x;
		}
	} 
	static Queue<Point> q = new LinkedList<>();
	static int count, N, M;
	static int[][] arr;
	static boolean[][] brr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(br.readLine());				
		for ( int i = 0 ; i < T ; i++ ) {			
			StringTokenizer stk = new StringTokenizer(br.readLine());			
			M = Integer.parseInt(stk.nextToken()); 	// 가로 
			N = Integer.parseInt(stk.nextToken());	// 세로
			int K = Integer.parseInt(stk.nextToken());	// 배추 개수
			arr = new int[N][M];
			brr = new boolean[N][M];
			count = 0;
			for ( int j = 0 ; j < K ; j++) {
				stk = new StringTokenizer(br.readLine());
				q.add(new Point( Integer.parseInt(stk.nextToken()) , Integer.parseInt(stk.nextToken()) ));
				arr[q.peek().y][q.poll().x] = 1;
			}
			q.clear();
			for ( int y = 0 ; y < N ; y++) {
				for ( int x = 0 ; x < M ; x++) {
					if (arr[y][x] == 1 && brr[y][x] == false ) {
						brr[y][x]=true;
						q.add(new Point(y, x));
						bfs(y, x);
						count++;
					}
				}
			}
			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}
	
	static void bfs(int y, int x) {
		int[] dy = {1, 0, -1, 0};
		int[] dx = {0, 1, 0, -1};

		while(!q.isEmpty()) {
			Point temp = q.poll();			
			for ( int i = 0 ; i < 4 ; i ++) {
				int ny = y - dy[i]; 
				int nx = x - dx[i];
				if( ny >= 0 && ny < N && nx >= 0 && nx < M ) {
					if (arr[ny][nx] == 1 && brr[ny][nx] == false) {
						brr[ny][nx] = true;
						q.add(new Point(ny, nx));
						bfs(ny, nx);
					}
				}
			}
		}
	}
	
}