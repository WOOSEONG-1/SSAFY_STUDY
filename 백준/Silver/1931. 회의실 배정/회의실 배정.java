import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;
//
//
public class Main {
    static class Conf implements Comparable<Conf>{
        int start, end;
        Conf(int start,int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Conf o){
            if(this.end > o.end)
                return 1;
            else if(this.end == o.end){
                if(this.start > o.start)
                    return 1;
                else if (this.start == o.start)
                    return 0;
                else
                    return -1;
            }
            else
                return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());
        Conf[] crr = new Conf[n];
        for ( int i = 0 ; i < n ; i++){
            stk = new StringTokenizer(br.readLine());
            crr[i] = new Conf(Integer.parseInt(stk.nextToken()), Integer.parseInt(stk.nextToken()));
        }
        Arrays.sort(crr);
        int end = crr[0].end;
        int cnt = 1;
        for ( int i = 1 ; i < crr.length ; i++ ){
            if ( crr[i].start >= end ){
                end = crr[i].end;
                cnt++;
            }
            else
                continue;
        }
        sb.append(cnt);
        System.out.print(sb);
    }

}
