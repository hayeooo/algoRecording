package algo_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_9251_LCS_김하연 {

    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));

        String str1=br.readLine().trim();
        String str2=br.readLine().trim();

        int[][] dp=new int[str2.length()+1][str1.length()+1];

        for (int row=1;row<=str2.length();row++){
            for (int col=1;col<=str1.length();col++){
                if (str2.charAt(row-1)==str1.charAt(col-1)){
                    dp[row][col]=dp[row-1][col-1]+1;
                }
                else{
                    dp[row][col]=Math.max(dp[row-1][col],dp[row][col-1]);
                }
            }
        }
       System.out.println(dp[str2.length()][str1.length()]);

    }
}
