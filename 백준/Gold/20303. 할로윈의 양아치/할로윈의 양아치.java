import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static class child {
		int w = 1;
		int v;

		child(int v) {
			this.v = v;
		}

	}

	static int n, m, k;
	static int[] parent;
	static child[] candy;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		// n : 아이 수, m : 아이들의 친구 관계 수(간선), k : 배낭 크기
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		candy = new child[n + 1];
		parent = new int[n + 1];
		stk = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			int v = Integer.parseInt(stk.nextToken());
			candy[i] = new child(v);
		}
		// union find 초기화
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		// union find
		for (int i = 1; i <= m; i++) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			union(a, b);
		}

		sb.append(solve());
		System.out.print(sb);
	}

	static int solve() {

		int[] knap = new int[k];
		for (int i = 1; i <= n; i++) {
			int w = candy[i].w;
			int v = candy[i].v;
			for (int j = k-1; j >= w; j--) {
				knap[j] = Math.max(knap[j], knap[j - w] + v);

			}

		}

		return knap[k - 1];
	}

	static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b)
			return;
		int root = 0;
		int son = 0;
		if (a > b) {
			parent[a] = b;
			son = a;
			root = b;
		} else {
			parent[b] = a;
			son = b;
			root = a;
		}
		candy[root].w += candy[son].w;
		candy[root].v += candy[son].v;
		candy[son].w = 0;
		candy[son].v = 0;
	}
}