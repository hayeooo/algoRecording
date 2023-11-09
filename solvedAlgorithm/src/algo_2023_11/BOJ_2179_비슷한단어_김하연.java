package algo_2023_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * BOJ 2179: 비슷한 단어
 * << 문제  >>
 * N개의 영단어들이 주어졌을 때, 가장 비슷한 두 단어를 구해내는 프로그램을 작성하시오.
 * 두 단어의 비슷한 정도는 두 단어의 접두사의 길이로 측정한다. 접두사란 두 단어의 앞부분에서 공통적으로 나타나는 부분문자열을 말한다.
 * 즉, 두 단어의 앞에서부터 M개의 글자들이 같으면서 M이 최대인 경우를 구하는 것이다.
 * 접두사의 길이가 최대인 경우가 여러 개일 때, 입력되는 순서대로 제일 앞쪽에 있는 단어를 답으로 한다.
 * 
 * << 풀이 방법 >>
 * 각 문자열과 순서를 저장하는 클래스를 활용한다.
 * 문자열을 기준으로 정렬 후, 접두사 길이가 최대인 위치를 S, T 변수에 저장한다.
 * 
 */


public class BOJ_2179_비슷한단어_김하연 {
	
	static BufferedReader br;
	static int N;
	static List<Word> words;
	static int maxPrefixLength;
	static int S,T;
	static class Word{
		String str;
		int index;
		
		Word(String str, int index){
			this.str=str;
			this.index=index;
		}

		@Override
		public String toString() {
			return "Word [str=" + str + ", index=" + index + "]";
		}
		
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 문자열의 개수 입력
		N=Integer.parseInt(br.readLine().trim());
		
		words=new ArrayList<>();
		for (int idx=0;idx<N;idx++) {
			String str=br.readLine().trim();
			words.add(new Word(str,idx));
		}
		
		// 문자열을 정렬한다.
		Collections.sort(words,new Comparator<Word>(){

			@Override
			public int compare(Word o1, Word o2) {
				return o1.str.compareTo(o2.str);
			}
			
		});
		
		maxPrefixLength=0;
		// two pointer로 공통 접두사 길이를 구한다.
		for (int startPointer=0;startPointer<N-1;startPointer++) {
			
			for (int endPointer=startPointer+1;endPointer<N;endPointer++) {
				// 같은 문자열인 경우 무시
				if (words.get(startPointer).str.equals(words.get(endPointer).str)) continue;
				
				// 공통 접두사 길이가 최대인 경우 갱신
				int prefix=commonPrefix(words.get(startPointer).str,words.get(endPointer).str);
				
				// 공통 접두사 길이가 최대 접두사 길이를 넘을 경우
				if (prefix>maxPrefixLength) {
					S=startPointer;
					T=endPointer;
					prefix=maxPrefixLength;
				}
				
				// 공통 접두사 길이가 최대 접두사 길이와 같을 경우
				// 앞쪽에 있는 단어 우선 저장
			}
		}
		System.out.println(words);
		
	}
	
	public static int commonPrefix(String str1,String str2) {
		int minLength=Math.min(str1.length(),str2.length());
		int commonCnt=0;
		
		for (int idx=0;idx<minLength;idx++) {
			if (str1.charAt(idx)!=str2.charAt(idx)) {
				break;
			}
			commonCnt++;
		}
		return commonCnt;
	}
}
