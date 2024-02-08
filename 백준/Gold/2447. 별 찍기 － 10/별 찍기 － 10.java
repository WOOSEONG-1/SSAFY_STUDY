import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 *   - 분할 정복
 * 2. 시간 복잡도
 *   - O(N^2)
 * 3. 자료구조
 *   - 배열
 */

public class Main {
    static boolean[][] B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int n = Integer.parseInt(br.readLine());
        B = new boolean[n][n];
        star(n, 0, 0);
        for (boolean[] brr : B){
            for ( boolean b : brr ){
                if ( b )
                    sb.append("*");
                else
                    sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    static void star ( int n, int y, int x){
        if ( n == 3 ){
            for ( int i = y ; i < y + n ; i++ ){
                for ( int j = x ; j < x + n ; j++ ){
                    if ( i == y+1 && j == x+1 )
                        continue;
                    else
                        B[i][j] = true;
                }
            }
        }
        else{
            for ( int i = y ; i < y + n ; i = i + n/3 ){
                for ( int j = x ; j < x + n ; j = j + n/3 ){
                    if ( i == y+n/3 && j == x+n/3 )
                        continue;
                    else
                        star(n/3, i, j);
                }
            }
        }
    }
}