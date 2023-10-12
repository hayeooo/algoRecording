package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * SWEA 5658: 보물상자 비밀번호
 * 보물 상자의 뚜껑은 시계방향으로 돌릴 수 있고, 한 번 돌릴 때마다 숫자가 시계방향으로 한 칸씩 회전한다.
 * 각 변에는 동일한 개수의 숫자가 있고, 시계방향 순으로 높은 자리 숫자에 해당하며 하나의 수를 나타낸다.
 * 보물상자에는 자물쇠가 걸려있는데, 이 자물쇠의 비밀번호는 보물 상자에 적힌 숫자로 만들 수 있는 모든 수 중, K 번째로 큰 수를 10진수로 만든 수이다.
 * N개의 숫자가 입력으로 주어졌을 때, 보물상자의 비밀번호를 출력한다.
 * 서로 다른 회전 횟수에서 동일한 수가 중복으로 생성될 수 있다. 크기 순서를 셀 때 같은 수를 중복으로 세지 않도록 주의한다.
 * 
 * <<풀이방법>>
 * 1. 보물상자에서 만들 수 있는 모든 숫자를 '중복 없이' 저장한다.
 * 2. 크기가 큰 순서대로 문자열 정렬한다.
 * 3. K번째 위치의 수를 찾아서 10진수로 변환하여 출력한다.
 */
public class SWEA_5658_보물상자비밀번호_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;			// 테스트케이스 개수
	static int N,K;			// 숫자의 개수 N, 크기 순서 K
	static char[] pwdBox; 	// 상자에 적힌 숫자
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트케이스 개수를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			
			st=new StringTokenizer(br.readLine().trim());
			
			// 숫자의 개수와 크기 순서를 입력받는다.
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			// 상자에 적힌 숫자를 입력받는다.
			pwdBox=br.readLine().trim().toCharArray();
			
			Set<String> hset=new HashSet<>();
			
			int rotateCnt=N/4;		// 회전 횟수
			for (int rotate=0;rotate<rotateCnt;rotate++) {
				// 각 회전마다 배열을 돌린다.
				rotateClockwise();
				
				// 돌린 배열에서 생성된 비밀번호를 저장한다.
				for (int start=0;start<N;start+=N/4) {
					String comb="";
					for (int idx=start;idx<start+N/4;idx++) {
						comb+=pwdBox[idx];
					}
					hset.add(comb);
				}
			}
			// hashSet을 arrayList로 변환하여 정렬
			List<String> pwdList=new ArrayList<>(hset);
			Collections.sort(pwdList,Collections.reverseOrder());
			
			// 10진수로 변환하여 출력
			System.out.println("#"+test_case+" "+Long.parseLong(pwdList.get(K-1),16));
			
		}
		
	}
	// 시계 방향으로 돌린다.
	public static void rotateClockwise() {
		char tmp=pwdBox[N-1];
		for (int idx=N-2;idx>=0;idx--) {
			pwdBox[idx+1]=pwdBox[idx];
		}
		pwdBox[0]=tmp;
	}

}
