package algo_2023_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * BOJ 2631: 줄세우기
 * 선생님은 1번부터 N번까지 번호가 적혀있는 번호표를 아이들의 가슴에 붙여주었다.
 * 선생님은 목적지까지 번호순서대로 일렬로 걸어가도록 하였다. 이동 도중에 보니 아이들의 번호 순서가 바뀌었다.
 * 선생님은 다시 번호 순서대로 줄을 세우기 위해 아이들의 위치를 옮기려고 한다. 아이들이 혼란스러워하지 않도록 하기 위해
 * 위치를 옮기는 아이들의 수를 최소로 하려고 한다.
 *
 * << 문제 풀이 >>
 * Greedy하게 생각해보자
 * 현재 아이 숫자 + 1 != 다음 아이 숫자 라면
 * 위치가 잘못되었으므로 자기 자리로 위치를 이동시킨다. : arraylist의 add method를 사용한다.
 * 원래 자기 자리로 가는 것인 순서대로 줄을 세우는 방법의 최소이다. -> 오히려 더 많이 움직이게 된다.
 *
 * 구글링하여 얻은 답! LIS
 * 굳이 어린이 한 명씩 옮기려고 하지 않아도 된다.
 * 이미 오름차순으로 정렬되어 있는 어린이들은 움직일 필요가 없기 때문에 고정된 어린이 수를 뺀 나머지 어린이들은 위치를 옮겨야 한다.
 */
public class BOJ_2631_줄세우기_김하연{

    static BufferedReader br;
    static List<Integer> numbers;
    static int minCount=0;
    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        // 아이들의 수를 입력 받는다.
        int N=Integer.parseInt(br.readLine().trim());

        
    }
}
