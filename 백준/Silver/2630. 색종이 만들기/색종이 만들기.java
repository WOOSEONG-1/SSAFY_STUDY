import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/* 
 * 1. 아이디어
 *  바이러스의 위치를 큐에 대입 -> 조합 -> 퍼트리기 -> 최소 시간 갱신
 *  
 * 2. 시간복잡도	( N, M 1 ~ 8 ) 
 * 	N 5 ~ 50 , M 1 ~ 10
 * 	64 C 3 * 64 * 5 = 13,000k   
 * 3. 자료 구조
 * 	
 */

public class Main {
	static int blu = 0;
	static int wht = 0;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(stk.nextToken());
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		if (!chk(n, 0, 0)) 
			solved(n, 0, 0);
		sb.append(wht).append("\n").append(blu);
		System.out.print(sb);
	}

	static void solved(int n, int y, int x) {
		if ( chk(n, y, x))
			return;
		
		int temp_n = n / 2;
		for (int i = y; i < y + n; i += temp_n) {
			for (int j = x; j < x + n; j += temp_n) {
				solved(temp_n, i, j);
			}
		}
	}
	
	static boolean chk(int n, int y, int x) {
		int blue = 0;
		int white = 0;
		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n ; j++) {
				if (arr[i][j] == 0)
					white++;
				else
					blue++;
			}
		}
		if (blue == n * n) {
			blu++;
			return true;
		} else if (white == n * n) {
			wht++;
			return true;
		} else
			return false;
	}

}