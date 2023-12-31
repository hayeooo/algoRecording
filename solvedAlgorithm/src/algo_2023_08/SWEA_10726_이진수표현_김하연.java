package algo_2023_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 10726 : 이진수 표현
 * 정수 N, M이 주어질 때, M의 이진수 표현의 마지막 N 비트가 모두 1로 켜져 있는지 아닌지 판별하여 출력한다.
 * N의 크기만큼 모두 켜져 있는(1<<N-1) 수로 표현한다.
 * 위를 M과 & (and) 연산을 수행했을 때, 자신과 같다면 마지막 N 비트가 모두 켜져 있다.
 */
public class SWEA_10726_이진수표현_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;				// 테스트케이스 수
	static int N, M;			// M의 이진수 표현의 마지막 N 비트
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트케이스 수를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			
			// N, M을 입력받는다.
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			
			// N만큼 1로 비트를 채운다.
			int toCompare=(1<<N)-1;
			
			sb.append("#").append(test_case).append(" ");
			
			// M과 and 연산을 한 결과과 자신과 같을 때 마지막 N비트는 켜져있다.
			if ((M&toCompare)==toCompare) {
				sb.append("ON");
			}
			// 그렇지 않은 경우 꺼져있다.
			else {
				sb.append("OFF");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
