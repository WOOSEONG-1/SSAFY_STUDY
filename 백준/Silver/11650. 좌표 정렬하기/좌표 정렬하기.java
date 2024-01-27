import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

public class Main{
    static class Point implements Comparable<Point> {
        int x, y;
        Point ( int y, int x){
            this.y = y;
            this.x = x;
        }
        @Override
        public String toString(){
            return this.x + " " + this.y ;
        }
        @Override
        public int compareTo(Point other){
            if ( this.x > other.x )
                return 1;
            else if ( this.x == other.x ){
                if( this.y > other.y )
                    return 1;
                else if ( this.y < other.y )
                    return -1;
                else
                    return 0;
            }
            else
                return -1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk;
        int n = Integer.parseInt(br.readLine());
        Point[] arr = new Point[n];
        for ( int i = 0 ; i < n ; i++ ){
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            arr[i] = new Point(y, x);
        }
        Arrays.sort(arr);
        for ( Point p : arr){
            sb.append(p).append("\n");
        }
        System.out.print(sb);
    }
}
