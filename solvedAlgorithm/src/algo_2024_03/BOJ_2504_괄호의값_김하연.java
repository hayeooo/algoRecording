package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Stack + 구현
 * 1. 여는 괄호('(','[')인 경우 2 or 3을 곱한다.
 * 2. 닫는 괄호가 나온 경우, 언제 점수에 반영해야 하는지 구분해서 더해야 함
 *  2-1. 올바를 괄호열을 만드는지 확인
 *  2-2. 올바른 괄호열인 경우
 *      2-2-a.
 */
public class BOJ_2504_괄호의값_김하연 {
    static BufferedReader br;
    static String brackets;

    static Stack<Character> stack;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        brackets = br.readLine().trim();

        stack=new Stack<>();
        int cnt=1;
        int result=0;

        for (int idx=0;idx<brackets.length();idx++){
            char bracket=brackets.charAt(idx);
            // 여는 괄호인 경우
            if (bracket == '(' ){
                cnt*=2;
                stack.add(bracket);
            }
            else if(bracket=='['){
                cnt*=3;
                stack.add(bracket);
            }
            else if (bracket==')'){
                if (stack.isEmpty() || stack.peek()!='('){
                    result=0;
                    break;
                }
                if (brackets.charAt(idx-1)=='('){
                    result+=cnt;
                }
                stack.pop();
                cnt/=2;
            }
            else if (bracket==']'){
                if (stack.isEmpty() || stack.peek()!='['){
                    result=0;
                    break;
                }
                if (brackets.charAt(idx-1)=='['){
                    result+=cnt;
                }
                stack.pop();
                cnt/=3;
            }
        }
        if (!stack.isEmpty()){
            System.out.print(0);
        }
        else{
            System.out.print(result);
        }
    }

}
