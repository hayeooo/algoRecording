package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * KMP algorithm
 * 문자열 T 안에 문자열 패턴 P가 들어있는 위치를 구하는 알고리즘
 * 건너 뛸 수 있는 최대한 건너뛰어서 불필요한 탐색을 줄인다.
 * 
 * 1. 전처리 과정이 필요하다. (부분 문자열에서 접두사==접미사의 최대 길이를 저장한다.)
 * 2. 탐색을 진행한다. (전처리 과정에서 생성한 배열을 바탕으로 건너 뛸 위치를 정한다.)
 */
public class KMPAlgorithm {
	static BufferedReader br;
	static int[] lps;
	static StringBuilder sb;
	static String T,P;		// 문자열 T, 문자열 P
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		// 문자열 T, P를 입력받는다.
		T=br.readLine();
		P=br.readLine();
		
		// 1. 전처리 과정을 수행한다.
		lps=new int[P.length()];
		// P의 첫 번째 문자의 접두사와 접미사는 하나로 겹치므로 0으로 초기화한다.
		lps[0]=0;
		
		// 접두사 == 접미사 최대길이
		int suffixLen=0;
		
		// P문자열 탐색 지점
		int pointer=1;
		
		while(pointer<P.length()) {
			// match
			if (P.charAt(pointer)==P.charAt(suffixLen)) {
				suffixLen++;
				lps[pointer]=suffixLen;
				pointer++;
			}
			// mismatch
			else {
				// 이전에 접두사 == 접미사 최대길이가 존재한 경우
				if (suffixLen!=0) {
					// 이전까지 같았던 길이-1 지점으로 suffixLen을 조정한다.
					suffixLen=lps[suffixLen-1];
					// 다시 비교 탐색한다.
				}
				else {
					lps[pointer]=0;
					pointer++;
				}
			}
		}// 전처리 완료
		
		// 탐색을 진행한다.
		int pPointer=0;
		List<Integer> indexList=new ArrayList<>();
		
		for (int tPointer=0;tPointer<T.length();tPointer++) {
			
			// mismatch인 경우 match일 때까지 pPointer를 조정한다.
			while (pPointer>0 && P.charAt(pPointer)!=T.charAt(tPointer)) {
				// pPointer에서 문자가 서로 다르다.
				// pPointer-1까지 부분문자열은 맞았다는 말
				// lps[pPointer-1]: P문자열의 pPointer-1까지 접두사 == 접미사 최대길이
				// lps[pPointer-1] 지점부터 다시 탐색한다.
				pPointer=lps[pPointer-1];
			}
			// match
			if (P.charAt(pPointer)==T.charAt(tPointer)) {
				// P문자열의 모든 비교가 끝났는지 확인
				if (pPointer==P.length()-1) {
					indexList.add(tPointer-pPointer+1);
					// T 내에 P 문자열 패턴을 또 발견하기 위해 위치를 조정한다.
					pPointer=lps[pPointer];
				}
				else {
					pPointer++;
				}
			}
		}
		sb.append(indexList.size()).append("\n");
		for (int idx=0;idx<indexList.size();idx++) {
			sb.append(indexList.get(idx)).append(" ");
		}
		System.out.print(sb);
	}

}
