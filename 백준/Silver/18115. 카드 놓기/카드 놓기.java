import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class Main {
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        Deque<Integer> q = new LinkedList<>();
        int n = Integer.parseInt(br.readLine());
        cnt = n;
        for ( int i = 0 ; i < n ; i++ ){
            q.addLast(i);
        }
        int[] arr = new int[n];
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for ( int i = 0 ; i < n ; i++ ){
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        int[] ans = new int[n];
        for ( int i : arr ){
            if ( i == 1 )
                s1(q, ans);
            else if ( i == 2)
                s2(q, ans);
            else
                s3(q, ans);
        }
        for ( int i : ans )
            sb.append(i + " ");
        System.out.print(sb);
        br.close();
    }
    static void s1(Deque<Integer> q, int[] a) {
        a[q.pollFirst()] = cnt--;
    }
    static void s2(Deque<Integer> q, int[] a) {
        int temp = q.pollFirst();
        a[q.pollFirst()] = cnt--;
        q.addFirst(temp);
    }
    static void s3(Deque<Integer> q, int[] a) {
        a[q.pollLast()] = cnt--;
    }
}