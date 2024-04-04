import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;
import java.io.IOException;

/*
 * 1. 조건
 *   - 토네이도 이동 (반시계) -> 모래 분배 -> 밖으로 나간거만 추산
 *   - 시작 위치 : (n/2, n/2), 이동 방식 : 2번 이동마다 이동 값(k += 1), d += 1
 *   - 모래 분배 -> 이동 방향마다 모래 분배 방식을 따로 설정
 *
 *
 * */
public class Main {
    static class Point {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int n, d, k = 1;
    static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        // 초기화
        n = Integer.parseInt(br.readLine());
        res = 0;
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        tornado(map, n / 2, n / 2);
        sb.append(res);
        System.out.print(sb);
    }
    // 델타 배열
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};
    // y, x : 현재 토네이도의 위치
    static void tornado(int[][] map, int y, int x) {
        int ny = y;
        int nx = x;
        // 좌표 밖으로 토네이도가 나가면 종료
        while (true) {

            for (int i = 1; i <= k; i++) {
                ny += dy[d % 4];
                nx += dx[d % 4];
                if (ny >= 0 && nx >= 0 && ny < n && nx < n ){
                    if ( map[ny][nx] != 0)
                        sand(map, ny , nx, d%4);
                }
                else
                    return;
            }
            // 방향, 속력 갱신
            d += 1;
            if (d % 2 == 0)
                k += 1;
        }
    }
    // dy, dx, 뿌릴 모래 비율
    static int[][] sand_move = {    {-1, 1, 1}, {1, 1, 1},
                                    { 1, 0, 7 }, { -1, 0, 7 }, { 2, 0, 2 }, { -2, 0, 2 },
                                    { 1, -1, 10 }, { -1, -1, 10 },
                                    { 0, -2, 5 }                                          };
    // 모래 분배
    static void sand(int[][]map, int y, int x, int d) {
        int amount = map[y][x];
        // 좌
        if (d == 0) {
            // 분배
            for ( int i = 0 ; i < 9 ; i++ ){
                int[] move_data = sand_move[i];
                int ny = y + move_data[0];
                int nx = x + move_data[1];
                // m : 옮길 모래의 양
                int m = (int) (map[y][x]*move_data[2]*0.01);
                amount -= m;
                if ( ny >= 0 && nx >= 0 && ny < n && nx < n )
                    map[ny][nx] += m;
                else
                    res += m;
            }
        }
        // 우
        else if (d == 2) {
            // 분배
            for ( int i = 0 ; i < 9 ; i++ ){
                int[] move_data = sand_move[i];
                int ny = y + move_data[0];
                int nx = x - move_data[1];
                int m = (int) (map[y][x]*move_data[2]*0.01);
                amount -= m;
                if ( ny >= 0 && nx >= 0 && ny < n && nx < n )
                    map[ny][nx] += m;
                else
                    res += m;
            }
        }
        // 상
        else if (d == 3) {
            // 분배
            for ( int i = 0 ; i < 9 ; i++ ){
                int[] move_data = sand_move[i];
                int ny = y + move_data[1];
                int nx = x + move_data[0];
                int m = (int) (map[y][x]*move_data[2]*0.01);
                amount -= m;
                if ( ny >= 0 && nx >= 0 && ny < n && nx < n )
                    map[ny][nx] += m;
                else
                    res += m;
            }
        }
        // 하
        else {
            // 분배
            for ( int i = 0 ; i < 9 ; i++ ){
                int[] move_data = sand_move[i];
                int ny = y - move_data[1];
                int nx = x + move_data[0];
                int m = (int) (map[y][x]*move_data[2]*0.01);
                amount -= m;
                if ( ny >= 0 && nx >= 0 && ny < n && nx < n )
                    map[ny][nx] += m;
                else
                    res += m;
            }
        }
        // 비율대로 분배 후, a칸에 남은 모래 더해주기
        map[y][x] = 0;
        int ny = y + dy[d%4];
        int nx = x + dx[d%4];
        if ( ny >= 0 && nx >= 0 && ny < n && nx < n )
            map[ny][nx] += amount;
        else
            res += amount;

    }

}