package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 파스칼의 삼각형
 * 각 층의 양 끝은 1이고 나머지 값은 위층에 있는 값의 합으로 구성되어 있다.
 * 조합의 경우 n!의 연산을 수행해야 하기 때문에 n의 크기가 커지면 int형으로 나타낼 수 있는 범위를 초과하는 문제가 발생
 * 파스칼의 삼각형을 이용하면 각 항목 nCk를 (n-1)C(k-1) + (n-1)C(k)로 나타낼 수 있다.
 */
public class PascalTriangle_김하연 {
	
	static BufferedReader br;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// nCk 입력 받는다.
		int N=Integer.parseInt(br.readLine().trim());
		int K=Integer.parseInt(br.readLine().trim());
		
		int[][] combinationMatrix=new int[N+1][N+1];
		combinationMatrix[1][0]=1;
		combinationMatrix[1][1]=1;
		
		for (int n=2;n<=N;n++) {
			combinationMatrix[n][0]=1;
			combinationMatrix[n][n]=1;
			
			for (int k=1;k<=N;k++) {
				combinationMatrix[n][k]=combinationMatrix[n-1][k-1]+combinationMatrix[n-1][k];
			}
		}
		
		System.out.println(combinationMatrix[N][K]);
	}

}
