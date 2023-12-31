package algo_2023_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 1288: 새로운 불면증 치료법
 * N의 배수에서
 * 0~9의 숫자가 등장했는지 확인하기 위한 10자리 수의 비트를 만든다.
 * 현재 본 숫자를 1로 셋팅한다.
 * 111111111와 같다면 숫자 세기를 종료한다. (1111111111=(1<<10)-1)
 */
public class SWEA_1288_새로운불면증치료법_김하연 {
	static BufferedReader br;
	static StringBuilder sb;
	
	static int T;				// 테스트케이스 개수
	static String N;				// 양의 시작 번호 N
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트케이스 개수를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			
			// 첫번째 양의 번호를 입력받는다.
			N=br.readLine().trim();
			int target=(1<<10)-1;			// 0~9까지의 숫자가 모두 확인되었을 때 나타나는 값
			int check=0;
			int multiple=1;
			String next = null;
			while (target!=check) {
				next=Integer.toString(Integer.parseInt(N)*multiple);
				for (int idx=0;idx<next.length();idx++) {
					char num=next.charAt(idx);
					int actualNum=num-'0';
					check|=(1<<actualNum);
				}
				multiple+=1;
			}
			
			sb.append("#").append(test_case).append(" ").append(next).append("\n");
		}
		
		System.out.println(sb);
	}

}
