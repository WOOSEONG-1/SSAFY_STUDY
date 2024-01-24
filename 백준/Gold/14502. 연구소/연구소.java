import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/* 
 * 1. 아이디어
 *  (0을 가변배열에 넣는 완전탐색)->(0이 넣어진 좌표 3개씩 조합)->(조합된 좌표별 bfs 결과값 중 maxv 찾기)
 *  
 * 2. 시간복잡도	( N, M 1 ~ 8 ) 
 * 	3 <= N, M <= 8
 * 	64 C 3 * 64 * 5 = 13,000k   
 * 3. 자료 구조
 * 	
 */

public class Main {
	static class Point{
		int x, y;
		Point(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	static ArrayList<Point> ls = new ArrayList<>();
	static Queue<Point> q = new LinkedList<>();
	static Queue<ArrayList<Point>> oneq = new LinkedList<>();
	static boolean[][] brrr;
	static int[][] arr;
	static int maxv;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		arr = new int[n][m];		
		for ( int i = 0 ; i < n ; i++) {
			stk = new StringTokenizer(br.readLine());
			for ( int j = 0 ; j < m ; j++) {
				arr[i][j] = Integer.parseInt(stk.nextToken());
				if ( arr[i][j] == 0 ) {
					ls.add(new Point(i, j));
					}
				else if ( arr[i][j] == 2 ) {
					q.add(new Point(i, j));
					}
			}
		}
		boolean[] brr = new boolean [ls.size()];
		brrr = new boolean[n][m];
		comb(ls, brr, 0, ls.size(), 3);
		bfs(n,m);
		sb.append(maxv);
		System.out.print(sb);
	}
	
	static void comb(ArrayList<Point> ls, boolean[] brr, int k, int n, int r) {
		if( r == 0) {
			ArrayList<Point> temp = new ArrayList<>();
			for ( int i = 0 ; i < ls.size(); i++) {
				if(brr[i]) {
					temp.add(ls.get(i));
				}
			}
			oneq.add(temp);
			return;
		}
		
		for ( int i = k ; i < n ; i++) {
			if(brr[i] == false) {
				brr[i] = true;
				comb(ls, brr, k+1, n, r-1);
				brr[i] = false;
			}
		}
	}
		
	static void bfs(int n, int m ) {		
		int[] dy = {1, 0, -1, 0};
		int[] dx = {0, 1, 0, -1};
		while(!oneq.isEmpty()) {
			int[][] temp_arr = 	new int [n][m];
			for ( int i = 0 ; i < n ; i++) {
				for ( int j = 0 ; j < m ; j++) {
					temp_arr[i][j] = arr[i][j];
				}
			}
			ArrayList<Point> prr = oneq.poll();
			for ( Point i : prr ) {
				temp_arr[i.y][i.x] = 1;				
			}
			
			Queue<Point> tempq = q.stream()
				    .collect(Collectors.toCollection(LinkedList::new));
			for ( boolean[] a : brrr) {
				Arrays.fill(a, false);			
			}
			while(!tempq.isEmpty()) {
				Point temp = tempq.poll();
				if( brrr[temp.y][temp.x] == false ) {
					brrr[temp.y][temp.x] = true; 
					for ( int k = 0 ; k < 4 ; k++) {
						int ny = temp.y + dy[k];
						int nx = temp.x + dx[k];
						if ( ny >= 0 && ny < n && nx >= 0 && nx < m && temp_arr[ny][nx]==0) {
							temp_arr[ny][nx] = 2;
							tempq.add(new Point(ny,nx));							
						}
					}
				}
			}
			maxv = Math.max(check(temp_arr), maxv);
		}
	}
	static int check(int[][] temp_arr) {
		int cnt = 0;
		for ( int[] a : temp_arr) {
			for ( int b : a) {
				if(b == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}