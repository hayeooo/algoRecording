package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * BOJ 22233 : 가희와 키워드
 * [ 문제 ]
 * 가희는 블로그에 글을 쓰기 위해 메모장에 키워드를 적곤 합니다.
 * 지금까지 메모장에 써진 키워드는 모두 서로 다르며 총 N개가 존재합니다.
 * 가희는 새로운 글을 작성할 때, 최대 10개의 키워드에 대해서 글을 작성합니다.
 * 이 키워드들 중에 메모장에 있었던 키워드는 가희가 글을 쓴 이후, 메모장에서 지워지게 됩니다.
 * 가희는 블로그에 글을 쓰고 나서 메모장에 있는 키워드 개수가 몇 개인지 알고 싶습니다.
 *
 * [ 풀이 ]
 * 가희가 블로그에 사용한 키워드를 메모장에서 지우기 위해 HashSet을 사용한다.
 * 인덱스를 사용해서 순차 탐색할 필요 없이 키워드를 메모장에서 지울 수 있다.
 */
public class BOJ_22233_가희와키워드_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static StringBuilder sb;
    static int N;       // 가희가 메모장에 적은 키워드 개수
    static int M;       // 가희가 블로그에 쓴 글의 개수

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine().trim());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        HashSet<String> memo=new HashSet<>();

        for (int idx=0;idx<N;idx++){
            memo.add(br.readLine().trim());
        }
        sb=new StringBuilder();
        for (int doc=0;doc<M;doc++){
            String[] word=br.readLine().trim().split(",");
            for (int idx=0;idx<word.length;idx++){
                memo.remove(word[idx]);
            }
            sb.append(memo.size()).append("\n");
        }
        System.out.print(sb);

    }
}
