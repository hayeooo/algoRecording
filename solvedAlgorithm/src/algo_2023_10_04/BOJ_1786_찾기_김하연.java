package algo_2023_10_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * BOJ 1786 : 찾기
 * KMP 알고리즘은 LPS(Longest Proper Prefix which is Suffix) 배열을 사용하여 문자열 검색 시 불필요한 문자 간 비교를 없애 성능을 개선시킨 알고리즘
 * LPS[]: 일치하지 않는 문자가 있을 때 어디서부터 검색을 해야할 지(몇 칸을 뛰어넘어야 하는지) 알려주기 위한 지표
 * 		: proper prefix란 일반 prefix와 다르게 문자열 자기 자신은 제외
 */
public class BOJ_1786_찾기_김하연 {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static String T,P;
	static int[] LPS;
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 문자열 T, P를 입력받는다.
		T=br.readLine();
		P=br.readLine();
		
		// 전처리 단계
		// LPS를 구한다.
		LPS=new int[P.length()];
		LPS[0]=0;
		int prefixLen=0;
		int pointer=1;
		
		while (pointer<P.length()) {
			// match
			if (P.charAt(prefixLen)==P.charAt(pointer)) {
				prefixLen++;
				LPS[pointer]=prefixLen;
				pointer++;
			}
			// mismatch
			else {
				// 이전까지는 같았음
				if(prefixLen!=0) {
					prefixLen=LPS[prefixLen-1];
					// 다시 검사해야 하므로 pointer는 증가하지 않는다.
				}
				else {
					LPS[pointer]=0;
					pointer++;
				}
			}
		}// 전처리 완료
		
		// T 중간에 P가 몇 번 나타나는지, P가 나타나는 위치를 차례대로 공백으로 구분해 출력
		List<Integer> indexList=new ArrayList<>();
		
		int pPointer=0;
		
		for (int tPointer=0;tPointer<T.length();tPointer++) {
			// 같은 문자가 나올 때까지 건너뛰기
			while (pPointer>0 && P.charAt(pPointer)!=T.charAt(tPointer)) {
				pPointer=LPS[pPointer-1];
			}
			
			if(P.charAt(pPointer)==T.charAt(tPointer)) {
				if (pPointer==P.length()-1) {
					indexList.add(tPointer-pPointer+1);
					pPointer=LPS[pPointer];
				}
				else {
					pPointer++;
				}
			}
		}
		sb=new StringBuilder();
		sb.append(indexList.size()).append("\n");
		for (int idx=0;idx<indexList.size();idx++) {
			sb.append(indexList.get(idx)).append(" ");
		}
		System.out.print(sb);
	}

}
