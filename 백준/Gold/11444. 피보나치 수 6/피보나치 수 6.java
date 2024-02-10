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
 *  -
 * 2. 시간 복잡도
 *  - n 1 ~ 1,000,000,000,000,000,000
 *  -
 * 3. 자료구조
 *   - 배열, 우선순위 큐
 */
//1000000000000000000
public class Main {
    static int div = 1000000007;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        long n = Long.parseLong(br.readLine());
        long[][] A = {{1, 1},{1,0}};
        A = fibo(n, A);

        sb.append(A[0][1]);
        System.out.print(sb);
    }
    static long[][] fibo(long n, long[][] A){
        if ( n == 1 )
            return A;
        long temp[][] = fibo(n/2, A);
        temp = mul_mat(temp, temp);
        if( n%2 == 1)
            temp = mul_mat(temp, A);

        return temp;
    }
    static long[][] mul_mat ( long[][] A, long[][] B ){
        long[][] C = new long[2][2];
        for ( int i = 0 ; i < 2 ; i++){
            for ( int j = 0 ; j < 2 ; j++ ){
                for ( int k = 0 ; k < 2; k++ ){
                    C[i][j] += A[i][k]%div * B[k][j]%div;
                    C[i][j] %= div;
                }
            }
        }
        return C;
    }
}