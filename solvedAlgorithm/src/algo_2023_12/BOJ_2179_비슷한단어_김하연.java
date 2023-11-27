package algo_2023_12;

import java.io.*;
import java.util.*;

/**
 * BOJ 2179: 비슷한 단어
 * N개의 영단어들이 주어졌을 때, 가장 비슷한 두 단어를 구해내는 프로그램을 작성한다.
 * 두 단어의 비슷한 정도는 두 단어의 접두사의 길이로 측정한다.
 * 접두사의 길이가 최대인 경우가 여러 개일 때에는 입력되는 순서대로 제일 앞쪽에 있는 단어를 답으로 한다.
 * S가 입력되는 순서대로 제일 앞쪽에 있는 단어인 경우를 출력하고, 그런 경우도 여러 개 있을 때에는 그 중에서 T가 입력되는 순서대로
 * 제일 앞쪽에 있는 단어인 경우를 출력한다.
 *
 * << 풀이 방법 >>
 * 입력 받을 때 같은 단어는 제외시킨다.
 * 원래 순서(위치)를 자료구조에 함께 담는다.
 * 정렬을 수행한 후, 접두사 길이가 최대인 경우 앞에 있는 문자열은 S에, 뒤에 있는 문자열을 T에 저장한다.
 */
public class BOJ_2179_비슷한단어_김하연{

    static BufferedReader br;
    static List<Info> infos;
    static List<String> strings;

    static int maxPrefix;

    static class Info implements Comparable<Info>{
        int index;
        String word;

        Info(int index,String word){
            this.index=index;
            this.word=word;
        }

        @Override
        public int compareTo(Info o) {
            return this.word.compareTo(o.word);
        }

        @Override
        public String toString(){
            return "("+index+", "+word+")";
        }
    }
    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        // 영단어의 개수를 입력받는다.
        int N=Integer.parseInt(br.readLine().trim());

        infos=new ArrayList<>();
        strings=new ArrayList<>();

        for (int idx=0;idx<N;idx++){
            String word=br.readLine().trim();
            // 중복되는 문자열이 아니라면 arraylist에 넣는다.
            if (!strings.contains(word)){
                infos.add(new Info(idx,word));
                strings.add(word);
            }
        }
        Collections.sort(infos);
        maxPrefix=0;
        Info S=null;
        Info T=null;

        // 정렬 후 접두사 길이 비교를 시작한다.
        for (int startPointer=0;startPointer<infos.size()-1;startPointer++){
            for (int endPointer=startPointer+1;endPointer<infos.size();endPointer++){
                // 두 문자열의 접두사 길이를 구한다.
                Info startInfo=infos.get(startPointer);
                Info endInfo=infos.get(endPointer);

                Info SInfo=startInfo.index<endInfo.index?startInfo:endInfo;
                Info TInfo=startInfo.index<endInfo.index?endInfo:startInfo;

                int length=getPrefixLength(startInfo.word,endInfo.word);

                // 접두사 길이가 0이라면 break;
                if (length==0) break;

                // 최대 접두사 길이와 같다면
                else if (length==maxPrefix){
                    // S가 앞쪽에 있는 단어를 먼저 출력한다.
                    if (S.index>SInfo.index){
                        S=SInfo;
                        T=TInfo;
                    }
                    else if(S.index==SInfo.index){
                        if (T.index>TInfo.index){
                            T=TInfo;
                        }
                    }
                }
                // 최대 접수다 길이보다 길다면
                else if (length>maxPrefix){
                    maxPrefix=length;
                    S=SInfo;
                    T=TInfo;
                }
            }
        }
        System.out.println(S.word);
        System.out.println(T.word);
    }

    public static int getPrefixLength(String s1, String s2){
        int minLength=Math.min(s1.length(),s2.length());
        int prefixLength=0;
        for (int idx=0;idx<minLength;idx++){
            if (s1.charAt(idx)!=s2.charAt(idx)) break;
            prefixLength++;
        }
        return prefixLength;
    }
}
