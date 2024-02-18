import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

/*
 * 1. 아이디어
 *  - 그래프 탐색 + 구현
 *  - 인접 리스트로 그래프 저장 -> 조합으로 선거구 선별 -> 분단 여부 판별 ( visited = true )
 * 2. 시간 복잡도
 *	- ( 1C10 + .. 10C10 )
 *
 * 3. 자료구조
 *
 */
public class Main {
    static ArrayList<ArrayList<Integer>> lst = new ArrayList<>();   // 인접 리스트
    static int n, minv = Integer.MAX_VALUE;       // 노드 수
    static int[] A;     // 인구 정보
    static boolean[] visit;     // 조합 방문 판별
    static boolean[] node_v;    // 노드 방문 판별

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            lst.add(new ArrayList<>());
        }
        A = new int[n + 1];
        visit = new boolean[n + 1];
        node_v = new boolean[n + 1];
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = Integer.parseInt(stk.nextToken());
        }
        for (int i = 1; i <= n; i++) {
            stk = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(stk.nextToken());
            for (int j = 0; j < m; j++) {
                lst.get(i).add(Integer.parseInt(stk.nextToken()));
            }
        }
        for (int i = 1; i <= n-1; i++) {
            comb(i, 1);
        }
        if (minv == Integer.MAX_VALUE)
            sb.append(-1);
        else
            sb.append(minv);
        System.out.print(sb);
    }

    // 조합
    static void comb(int r, int k) {
        // 조합 결과
        if (r == 0) {
            int[] temp = new int[n + 1];
            int sumv = 0;   // 방문할 노드를 제외한 인구 합
            for (int i = 1; i <= n; i++) {
                if (visit[i])
                    temp[i] = i;
                else
                    sumv += A[i];
            }
            if (solve(temp)) {
                for (int j : temp){
                    sumv -= A[j];
                }
                sumv = Math.abs(sumv);
                minv = Math.min(sumv, minv);
            }
            Arrays.fill(node_v, false);
            return;
        }
        // 조합
        for (int i = k; i <= n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                comb(r - 1, k + 1);
                visit[i] = false;
            }
        }
    }

    // 그래프 순회 및 결과 갱신
    static boolean solve(int[] temp) {
        Deque<Integer> q = new ArrayDeque<>();
        Deque<Integer> qq = new ArrayDeque<>();
        // 큐에 방문할 노드 추가
        for (int i = 1 ; i <= n ; i++ ) {
            if (!q.isEmpty() && !qq.isEmpty())
                break;
            else if ( q.isEmpty() && temp[i] != 0) {
                q.add(i);
                node_v[i] = true;
            }
            else if( qq.isEmpty() && temp[i] == 0 ){
                qq.add(i);
            }
        }
        // 정점 하나를 기준으로 순회
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int next : lst.get(now)) {
                if (!node_v[next] && temp[next] != 0 ) {
                    q.add(next);
                    node_v[next] = true;
                }
            }
        }
        // 방문할 노드가 false면 연결안 된 선거구
        for (int i : temp) {
            if ( i != 0 && !node_v[i] )
                return false;
        }
        // 단절 여부 판단
//        Arrays.fill(node_v, false);
        node_v[qq.peekLast()] = true;
        while (!qq.isEmpty()) {
            int now = qq.poll();
            for (int next : lst.get(now)) {
                if (!node_v[next]) {
                    qq.add(next);
                    node_v[next] = true;
                }
            }
        }
        for (int i = 1 ; i <= n ; i++) {
            if ( !node_v[i] )
                return false;
        }
        return true;
    }

}