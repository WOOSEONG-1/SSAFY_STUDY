import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Arrays;

/*
 * 1. 아이디어
 *  - 분할 정복
 *  - f(0) + f(1) =
 * 2. 시간 복잡도
 *  - n 1 ~ 1,000,000,000,000,000,000
 *  -
 * 3. 자료구조
 *   - 배열, 우선순위 큐
 */
//1000000000000000000
public class Main {
//    static String div = "1000000007";
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        long exp = Long.parseLong(stk.nextToken());
        long[][] arr = new long[N+1][N+1];
        for ( int i = 1 ; i <= N ; i++){
            stk = new StringTokenizer(br.readLine());
            for ( int j = 1 ; j <= N ; j++){
                arr[i][j] = Long.parseLong(stk.nextToken())%1000;
            }
        }
        arr = solve(exp, arr);
        for ( int i = 1 ; i <= N ; i++ ){
            for ( int j =1 ; j <= N ; j++ ){
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    static long[][] mul_mat(long[][] A, long[][] B){
        long[][] C = new long[N+1][N+1];
        for ( int i = 1 ; i <= N ; i++ ){
            for ( int j = 1 ; j <= N; j++ ){
                for ( int k = 1 ; k <= N ; k++){
                    C[i][j] += A[i][k] * B[k][j];
                }
                C[i][j] %= 1000;
            }
        }
        return C;
    }
    static long[][] solve(long exp, long[][] A){
        if ( exp == 1 )
            return A;
        long[][] temp = solve(exp/2, A);
        temp = mul_mat(temp, temp);
        if ( exp%2 == 1 )
            temp = mul_mat(temp, A);

        return temp;
    }
}