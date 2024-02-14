package algo_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1254_팰린드롬만들기_김하연 {

    static BufferedReader br;

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));
        String str=br.readLine().trim();

        // 가장 긴 펠린드롬 길이를 구한다.
        int size=-1;
        for (int start=0;start<str.length();start++){
            String sliced=str.substring(start);
            boolean result=isSymmetry(sliced);
            if (result){
                size=str.length()-sliced.length()+str.length();
                break;
            }
        }
        if (size==-1) size=str.length()*2-1;
        System.out.println(size);
    }

    public static boolean isSymmetry(String sliced){
        int left=0;
        int right=sliced.length()-1;
        boolean isSymmetry=true;
        while (left<=right){
            if(sliced.charAt(left)!=sliced.charAt(right)){
                isSymmetry=false;
                break;
            }
            left+=1;
            right-=1;
        }
        return isSymmetry;
    }
}
