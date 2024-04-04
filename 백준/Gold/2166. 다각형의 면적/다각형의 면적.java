import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 아이디어 
 * 	- 수학, 신발끈 공식
 * 2. 시간 복잡도
 * 	-  
 * */

public class Main {
	static class Node{
		int x, y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int n = Integer.parseInt(br.readLine());
		Node[] arr = new Node[n+1];
		for ( int i = 0 ; i < n ; i++ ) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(stk.nextToken());
			int y = Integer.parseInt(stk.nextToken());
			arr[i] = new Node(x, y);
		}
		arr[n] = arr[0];
		double ans = 0;
		for ( int i = 0 ; i < n ; i++ ) {
			ans += ((long)arr[i].x * arr[i+1].y) - ((long)arr[i].y * arr[i+1].x) ;
		}
		ans = Math.abs(ans);
		ans /= 2;
		sb.append(String.format("%.1f", ans));
		System.out.print(sb);
	}
}