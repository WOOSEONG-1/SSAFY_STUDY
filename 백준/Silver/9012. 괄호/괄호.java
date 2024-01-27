import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

public class Main{
    static class Person implements Comparable<Person>{
        int age;
        String name;
        Person(int age, String name){
            this.age = age;
            this.name = name;
        }
        @Override
        public int compareTo(Person other){
            if ( this.age > other.age )
                return 1;
            else if ( this.age == other.age)
                return 0;
            else
                return -1;
        }
        @Override
        public String toString(){
            return age + " " + name;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        StringTokenizer stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken());

        for ( int i = 0 ; i < n ; i++){
            stk = new StringTokenizer(br.readLine());
            String line = stk.nextToken();
            char[] crr = line.toCharArray();
            if(chk(crr))
                sb.append("YES").append("\n");
            else
                sb.append("NO").append("\n");
        }
        System.out.print(sb);
    }
    static boolean chk ( char[] crr){
        int left = 0;
        int right = 0;
        for ( char c : crr ){
            if ( c == '(' ){
                left++;
            }
            else{
                right++;
            }
            if (right > left)
                return false;
        }
        if ( left == right )
            return true;
        else
            return false;
    }
}
