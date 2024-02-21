package algo_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_1991_트리순회_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N;
    static HashMap<String,String[]> binaryTree;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine().trim());

        binaryTree=new HashMap<>();

        // 트리를 입력받는다.
        for (int idx=0;idx<N;idx++){
            st=new StringTokenizer(br.readLine().trim());
            String parent=st.nextToken();
            String leftChild=st.nextToken();
            String rightChild=st.nextToken();

            binaryTree.put(parent,new String[]{leftChild,rightChild});
        }
        sb=new StringBuilder();
        // 전위 순회
        preorder("A");
        sb.append("\n");
        // 중위 순회
        inorder("A");
        sb.append("\n");
        // 후위 순회
        postorder("A");

        System.out.print(sb);
    }
    // 전위 순회
    public static void preorder(String parent){
        // 종료 조건
        if (parent.equals(".")) return;
        // 루트
        sb.append(parent);
        // 왼쪽 자식
        preorder(binaryTree.get(parent)[0]);
        // 오른쪽 자식
        preorder(binaryTree.get(parent)[1]);
    }
    // 중위 순회
    public static void inorder(String parent){
        // 종료 조건
        if (parent.equals(".")) return;
        // 왼쪽 자식
        inorder(binaryTree.get(parent)[0]);
        // 루트
        sb.append(parent);
        // 오른쪽 자식
        inorder(binaryTree.get(parent)[1]);
        
    }

    // 후위 순회
    public static void postorder(String parent){
        // 종료 조건
        if (parent.equals(".")) return;
        // 왼쪽 자식
        postorder(binaryTree.get(parent)[0]);
        // 오른쪽 자식
        postorder(binaryTree.get(parent)[1]);
        // 루트
        sb.append(parent);
    }
    
}
