package algo_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2607_비슷한단어_김하연 {

    static BufferedReader br;
    static int N;
    static int[] count;

    static int result;
    public static void main(String[] args) throws IOException{
        br=new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine().trim());

        String origin=br.readLine().trim();
        count=new int[26];
        for (char c: origin.toCharArray()){
            count[c-'A']+=1;
        }
        result=0;
        // 다른 문자열
        for (int idx=1;idx<N;idx++){

            String other=br.readLine().trim();
            if (Math.abs(other.length()-origin.length())>=2) continue;

            int[] originCount=count.clone();
            int common=0;
            for (char c:other.toCharArray()){
                if (originCount[c-'A']>0){
                    common+=1;
                    originCount[c-'A']-=1;
                }
            }
            // 공통된 문자의 개수와 문자열 길이로 판단.
            if (origin.length()==other.length() && (common==origin.length() || common+1==origin.length())) result+=1;
            else if (origin.length()+1==other.length() && (common==origin.length())) result+=1;
            else if (origin.length()==other.length()+1 && (common+1==origin.length())) result+=1;
        }
        System.out.println(result);
    }
}
