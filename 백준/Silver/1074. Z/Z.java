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
		static int div = 1000000000;
		public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(stk.nextToken());
		int r = Integer.parseInt(stk.nextToken());
		int c = Integer.parseInt(stk.nextToken());
		
		sb.append(solved(n,r,c,0));
		System.out.print(sb);
	}
		
	static int solved(int n, int r, int c, int k) {
		int temp_k = (int)Math.pow(Math.pow(2, n-1), 2);
		int temp_rc = (int)Math.pow(2, n-1);
		if ( n == 0 )
			return k;
		else {
			if( r < temp_rc && c < temp_rc ) {
				return solved( n-1, r, c, k + temp_k*0);
			}
			else if( r < temp_rc && c >= temp_rc ) {
				return solved( n-1, r, c - temp_rc, k + temp_k*1 );
			}
			else if( r >= temp_rc && c < temp_rc ) {
				return solved( n-1, r - temp_rc , c, k + temp_k*2 );				
			}
			else {
				return solved( n-1, r-temp_rc, c-temp_rc, k + temp_k*3 );				
			}		
			
		}			
	}
	
}