import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(br.readLine());
        parent = new int[n+1];
        for ( int i = 1 ; i <= n ; i++ ) {
            parent[i] = i;
        }

        String line;
        while ((line=br.readLine()) != null ){
            StringTokenizer stk = new StringTokenizer(line);
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            union(a, b);
        }
        HashSet<Integer> set = new HashSet<>();
        for ( int i = 1 ; i <= n ; i++ ) {
            int b = find(parent[i]);
            set.add(b);
        }
        // 출력
        for ( int i : set )
            sb.append(i).append(" ");
        System.out.print(sb);
    }

    static int find(int a){
        if ( parent[a] == a )
            return a;
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if ( a == b )
            return;
        if ( a > b )
            parent[a] = b;
        else
            parent[b] = a;
    }
}