import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        Node left, right;
        int key;
        Node(){
            Node left = null;
            Node right = null;
            Node key = null;
        }
    }
    static class BinaryTree{
        Node root = null;
        public void add(int key){
            Node newNode = new Node();
            newNode.key = key;
            if (this.root == null)
                root = newNode;
            else{
                root = addNode(root, newNode);
            }
        }
        private Node addNode(Node root, Node newNode){
            if(root == null)
                return newNode;
            else if( root.key > newNode.key ){
                root.left = addNode(root.left, newNode);
            }
            else if( root.key < newNode.key ){
                root.right = addNode(root.right, newNode);
            }
            return root;
        }
        public Node getRoot(){
            return root;
        }
        public void postOrder(Node now){
            if(now.left!=null){
                postOrder(now.left);
            }
            if(now.right!=null)
                postOrder(now.right);
            System.out.println(now.key);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        String num;
        BinaryTree bst = new BinaryTree();
        while ( (num = br.readLine()) != null){
            int key = Integer.parseInt(num);
            bst.add(key);
        }
        bst.postOrder(bst.getRoot());
    }
}