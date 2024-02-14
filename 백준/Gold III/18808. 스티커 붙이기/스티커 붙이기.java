import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

/*
 * 1. 아이디어
 * 
 * 2. 시간 복잡도
 *	- N, M 1 ~ 8 / 8
 *	
 * 3. 자료구조
 * 
 * */
public class Main {
	static class Img {
		int h, v;
		int[][] map;

		Img(int h, int v, int[][] map) {
			this.h = h;
			this.v = v;
			this.map = map;
		}
	}

	static ArrayList<Img> st_lst = new ArrayList<>();
	static int[][] map;
	static int n, m, k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		map = new int[n][m];
		for (int i = 0; i < k; i++) {
			stk = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(stk.nextToken());
			int h = Integer.parseInt(stk.nextToken());
			int[][] stc = new int[v][h];
			for (int a = 0; a < v; a++) {
				stk = new StringTokenizer(br.readLine());
				for (int b = 0; b < h; b++) {
					stc[a][b] = Integer.parseInt(stk.nextToken());
				}
			}
			st_lst.add(new Img(h, v, stc));
		}
		for (Img img : st_lst) {
			draw(img);
		}
		int cnt = 0;
		for (int[] i : map) {
			for (int j : i) {
				if (j == 1) {
					cnt++;
				}
			}
		}
		sb.append(cnt);
		System.out.print(sb);
	}

	static void draw(Img img) {
		for (int r = 0; r < 4; r++) {
			for (int i = 0; i < n - img.v + 1; i++) {
				for (int j = 0; j < m - img.h + 1; j++) {
					if (check(img, i, j)) {
						for (int a = i; a < i + img.v; a++) {
							for (int b = j; b < j + img.h; b++) {
								if (img.map[a-i][b-j] == 1)
									map[a][b] = img.map[a-i][b-j];
							}
						}
						return;
					}
				}
			}
			img = rotate(img);
			
			
		}
	}

	static Img rotate(Img img) {
		int[][] res = new int[img.h][img.v];
		for (int i = 0; i < img.v; i++) {
			for (int j = 0; j < img.h; j++) {
				res[j][img.v-1-i] = img.map[i][j];
			}
		}
		return new Img(img.v, img.h, res);
	}

	static boolean check(Img img, int ver, int hor) {
		for (int i = ver; i < ver + img.v; i++) {
			for (int j = hor; j < hor + img.h; j++) {
				if (map[i][j] == 1 && img.map[i - ver][j - hor] == 1)
					return false;
			}
		}

		return true;
	}

}