import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 *  - 분할 정복
 * 2. 시간 복잡도
 *  -
 * 3. 자료구조
 *  -
 */
//1000000000000000000
public class Main {
    static int ny, nx, cnt;
    static class Point {
        int y, x;
        Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int find = Integer.parseInt(stk.nextToken());
        ny = 0;
        nx = 0;
        cnt = 0;
        Point ans = hilbert(N, find);
        sb.append(ans.x).append(" ").append(ans.y);
        System.out.print(sb);
    }

    static Point hilbert( int N, int find ){
        if ( N == 2 ){
            if (find == 1){
                return new Point(1, 1);
            }
            else if (find == 2)
                return new Point(2, 1);
            else if (find == 3)
                return new Point(2, 2);
            else
                return new Point(1, 2);
        }
        else{
            int half = N/2;
            int check = half * half;
            if ( find <= check ){
                Point p = hilbert( half , find );
                return new Point(p.x, p.y);
            }
            else if ( find <= 2*check ){
                Point p = hilbert( half, find - check);
                return new Point( p.y+half, p.x );
            }
            else if ( find <= 3*check ){
                Point p = hilbert( half, find - check*2);
                return new Point (p.y+half, p.x + half);
            }
            else{
                Point p = hilbert( half, find - check*3);
                return new Point(half-p.x+1 , 2*half-p.y+1);
            }
        }
    }

}