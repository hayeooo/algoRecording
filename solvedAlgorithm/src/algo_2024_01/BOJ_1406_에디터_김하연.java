package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * LinkedList로 만들어서 풀었지만 시간 초과
 *
 */
public class BOJ_1406_에디터_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static StringBuilder sb;
    static int N;
    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        String string=br.readLine().trim();

        Stack<Character> leftStack=new Stack<>();
        Stack<Character> rightStack=new Stack<>();

        for (int idx=0;idx<string.length();idx++){
            leftStack.push(string.charAt(idx));
        }

        N=Integer.parseInt(br.readLine().trim());

        for (int idx=0;idx<N;idx++){
            st=new StringTokenizer(br.readLine().trim());
            String d=st.nextToken();

            if (d.equals("L")){
                if (!leftStack.isEmpty()){
                    rightStack.push(leftStack.pop());
                }
            }
            else if(d.equals("D")){
                if(!rightStack.isEmpty()){
                    leftStack.push(rightStack.pop());
                }
            }
            else if(d.equals("B")){
                if(!leftStack.isEmpty()){
                    leftStack.pop();
                }
            }
            else if(d.equals("P")){
                char c=st.nextToken().charAt(0);
                leftStack.push(c);
            }
        }
        sb=new StringBuilder();
        while (!leftStack.isEmpty()){
            rightStack.push(leftStack.pop());
        }
        while (!rightStack.isEmpty()){
            sb.append(rightStack.pop());
        }
        System.out.println(sb);
        
    }


}
