import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static StringBuffer sb;
	static int temp[];	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		int arr[] = new int[n];
		boolean brr[] = new boolean[n];
		temp = new int[m];
		stk = new StringTokenizer(br.readLine());
		for ( int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(stk.nextToken());			
		}
		Arrays.sort(arr);
		back(0,arr,brr);
		System.out.print(sb);
	}
	
	static void back(int depth, int[] arr, boolean[] brr) {
		if ( depth == m ) {
			
			for ( int i = 0 ; i < temp.length ; i++ ) {
				sb.append(temp[i]).append(" "); 
			}
			sb.append("\n");
			return;
		}
		
		for ( int i = 0 ; i < arr.length ; i++) {
			if( brr[i]== false) {
				temp[depth] = arr[i];
				brr[i]=true;
				back(depth+1,arr,brr);
				brr[i]=false;
			}			
		}		
	}
}