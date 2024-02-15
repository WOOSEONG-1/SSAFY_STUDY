import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

/*
 * 1. 아이디어
 * 	- 최대 5번 이동해서 가장 큰 값 -> 백트래킹 깊이 : 5
 * 2. 시간 복잡도
 *	- O(n) = 20*20 * 1024 * 4 <= 10^8
 * 3. 자료구조
 * 	- 배열
 * */
public class Main {
	static int n, maxv;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		maxv = 0;
		StringTokenizer stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		int[][] map = new int[n + 2][n + 2];
		for (int i = 1; i <= n; i++) {
			stk = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		back(0, map);		
		sb.append(maxv);
		System.out.print(sb);
	}

	static void back(int k, int[][] M) {

		if ( k == 5) {
			int sumv = find_max(M);
			maxv = Math.max(maxv, sumv);
			return;
		}
		// 0 left 1 right 2 up 3 down
		for (int i = 0; i < 4; i++) {
			// left
			if (i == 0) {
				back(k + 1, left(M));
			}
			// right
			else if (i == 1) {
				back(k + 1, right(M));
			}
			// up
			else if (i == 2) {
				back(k + 1, up(M));
			}
			// down
			else if (i == 3) {
				back(k + 1, down(M));
			}
		}
	}

	static int[][] left(int[][] M) {
		int[][] temp = new int[n + 2][n + 2];
		for (int j = 1; j <= n; j++) {
			int idx = 1;
			for (int a = 1; a <= n; a++) {
				boolean flag = true;
				if (M[j][a] != 0) {
					if (M[j][a + 1] == 0 && a != n) {
						for (int b = a + 2; b <= n; b++) {
							if (M[j][b] != 0) {
								if (M[j][a] == M[j][b]) {
									temp[j][idx++] = M[j][a] + M[j][b];
									flag = false;
									a = b;
									break;
								} else {
									temp[j][idx++] = M[j][a];
									flag = false;
									break;
								}
							}
						}
						if (flag) {
							temp[j][idx++] = M[j][a];
						}
					} else if (M[j][a] == M[j][a + 1]) {
						temp[j][idx++] = M[j][a] + M[j][a + 1];
						a += 1;
					} else {
						temp[j][idx++] = M[j][a];
					}
				}
			}
			for (int b = idx; b <= n; b++) {
				temp[j][b] = 0;
			}
		}
		return temp;
	}

	static int[][] right(int[][] M) {
		int[][] temp = new int[n + 2][n + 2];
		for (int j = 1; j <= n; j++) {
			int idx = n;
			for (int a = n; a > 0; a--) {
				boolean flag = true;
				if (M[j][a] != 0) {
					if (M[j][a - 1] == 0 && a != 1) {
						for (int b = a - 2; b >= 1; b--) {
							if (M[j][b] != 0) {
								if (M[j][a] == M[j][b]) {
									temp[j][idx--] = M[j][a] + M[j][b];
									flag = false;
									a = b;
									break;
								} else {
									temp[j][idx--] = M[j][a];
									flag = false;
									break;
								}
							}
						}
						if (flag)
							temp[j][idx--] = M[j][a];
					} else if (M[j][a] == M[j][a - 1]) {
						temp[j][idx--] = M[j][a] + M[j][a - 1];
						a -= 1;
					} else {
						temp[j][idx--] = M[j][a];
					}
				}
			}
			for (int b = idx; b > 0; b--) {
				temp[j][b] = 0;
			}
		}
		return temp;
	}

	// up
	static int[][] up(int[][] M) {
		int[][] temp = new int[n + 2][n + 2];
		for (int j = 1; j <= n; j++) {
			int idx = 1;
			for (int a = 1; a <= n; a++) {
				boolean flag = true;
				if (M[a][j] != 0) {
					if (M[a + 1][j] == 0 && a != n) {
						for (int b = a + 2; b <= n; b++) {
							if (M[b][j] != 0) {
								if (M[a][j] == M[b][j]) {
									temp[idx++][j] = M[a][j] + M[b][j];
									flag = false;
									a = b;
									break;
								} else {
									temp[idx++][j] = M[a][j];
									flag = false;
									break;
								}
							}
						}
						if (flag)
							temp[idx++][j] = M[a][j];
					} else if (M[a][j] == M[a + 1][j]) {
						temp[idx++][j] = M[a][j] + M[a + 1][j];
						a += 1;
					} else {
						temp[idx++][j] = M[a][j];
					}
				}
			}
			for (int b = idx; b <= n; b++) {
				temp[b][j] = 0;
			}
		}
		return temp;
	}

	// down
	static int[][] down(int[][] M) {
		int[][] temp = new int[n + 2][n + 2];
		for (int j = 1; j <= n; j++) {
			int idx = n;
			for (int a = n; a >= 1; a--) {
				boolean flag = true;
				if (M[a][j] != 0) {
					if (M[a - 1][j] == 0 && a != 1) {
						for (int b = a - 2; b >= 1; b--) {
							if (M[b][j] != 0) {
								if (M[b][j] == M[a][j]) {
									temp[idx--][j] = M[a][j] + M[b][j];
									flag = false;
									a = b;
									break;
								} else {
									temp[idx--][j] = M[a][j];
									flag = false;
									break;
								}
							}
						}
						if (flag)
							temp[idx--][j] = M[a][j];
					} else if (M[a][j] == M[a - 1][j]) {
						temp[idx--][j] = M[a][j] + M[a - 1][j];
						a -= 1;
					} else {
						temp[idx--][j] = M[a][j];
					}
				}
			}
			for (int b = idx; b >= 1; b--) {
				temp[b][j] = 0;
			}
		}
		return temp;
	}

	static int find_max(int[][] M) {
		int v = 0;
		int cnt = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				v = Math.max(v, M[i][j]);
			}
		}
		return v;
	}

}