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
 * 	- 비트 마스킹 부분 집합 완전 탐색
 *  - Food 클래스 생성 
 * 2. 시간 복잡도
 * 	- 2^10 * 2 = 2048
 * 3. 자료 구조
 * 	- 배열 ( 탐색 : O(N), 삽입:O(1), 삭제 : O(N), 검색 : O(1) ) 
 */

public class Main {
	static class Food{
		int b, s;
		Food(int s, int b){
			this.b = b;
			this.s = s;
		}		
	}
	static int minv = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(stk.nextToken());
		Food[] arr = new Food[n];
		for ( int i = 0 ; i < n ; i++ ) {
			stk = new StringTokenizer(br.readLine());
			arr[i] = new Food(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));			
		}
		for ( int i = 1 ; i < 1<<n ; i++) {
			int temp_s = 1;
			int temp_b = 0;
			for ( int j = 0 ; j < n ; j++ ) {
				if ( (i & 1<<j) == 1<<j ) {
					temp_s *= arr[j].s;
					temp_b += arr[j].b;
				}
			}
			minv = Math.min(Math.abs(temp_s - temp_b), minv);
		}
		sb.append(minv);
		System.out.print(sb);;
	}
}