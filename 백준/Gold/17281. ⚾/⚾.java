import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

/*	10:10 ~ 
 * 1. 아이디어
 * 	- 순열 이용 완전 탐색
 * 2. 시간 복잡도
 *	- 순열 : 8! * a
 * 3. 자료구조 :
 * 	- 배열
 * */
public class Main {
	static boolean[] brr;
	static int n, maxv = -1;
	static int[][] batter;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		n = Integer.parseInt(br.readLine());

		batter = new int[n][9];
		for (int i = 0; i < n; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				batter[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		brr = new boolean[9];
		brr[0] = true;
		int[] temp = new int[9];
		temp[0] = 0;
		perm(temp, 1, 9);
		sb.append(maxv);
		System.out.print(sb);
	}
	// temp = [ 0, 1, 2, 3, 4, 5, 6, 7, 8 ]
	// 순열 ( arr : 타순 )
	static void perm(int[] arr, int k, int r) {
		if (r == k) {
			// 재배열
			int[] temp = arr.clone();
			temp[0] = temp[3];
			temp[3] = 0;
			game(temp);
			return;
		}
		
		for (int i = 1 ; i < 9 ; i++) {
			if (!brr[i] && i != 0) {
				brr[i] = true;
				arr[k] = i;
				perm(arr, k + 1, r);
				brr[i] = false;
			}
		}
	}

	static void game(int[] arr) {
		// 타순 재배열
		// cnt : 타순 ( 0 ~ 8 )
		int score = 0;
		int inning = 0;
		int cnt = 0;
		// out : 아웃, go : 진루 ( 0: 1루 ~ )
		while (inning != n) {
			int out = 0;
			int go = 0;
			while (out != 3) {				
				int bat = batter[inning][arr[cnt]];
 				int check = Integer.bitCount(go);
				// 0 이면 아웃
				if (bat == 0)
					out += 1;
				// 1~3이면 안타 , 4면 홈런
				else {
					// 1루타
					if (bat == 1) {
						if (check == 0)
							go = 1;
						else {
							go = (go << 1) + 1;
						}
					}
					// 2루타
					else if (bat == 2) {
						if (check == 0)
							go = 2;
						else {
							go = (go << 2) + 2;
						}
					}
					// 3루타
					else if (bat == 3) {
						if (check == 0)
							go = 4;
						else {
							go = (go << 3) + 4;							
						}
					}
					// 홈런
					else if (bat == 4) {
						if (check == 0)
							go = 8;
						else
							go = (go<<4) + 8; 
					}
					if ((go >> 3) >= 1) {
						int res = Integer.bitCount(go >> 3);
						score += res;
						go &= ~(0b1111000);
					}
				}
				cnt = (cnt+1)%9;
			}
			inning += 1;
		}

		maxv = Math.max(maxv, score);
		return;
	}

}