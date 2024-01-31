import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * 1. 아이디어
 *  
 *  
 * 2. 시간복잡도	( N, M 1 ~ 8 ) 
 * 	
 * 	   
 * 3. 자료 구조
 * 	
 */

public class Main {
	static int mod;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		long num = Integer.parseInt(stk.nextToken());
		int power = Integer.parseInt(stk.nextToken());
		mod = Integer.parseInt(stk.nextToken());
		
		sb.append(pow_mod(num, power));		
		System.out.print(sb);
	}
	// 11 3
	static long pow_mod( long n, int exp ) {
		if (exp == 1)
			return n % mod;
		else {
			long res = pow_mod(n, exp/2);
			if ( exp % 2 == 0 ) {
				return res * res % mod;
			}
			else {
				return (res * res) % mod * n % mod;
			}
		}
	}
}