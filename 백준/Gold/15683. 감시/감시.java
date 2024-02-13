import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

/*
 * 1. 아이디어
 *
 * 2. 시간 복잡도
 *	- N, M 1 ~ 8 / 8
 *
 * 3. 자료구조
 *
 * */
public class Main {
    static int minv = Integer.MAX_VALUE;
    static ArrayList<Point> lst;
    static int n, m;

    static class Point {
        int y, x, cmd;

        Point(int y, int x, int cmd) {
            this.y = y;
            this.x = x;
            this.cmd = cmd;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        int arr[][] = new int[n][m];
        lst = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
                if (arr[i][j] != 6 && arr[i][j] != 0)
                    lst.add(new Point(i, j, arr[i][j]));
            }
        }
        int r = lst.size();
        dfs(arr, 0, r);
        sb.append(minv);
        System.out.print(sb);
    }

    // arr 데이터 배열 / depth = 깊이 / r = 최대 깊이 )
    static void dfs(int[][] arr, int depth, int r) {
        if (depth == r) {
            int sumv = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] == 0) {
                        sumv += 1;
                    }
                }
            }
//            for ( int i = 0 ; i < n ; i++ ){
//                for ( int j = 0 ; j < m ; j++ ){
//                    System.out.print(arr[i][j]+"\t");
//                }
//                System.out.println();
//            }
//            System.out.println("sumv = "+sumv);
            minv = Math.min(minv, sumv);
            return;
        }
        int cmd = lst.get(depth).cmd;
        int ny = lst.get(depth).y;
        int nx = lst.get(depth).x;
        if (cmd == 1) {
            for (int k = 0; k < 4; k++) {
                // left
                if (k == 0) {
                    for (int i = nx - 1; i >= 0; i--) {
                        if (arr[ny][i] == 0)
                            arr[ny][i] = 8;
                        else if (arr[ny][i] >= 8)
                            arr[ny][i] += 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    dfs(arr, depth + 1, r);
                    for (int i = nx - 1; i >= 0; i--) {
                        if (arr[ny][i] == 8)
                            arr[ny][i] = 0;
                        else if (arr[ny][i] > 8)
                            arr[ny][i] -= 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                }
                // right
                else if (k == 1) {
                    for (int i = nx + 1; i < m; i++) {
                        if (arr[ny][i] == 0)
                            arr[ny][i] = 8;
                        else if (arr[ny][i] >= 8)
                            arr[ny][i] += 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    dfs(arr, depth + 1, r);
                    for (int i = nx + 1; i < m; i++) {
                        if (arr[ny][i] == 8)
                            arr[ny][i] = 0;
                        else if (arr[ny][i] > 8)
                            arr[ny][i] -= 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                }
                // up
                else if (k == 2) {
                    for (int i = ny - 1; i >= 0; i--) {
                        if (arr[i][nx] == 0)
                            arr[i][nx] = 8;
                        else if (arr[i][nx] >= 8)
                            arr[i][nx] += 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    dfs(arr, depth + 1, r);
                    for (int i = ny - 1; i >= 0; i--) {
                        if (arr[i][nx] == 8)
                            arr[i][nx] = 0;
                        else if (arr[i][nx] > 8)
                            arr[i][nx] -= 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                }
                // down
                else if (k == 3) {
                    for (int i = ny + 1; i < n; i++) {
                        if (arr[i][nx] == 0)
                            arr[i][nx] = 8;
                        else if (arr[i][nx] >= 8)
                            arr[i][nx] += 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    dfs(arr, depth + 1, r);
                    for (int i = ny + 1; i < n; i++) {
                        if (arr[i][nx] == 8)
                            arr[i][nx] = 0;
                        else if (arr[i][nx] > 8)
                            arr[i][nx] -= 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                }
            }
        } else if (cmd == 2) {
            for (int k = 0; k < 2; k++) {
                // left
                if (k == 0) {
                    for (int i = nx - 1; i >= 0; i--) {
                        if (arr[ny][i] == 0)
                            arr[ny][i] = 8;
                        else if (arr[ny][i] >= 8)
                            arr[ny][i] += 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = nx + 1; i < m; i++) {
                        if (arr[ny][i] == 0)
                            arr[ny][i] = 8;
                        else if (arr[ny][i] >= 8)
                            arr[ny][i] += 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    dfs(arr, depth + 1, r);
                    for (int i = nx - 1; i >= 0; i--) {
                        if (arr[ny][i] == 8)
                            arr[ny][i] = 0;
                        else if (arr[ny][i] > 8)
                            arr[ny][i] -= 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = nx + 1; i < m; i++) {
                        if (arr[ny][i] == 8)
                            arr[ny][i] = 0;
                        else if (arr[ny][i] > 8)
                            arr[ny][i] -= 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                }
                // up
                else if (k == 1) {
                    for (int i = ny - 1; i >= 0; i--) {
                        if (arr[i][nx] == 0)
                            arr[i][nx] = 8;
                        else if (arr[i][nx] >= 8)
                            arr[i][nx] += 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    for (int i = ny + 1; i < n; i++) {
                        if (arr[i][nx] == 0)
                            arr[i][nx] = 8;
                        else if (arr[i][nx] >= 8)
                            arr[i][nx] += 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    dfs(arr, depth + 1, r);
                    for (int i = ny - 1; i >= 0; i--) {
                        if (arr[i][nx] == 8)
                            arr[i][nx] = 0;
                        else if (arr[i][nx] > 8)
                            arr[i][nx] -= 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    for (int i = ny + 1; i < n; i++) {
                        if (arr[i][nx] == 8)
                            arr[i][nx] = 0;
                        else if (arr[i][nx] > 8)
                            arr[i][nx] -= 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                }
            }
        } else if (cmd == 3) {
            for (int k = 0; k < 4; k++) {
                // up + left
                if (k == 0) {
                    for (int i = nx - 1; i >= 0; i--) {
                        if (arr[ny][i] == 0)
                            arr[ny][i] = 8;
                        else if (arr[ny][i] >= 8)
                            arr[ny][i] += 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = ny - 1; i >= 0; i--) {
                        if (arr[i][nx] == 0)
                            arr[i][nx] = 8;
                        else if (arr[i][nx] >= 8)
                            arr[i][nx] += 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    dfs(arr, depth + 1, r);
                    for (int i = nx - 1; i >= 0; i--) {
                        if (arr[ny][i] == 8)
                            arr[ny][i] = 0;
                        else if (arr[ny][i] > 8)
                            arr[ny][i] -= 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = ny - 1; i >= 0; i--) {
                        if (arr[i][nx] == 8)
                            arr[i][nx] = 0;
                        else if (arr[i][nx] > 8)
                            arr[i][nx] -= 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                }
                // up + right
                else if (k == 1) {
                    for (int i = ny - 1; i >= 0; i--) {
                        if (arr[i][nx] == 0)
                            arr[i][nx] = 8;
                        else if (arr[i][nx] >= 8)
                            arr[i][nx] += 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    for (int i = nx + 1; i < m; i++) {
                        if (arr[ny][i] == 0)
                            arr[ny][i] = 8;
                        else if (arr[ny][i] >= 8)
                            arr[ny][i] += 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    dfs(arr, depth + 1, r);
                    for (int i = ny - 1; i >= 0; i--) {
                        if (arr[i][nx] == 8)
                            arr[i][nx] = 0;
                        else if (arr[i][nx] > 8)
                            arr[i][nx] -= 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    for (int i = nx + 1; i < m; i++) {
                        if (arr[ny][i] == 8)
                            arr[ny][i] = 0;
                        else if (arr[ny][i] > 8)
                            arr[ny][i] -= 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                }
                // down + left
                else if (k == 2) {
                    for (int i = nx - 1; i >= 0; i--) {
                        if (arr[ny][i] == 0)
                            arr[ny][i] = 8;
                        else if (arr[ny][i] >= 8)
                            arr[ny][i] += 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = ny + 1; i < n; i++) {
                        if (arr[i][nx] == 0)
                            arr[i][nx] = 8;
                        else if (arr[i][nx] >= 8)
                            arr[i][nx] += 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    dfs(arr, depth + 1, r);
                    for (int i = nx - 1; i >= 0; i--) {
                        if (arr[ny][i] == 8)
                            arr[ny][i] = 0;
                        else if (arr[ny][i] > 8)
                            arr[ny][i] -= 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = ny + 1; i < n; i++) {
                        if (arr[i][nx] == 8)
                            arr[i][nx] = 0;
                        else if (arr[i][nx] > 8)
                            arr[i][nx] -= 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }

                }
                // down + right
                else if (k == 3) {
                    for (int i = ny + 1; i < n; i++) {
                        if (arr[i][nx] == 0)
                            arr[i][nx] = 8;
                        else if (arr[i][nx] >= 8)
                            arr[i][nx] += 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    for (int i = nx + 1; i < m; i++) {
                        if (arr[ny][i] == 0)
                            arr[ny][i] = 8;
                        else if (arr[ny][i] >= 8)
                            arr[ny][i] += 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    dfs(arr, depth + 1, r);
                    for (int i = ny + 1; i < n; i++) {
                        if (arr[i][nx] == 8)
                            arr[i][nx] = 0;
                        else if (arr[i][nx] > 8)
                            arr[i][nx] -= 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    for (int i = nx + 1; i < m; i++) {
                        if (arr[ny][i] == 8)
                            arr[ny][i] = 0;
                        else if (arr[ny][i] > 8)
                            arr[ny][i] -= 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }

                }
            }
        } else if (cmd == 4) {
            for (int k = 0; k < 4; k++) {
                // l + r + u
                if (k == 0) {
                    for (int i = nx - 1; i >= 0; i--) {
                        if (arr[ny][i] == 0)
                            arr[ny][i] = 8;
                        else if (arr[ny][i] >= 8)
                            arr[ny][i] += 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = nx + 1; i < m; i++) {
                        if (arr[ny][i] == 0)
                            arr[ny][i] = 8;
                        else if (arr[ny][i] >= 8)
                            arr[ny][i] += 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = ny - 1; i >= 0; i--) {
                        if (arr[i][nx] == 0)
                            arr[i][nx] = 8;
                        else if (arr[i][nx] >= 8)
                            arr[i][nx] += 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    dfs(arr, depth + 1, r);
                    for (int i = nx - 1; i >= 0; i--) {
                        if (arr[ny][i] == 8)
                            arr[ny][i] = 0;
                        else if (arr[ny][i] > 8)
                            arr[ny][i] -= 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = nx + 1; i < m; i++) {
                        if (arr[ny][i] == 8)
                            arr[ny][i] = 0;
                        else if (arr[ny][i] > 8)
                            arr[ny][i] -= 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = ny - 1; i >= 0; i--) {
                        if (arr[i][nx] == 8)
                            arr[i][nx] = 0;
                        else if (arr[i][nx] > 8)
                            arr[i][nx] -= 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                }
                // l + r + d
                else if (k == 1) {
                    for (int i = nx - 1; i >= 0; i--) {
                        if (arr[ny][i] == 0)
                            arr[ny][i] = 8;
                        else if (arr[ny][i] >= 8)
                            arr[ny][i] += 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = nx + 1; i < m; i++) {
                        if (arr[ny][i] == 0)
                            arr[ny][i] = 8;
                        else if (arr[ny][i] >= 8)
                            arr[ny][i] += 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = ny + 1; i < n; i++) {
                        if (arr[i][nx] == 0)
                            arr[i][nx] = 8;
                        else if (arr[i][nx] >= 8)
                            arr[i][nx] += 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    dfs(arr, depth + 1, r);
                    for (int i = nx - 1; i >= 0; i--) {
                        if (arr[ny][i] == 8)
                            arr[ny][i] = 0;
                        else if (arr[ny][i] > 8)
                            arr[ny][i] -= 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = nx + 1; i < m; i++) {
                        if (arr[ny][i] == 8)
                            arr[ny][i] = 0;
                        else if (arr[ny][i] > 8)
                            arr[ny][i] -= 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = ny + 1; i < n; i++) {
                        if (arr[i][nx] == 8)
                            arr[i][nx] = 0;
                        else if (arr[i][nx] > 8)
                            arr[i][nx] -= 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                }
                // l + u + d
                else if (k == 2) {
                    for (int i = nx - 1; i >= 0; i--) {
                        if (arr[ny][i] == 0)
                            arr[ny][i] = 8;
                        else if (arr[ny][i] >= 8)
                            arr[ny][i] += 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = ny - 1; i >= 0; i--) {
                        if (arr[i][nx] == 0)
                            arr[i][nx] = 8;
                        else if (arr[i][nx] >= 8)
                            arr[i][nx] += 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    for (int i = ny + 1; i < n; i++) {
                        if (arr[i][nx] == 0)
                            arr[i][nx] = 8;
                        else if (arr[i][nx] >= 8)
                            arr[i][nx] += 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    dfs(arr, depth + 1, r);
                    for (int i = nx - 1; i >= 0; i--) {
                        if (arr[ny][i] == 8)
                            arr[ny][i] = 0;
                        else if (arr[ny][i] > 8)
                            arr[ny][i] -= 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = ny - 1; i >= 0; i--) {
                        if (arr[i][nx] == 8)
                            arr[i][nx] = 0;
                        else if (arr[i][nx] > 8)
                            arr[i][nx] -= 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    for (int i = ny + 1; i < n; i++) {
                        if (arr[i][nx] == 8)
                            arr[i][nx] = 0;
                        else if (arr[i][nx] > 8)
                            arr[i][nx] -= 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                }
                // r + u + d
                else if (k == 3) {
                    for (int i = nx + 1; i < m; i++) {
                        if (arr[ny][i] == 0)
                            arr[ny][i] = 8;
                        else if (arr[ny][i] >= 8)
                            arr[ny][i] += 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = ny - 1; i >= 0; i--) {
                        if (arr[i][nx] == 0)
                            arr[i][nx] = 8;
                        else if (arr[i][nx] >= 8)
                            arr[i][nx] += 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    for (int i = ny + 1; i < n; i++) {
                        if (arr[i][nx] == 0)
                            arr[i][nx] = 8;
                        else if (arr[i][nx] >= 8)
                            arr[i][nx] += 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    dfs(arr, depth + 1, r);
                    for (int i = nx + 1; i < m; i++) {
                        if (arr[ny][i] == 8)
                            arr[ny][i] = 0;
                        else if (arr[ny][i] > 8)
                            arr[ny][i] -= 1;
                        else if (arr[ny][i] == 6)
                            break;
                    }
                    for (int i = ny - 1; i >= 0; i--) {
                        if (arr[i][nx] == 8)
                            arr[i][nx] = 0;
                        else if (arr[i][nx] > 8)
                            arr[i][nx] -= 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                    for (int i = ny + 1; i < n; i++) {
                        if (arr[i][nx] == 8)
                            arr[i][nx] = 0;
                        else if (arr[i][nx] > 8)
                            arr[i][nx] -= 1;
                        else if (arr[i][nx] == 6)
                            break;
                    }
                }
            }
        } else if (cmd == 5) {
            for (int i = nx - 1; i >= 0; i--) {
                if (arr[ny][i] == 0)
                    arr[ny][i] = 8;
                else if (arr[ny][i] >= 8)
                    arr[ny][i] += 1;
                else if (arr[ny][i] == 6)
                    break;
            }
            for (int i = nx + 1; i < m; i++) {
                if (arr[ny][i] == 0)
                    arr[ny][i] = 8;
                else if (arr[ny][i] >= 8)
                    arr[ny][i] += 1;
                else if (arr[ny][i] == 6)
                    break;
            }
            for (int i = ny - 1; i >= 0; i--) {
                if (arr[i][nx] == 0)
                    arr[i][nx] = 8;
                else if (arr[i][nx] >= 8)
                    arr[i][nx] += 1;
                else if (arr[i][nx] == 6)
                    break;
            }
            for (int i = ny + 1; i < n; i++) {
                if (arr[i][nx] == 0)
                    arr[i][nx] = 8;
                else if (arr[i][nx] >= 8)
                    arr[i][nx] += 1;
                else if (arr[i][nx] == 6)
                    break;
            }
            dfs(arr, depth + 1, r);
            for (int i = nx - 1; i >= 0; i--) {
                if (arr[ny][i] == 8)
                    arr[ny][i] = 0;
                else if (arr[ny][i] > 8)
                    arr[ny][i] -= 1;
                else if (arr[ny][i] == 6)
                    break;
            }
            for (int i = nx + 1; i < m; i++) {
                if (arr[ny][i] == 8)
                    arr[ny][i] = 0;
                else if (arr[ny][i] > 8)
                    arr[ny][i] -= 1;
                else if (arr[ny][i] == 6)
                    break;
            }
            for (int i = ny + 1; i < n; i++) {
                if (arr[i][nx] == 8)
                    arr[i][nx] = 0;
                else if (arr[i][nx] > 8)
                    arr[i][nx] -= 1;
                else if (arr[i][nx] == 6)
                    break;
            }
            for (int i = ny - 1; i >= 0; i--) {
                if (arr[i][nx] == 8)
                    arr[i][nx] = 0;
                else if (arr[i][nx] > 8)
                    arr[i][nx] -= 1;
                else if (arr[i][nx] == 6)
                    break;
            }
        }
    }
}