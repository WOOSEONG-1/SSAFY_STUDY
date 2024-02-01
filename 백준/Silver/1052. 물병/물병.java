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
 * 	- 2진수 값 n에 1을 더한 횟수 = answer
 *  - 
 * 2. 시간 복잡도
 * 	- 2^30 * 2
 * 3. 자료 구조
 * 	- 배열 ( 탐색 : O(N), 삽입:O(1), 삭제 : O(N), 검색 : O(1) ) 
 */

public class Main {
	
	static int minv = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(stk.nextToken());
		int m = Integer.parseInt(stk.nextToken());
		for ( int i = 0 ; ; i++) {
			
			if( Integer.bitCount(n+i) <= m ) {
				sb.append(i);
				break;
			}
		}
		System.out.print(sb);
	}
	static String binary(int n) {
		String t = Integer.toBinaryString(n);
		return t;
	}
}