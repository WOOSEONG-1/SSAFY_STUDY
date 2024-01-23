import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Graph{
		int y;
		int x;
		Graph(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	static int n, m, minv;
	static int[] temp;
	static ArrayList<Graph> c_lst;
	static ArrayList<Graph> lst;
	static int[][] dist ;
	 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());

		n = Integer.parseInt(stk.nextToken());	
		m = Integer.parseInt(stk.nextToken());
		int[][] arr = new int [n][n];
		temp = new int[m];
		lst = new ArrayList<>();
		c_lst = new ArrayList<>();
		minv = 12500;
		for (int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			for ( int j = 0 ; j < n ; j++ ) {
				arr[i][j] = Integer.parseInt(stk.nextToken());
				if ( arr[i][j] == 2)
					lst.add(new Graph(i,j));
				if ( arr[i][j] == 1)
					c_lst.add(new Graph(i,j));
			}
		}
		br.close();
		dist = new int [c_lst.size()][lst.size()];
		for ( int i = 0 ; i < c_lst.size() ; i++) {
			for ( int j = 0 ; j < lst.size() ; j++ ) {
				dist[i][j] = (Math.abs(c_lst.get(i).y-lst.get(j).y) 
						+ Math.abs(c_lst.get(i).x-lst.get(j).x));				
			}
		}
		boolean[] brr = new boolean [lst.size()];		
		back(0, arr, brr, lst, m);
		sb.append(minv);
		System.out.print(sb);
	}
	static void back( int depth, int[][] arr, boolean[] brr, ArrayList<Graph> lst, int r ) {
		if ( r == 0 ) {
			int k = 0;
			for ( int i = 0 ; i < lst.size() ; i++) {
				if (brr[i]) 
					temp[k++] = i;
			}
			chk_cal(temp);
			return;
		}		
		for ( int i = depth ; i < lst.size() ; i++) {
			brr[i] = true;
			back(i+1, arr, brr, lst, r-1);
			brr[i] = false;
		}
	}
	
	static void chk_cal (int[] temp) {
		int res = 0 ;
		int arr[] = new int[c_lst.size()];
		Arrays.fill(arr,50);
		for ( int i = 0 ; i < c_lst.size() ; i++) {
			for ( int j : temp) {
				arr[i] = Math.min(dist[i][j], arr[i]);
			}
		}
		for ( int i = 0 ; i < c_lst.size(); i++)
			res += arr[i];
		
		if( minv > res )
			minv = res;			
	}
	
}