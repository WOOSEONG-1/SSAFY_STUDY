import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    static int[] parent;
    static ArrayList<Integer> truth_arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        // 초기값 input
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        ArrayList<ArrayList<Integer>> lst = new ArrayList<>();

        // 초기화
        parent = new int[n+1];
        for ( int i = 1 ; i <= n ; i++ )
            parent[i] = i;

        // 진실을 아는 사람 리스트
        stk = new StringTokenizer(br.readLine());
        int truth = Integer.parseInt(stk.nextToken());
        truth_arr = new ArrayList<>(truth);
        for ( int i = 0 ; i < truth ; i++ )
            truth_arr.add(Integer.parseInt(stk.nextToken()));

        // 파티
        for ( int i = 0 ; i < m ; i++ ){
            stk = new StringTokenizer(br.readLine());
            lst.add(new ArrayList<>());
            int num = Integer.parseInt(stk.nextToken());
            for ( int j = 0 ; j < num ; j++ ){
                lst.get(i).add(Integer.parseInt(stk.nextToken()));
            }
        }
        br.close();

        // 각 파티 union
        int cnt = 0;
        for ( ArrayList<Integer> llst : lst ){
            int start = llst.get(0);
            for ( int i = 1 ; i < llst.size() ; i++ ){
                union(start, llst.get(i));
            }
        }

        // 진실 아는 사람이 섞인 파티 판별
        for ( ArrayList<Integer> llst : lst ){
            for ( int i : llst ){
                if ( truth_arr.contains(find(i)) ){
                    cnt++;
                    break;
                }
            }
        }

        // 전체 파티 수 - 진실을 아는 파티 수
        sb.append(m-cnt).append("\n");
        System.out.print(sb);
    }

    static int find(int a){
        if ( a == parent[a] )
            return a;
        return parent[a] = find(parent[a]);
    }
    
    // 진실을 아는 사람일 경우 그 사람의 unioin rank 최상위
    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if ( a == b )
            return;
        if ( truth_arr.contains(a) && truth_arr.contains(b)){
            if ( a > b )
                parent[a] = b;
            else
                parent[b] = a;
        }
        else if ( truth_arr.contains(a) )
            parent[b] = a;
        else if ( truth_arr.contains(b))
            parent[a] = b;
        else{
            if ( a > b )
                parent[a] = b;
            else
                parent[b] = a;
        }
    }
}

