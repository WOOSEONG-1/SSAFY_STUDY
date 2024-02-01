import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 * 	- DP -> 위를 고르면 좌우아래, 아래는 좌우위 못쓰게 됨
 * 2. 시간 복잡도
 * 	- pass
 * 3. 자료 구조
 * 	- 배열 ( 탐색 : O(N), 삽입:O(1), 삭제 : O(N), 검색 : O(1) ) 
 */

public class Main {	
	static class Node{
		String l, r;
		Node(String l, String r) {
			this.l = l;
			this.r = r;
		}
	}
	static HashMap<String, Node> map = new HashMap<>();
	static StringBuffer sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuffer();
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		for ( int i = 1 ; i <= n ; i++) {
			stk = new StringTokenizer(br.readLine());
			String root = stk.nextToken();
			String left = stk.nextToken();
			String right = stk.nextToken();
			if ( left.equals(".") )
				left = "";
			if ( right.equals(".") )
				right = "";
			map.put(root, new Node(left, right));
		}
		preorder("A");
		sb.append("\n");
		inorder("A");
		sb.append("\n");
		postorder("A");
		System.out.print(sb);		
	}
	
	static void inorder(String root) {
		if ( root == "" )
			return;
		String left = map.get(root).l;
		String right = map.get(root).r;
		
		inorder(map.get(root).l);
		sb.append(root);
		inorder(map.get(root).r);
	}
	static void preorder(String root) {
		if ( root == "" )
			return;
		String left = map.get(root).l;
		String right = map.get(root).r;
		
		sb.append(root);
		preorder(map.get(root).l);		
		preorder(map.get(root).r);
	}
	static void postorder(String root) {
		if ( root == "" )
			return;
		String left = map.get(root).l;
		String right = map.get(root).r;
		
		postorder(map.get(root).l);		
		postorder(map.get(root).r);
		sb.append(root);
	}
}